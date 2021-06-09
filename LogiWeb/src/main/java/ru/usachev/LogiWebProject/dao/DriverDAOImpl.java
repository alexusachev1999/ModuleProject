package ru.usachev.LogiWebProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.businessCalculating.BusinessCalculating;
import ru.usachev.LogiWebProject.controller.admin.AdminDriverController;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.City;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.entity.Waypoint;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DriverDAOImpl implements DriverDAO{
    private static Logger logger = Logger.getLogger(DriverDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BusinessCalculating businessCalculating;

    @Override
    public List<Driver> getAllDrivers() {
        Session session = sessionFactory.getCurrentSession();
        List<Driver> drivers = session.createQuery("from Driver", Driver.class).getResultList();

        try {
            return drivers;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void saveDriver(Driver driver) {
        Session session = sessionFactory.getCurrentSession();

        if (driver.getId() == 0){

        Query query = session.createQuery("from City where name=:cityName ");
        query.setParameter("cityName", driver.getCity().getName());

        City city = (City) query.getSingleResult();
        city.addDriverToDriverList(driver);
        session.saveOrUpdate(driver);
        logger.info("save new driver" + driver.getName() + " " + driver.getSurname());
        } else {
            Driver driverFromDB = session.get(Driver.class, driver.getId());
            driverFromDB.setName(driver.getName());
            driverFromDB.setSurname(driver.getSurname());
            driverFromDB.setStatus(driver.getStatus());
            driverFromDB.setWorkType(driver.isWorkType());

            if (driver.getTruck() != null)
                driverFromDB.setTruck(driver.getTruck());

            if (driver.getOrder() != null)
            driverFromDB.setOrder(driver.getOrder());

            driverFromDB.setCity(driver.getCity());

            driverFromDB.setPhoneNumber(driver.getPhoneNumber());

            driverFromDB.setUser(driver.getUser());

            session.saveOrUpdate(driverFromDB);
            logger.info("update driver" + driver.getName() + " " + driver.getSurname());
        }

    }

    @Override
    public Driver getDriver(int id) {
        Session session = sessionFactory.getCurrentSession();
        Driver driver = session.get(Driver.class, id);
        return driver;
    }

    @Override
    public void deleteDriver(int id) {
        Session session = sessionFactory.getCurrentSession();
        Driver driver = session.get(Driver.class, id);
        session.delete(driver);
        logger.info("delete driver" + driver.getName() + " " + driver.getSurname());

    }

    @Override
    public List<Driver> getDriversByOrderId(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        List<Driver> drivers = null;
        drivers = session.createQuery("from Driver where order.id=:orderId")
                .setParameter("orderId", orderId)
                .getResultList();

        try {
            return drivers;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Driver> getValidDriversByOrderId(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);

        City cityOfTruck = order.getTruck().getCity();

        int timeOfOrderExecution = businessCalculating.calculateApproximateTimeOfOrderExecution(orderId);

        int workedHours = businessCalculating.calculateDriverWorkedHoursLimitForOrderByOrderId(orderId);

        Query query = session.createQuery("from Driver where order.id = null ");

        List<Driver> drivers = query.getResultList();


        if (drivers != null){
            drivers.removeIf(driver -> !driver.getCity().equals(cityOfTruck));

            drivers.removeIf(driver -> driver.getWorkedHours() > workedHours);

            for (Driver driver: drivers){
                driver.setTimeForOrderExecution(timeOfOrderExecution);
            }

            return drivers;
        } else
            return null;
    }

    @Override
    public Driver getDriverByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        Driver driver = (Driver) session.createQuery("from Driver where user.username=:username")
                .setParameter("username", username)
                .getSingleResult();
        return driver;
    }

    @Override
    public List<Driver> getCoDriverListByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        Driver driver = (Driver) session.createQuery("from Driver where user.username=:username")
                .setParameter("username", username)
                .getSingleResult();

        if (driver.getOrder() != null) {
            int orderId = driver.getOrder().getId();

        List<Driver> driverList = session.createQuery("from Driver where order.id=:orderId")
                .setParameter("orderId", orderId)
                .getResultList();

        return driverList;}

        else
            return null;
    }

    @Override
    public List<Driver> getDriverListByIds(List<Integer> driverIds) {
        Session session = sessionFactory.getCurrentSession();

        List<Driver> drivers = new ArrayList<>();

        for (Integer id: driverIds){
            Driver driver = session.get(Driver.class, id);
            drivers.add(driver);
        }

        if (drivers != null)
            return drivers;
        else
            return null;
    }

    @Override
    public void saveEntityDriver(Driver driver) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(driver);
        logger.info("save new driver" + driver.getName() + " " + driver.getSurname());
    }

    @Override
    public void updateDriver(DriverDTO driver) {
        Session session = sessionFactory.getCurrentSession();

        Driver driverFromDB = session.get(Driver.class, driver.getId());

        driverFromDB.setStatus(driver.getStatus());
        driverFromDB.setWorkType(driver.isWorkType());

        session.update(driverFromDB);
        logger.info("update driver" + driver.getName() + " " + driver.getSurname());

    }

    @Override
    public Driver getDriverByPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();

        List<Driver> drivers = (List<Driver>) session.createQuery("from Driver where phoneNumber=:phoneNumber")
                    .setParameter("phoneNumber", phoneNumber)
                    .getResultList();

        if (drivers.isEmpty())
            return null;
        else
            return drivers.get(0);
    }
}
