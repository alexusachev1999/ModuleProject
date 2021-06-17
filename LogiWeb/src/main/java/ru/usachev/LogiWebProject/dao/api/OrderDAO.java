package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;

import java.util.List;

/**
 * DAO for operations for table 'orders' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface OrderDAO {

    /**
     * Getting all orders from DB
     * @return List
     */
    List<Order> getAllOrders();

    /**
     * Saving order to DB
     * @param order
     */
    void saveOrder(Order order);

    /**
     * Getting order by id from DB
     * @param id
     * @return Order
     */
    Order getOrder(int id);

    /**
     * Deleting order by id from DB
     * @param id
     */
    void deleteOrder(int id);

    /**
     * Getting order by number from DB
     * @param number
     * @return Order
     */
    Order getOrderByNumber(int number);

    /**
     * Getting order for driver by his username
     * @param username
     * @return Order
     */
    Order getOrderByUsername(String username);

    /**
     * Saving drivers to new order and setting order for all these drivers
     * @param drivers
     * @param order
     */
    void saveDriversToOrder(List<Driver> drivers, Order order);

    /**
     * Setting for all drivers and truck of this order null and save this information
     * into utility table 'completed_orders'
     * This method uses for driver view
     * @param orderId
     */
    void orderComplete(int orderId);
}
