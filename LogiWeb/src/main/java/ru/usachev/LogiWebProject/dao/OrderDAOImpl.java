package ru.usachev.LogiWebProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.*;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO{

    private static Logger logger = Logger.getLogger(OrderDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> orders = session.createQuery("from Order").getResultList();

        try {
            return orders;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Order orderFromDB;

        if (order.getId() != 0){
            orderFromDB = session.get(Order.class, order.getId());

            orderFromDB.setDrivers(order.getDrivers());
            orderFromDB.setTruck(order.getTruck());
            orderFromDB.setWaypoints(order.getWaypoints());

            session.saveOrUpdate(orderFromDB);
            logger.info("update order №: " + order.getNumber());
        } else {
            session.saveOrUpdate(order);
            logger.info("save new order №: " + order.getNumber());
        }

    }

    @Override
    public Order getOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, id);

        try {
            return order;
        } catch (NoResultException e){
            logger.info("no order №: " + order.getNumber() + " in DB");
            return null;
        }
    }

    @Override
    public void deleteOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, id);

        // To exclude constraint exception - set order_id null for waypoints which have this order
        List<Waypoint> waypoints = order.getWaypoints();
        for (Waypoint waypoint: waypoints){
            waypoint.setOrder(null);
        }

        List<Driver> drivers = order.getDrivers();
        for (Driver driver: drivers){
            driver.setOrder(null);
            driver.setTruck(null);
        }
        session.delete(order);
        logger.info("delete order №: " + order.getNumber());
    }

    @Override
    public Order getOrderByNumber(int number) {
        Session session = sessionFactory.getCurrentSession();
        Order orderByName = new Order();
        orderByName = session.createQuery("from Order where number=:number", Order.class)
                .setParameter("number", number)
                .getSingleResult();

        try {
            return orderByName;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Order getOrderByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        List drivers = session.createQuery("from Driver where user.username=:username")
                .setParameter("username", username)
                .getResultList();

        if (drivers != null) {
            Driver driver = (Driver) drivers.get(0);
            if (driver.getTruck() != null) {
                Order order = driver.getTruck().getOrder();
                return order;
            } else
                return null;
        } else
            return null;
    }

    @Override
    public void saveDriversToOrder(List<Driver> drivers, Order order) {
        Session session = sessionFactory.getCurrentSession();

        session.update(order);
        logger.info("update order №: " + order.getNumber());

        for (Driver driver: drivers){
            session.saveOrUpdate(driver);
            logger.info("set order №: " + order.getNumber() + " to driver: " + driver.getId() + " "
            + driver.getName() + " " + driver.getSurname());
        }
    }

    @Override
    public void orderComplete(int orderId) {
        Session session = sessionFactory.getCurrentSession();

        Order orderFromDB = session.get(Order.class, orderId);

        List<Driver> drivers = orderFromDB.getDrivers();


        for (Driver driver: drivers){
            driver.setOrder(null);
            driver.setWorkType(false);
            driver.setStatus("Отдых");
            driver.setTruck(null);

            /* Set driver his worked hours for this order*/
            int workedHoursInThisMonth = driver.getWorkedHours();
            int workedHoursOnThisOrder = driver.getTimeForOrderExecution();
            driver.setWorkedHours(workedHoursInThisMonth + workedHoursOnThisOrder);
            driver.setTimeForOrderExecution(0);
            session.saveOrUpdate(driver);
        }

        Truck truck = orderFromDB.getTruck();
        truck.setOrder(null);
        session.saveOrUpdate(truck);


        orderFromDB.setDrivers(null);
        orderFromDB.setTruck(null);
        orderFromDB.setStatus(true);

        session.saveOrUpdate(orderFromDB);

        /* Block of native query which save info about order. This data will be use in restService
        * for getting information about drivers and truck for completed order */
        for (Driver driver : drivers){
            session.createNativeQuery("INSERT INTO completed_orders (number, driver_id, truck_id) VALUES (?,?,?)")
                    .setParameter(1, orderFromDB.getId())
                    .setParameter(2, driver.getId())
                    .setParameter(3, truck.getId())
                    .executeUpdate();
        }
    }
}
