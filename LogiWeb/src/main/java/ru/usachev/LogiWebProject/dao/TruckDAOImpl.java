package ru.usachev.LogiWebProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.BusinessCalculating;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.dto.restDTO.TruckRestDTO;
import ru.usachev.LogiWebProject.entity.City;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.entity.Truck;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TruckDAOImpl implements TruckDAO{

    private static Logger logger = Logger.getLogger(TruckDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BusinessCalculating calculating;

    @Override
    public List<Truck> getAllTrucks() {
        Session session = sessionFactory.getCurrentSession();
        List<Truck> trucks = session.createQuery("from Truck ").getResultList();
        try {
            return trucks;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Truck> getAllEnabledTrucks() {
        Session session = sessionFactory.getCurrentSession();

        List<Truck> trucks = session
                .createQuery("from Truck where isEnabled = true")
                .getResultList();

        try {
            return trucks;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Truck getTruck(int id) {
        Session session = sessionFactory.getCurrentSession();
        Truck truck = session.get(Truck.class, id);
        try {
            return truck;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void deleteTruck(int id) {
        Session session = sessionFactory.getCurrentSession();
        Truck truck = session.get(Truck.class, id);
        truck.setEnabled(false);
        session.saveOrUpdate(truck);

        logger.info("disable truck: " + truck.getRegistrationNumber());
    }

    @Override
    public void saveTruck(Truck truck) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from City where name=:cityName ");
        query.setParameter("cityName", truck.getCity().getName());
        City city = (City) query.getSingleResult();
        city.addTruckToTruckList(truck);
        session.saveOrUpdate(truck);

        if (truck.getId() != 0)
            logger.info("save truck: " + truck.getRegistrationNumber());
        else
            logger.info("update truck: " + truck.getRegistrationNumber());
    }

    @Override
    public List<Truck> getValidTrucksForOrder(List<WaypointDTO> waypoints) {
        Session session = sessionFactory.getCurrentSession();

        final int needingCapacity;
        needingCapacity = calculating.calculateNeedingCapacityByWaypointList(waypoints);

        Query query = session.createQuery("from Truck where state=true and isEnabled = true");
        List<Truck> trucks = query.getResultList();

        try {
            if (!trucks.isEmpty()){
                trucks.removeIf(truck -> !truck.isState());
                trucks.removeIf(truck -> truck.getOrder() != null);
                trucks.removeIf(truck -> truck.getCapacity() < needingCapacity);
            }
            return trucks;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Truck getTruckByRegistrationNumber(String registrationNumber) {
        Session session = sessionFactory.getCurrentSession();
        Truck truck = session.createQuery("from Truck where registrationNumber=:regNumber", Truck.class)
                .setParameter("regNumber", registrationNumber).getSingleResult();

        try {
            return truck;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Truck getTruckByOrderId(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        Truck truck;

        if (order.isStatus()){
            int truckId = (int) session.createNativeQuery("select truck_id from completed_orders where number = ?")
                    .setParameter(1, orderId)
                    .getSingleResult();

            truck = session.get(Truck.class, truckId);

        } else {
            truck = session.createQuery("from Truck where Order.id=:orderId", Truck.class)
                    .setParameter("orderId", orderId).getSingleResult();
        }

        try {
            return truck;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Truck getTruckByOrderNumber(int number) {
        Session session = sessionFactory.getCurrentSession();

        Order order = (Order) session.createQuery("from Order where number=:number")
                .setParameter("number", number)
                .getSingleResult();


        return order.getTruck();
    }

    @Override
    public Truck getTruckByDriverId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Driver driver = session.get(Driver.class, id);

        return driver.getTruck();
    }

    @Override
    public TruckRestDTO getTruckRestDTO() {
        TruckRestDTO truckRestDTO = null;

        List<Truck> trucks = getAllEnabledTrucks();

        if (trucks != null){
            truckRestDTO = new TruckRestDTO();
            int numberOfTruckNow = trucks.size();

            trucks.removeIf(truck -> !truck.isState());
            int numberOfBrokenTruck = numberOfTruckNow - trucks.size();

            int numberOfFreeTruck = (int) trucks
                    .stream()
                    .filter(truck -> truck.getOrder() == null)
                    .count();

            int numberOfInOrderTruck = trucks.size() - numberOfFreeTruck;

            truckRestDTO.setNumberOfTruckNow(numberOfTruckNow);
            truckRestDTO.setNumberOfFreeTruck(numberOfFreeTruck);
            truckRestDTO.setNumberOfInOrderTruck(numberOfInOrderTruck);
            truckRestDTO.setNumberOfBrokenTruck(numberOfBrokenTruck);
        }

        return truckRestDTO;
    }
}
