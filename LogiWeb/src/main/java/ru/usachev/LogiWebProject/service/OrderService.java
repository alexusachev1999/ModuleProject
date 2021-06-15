package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    void saveOrder(OrderDTO order);

    Order getOrder(int id);

    void deleteOrder(int id);

    Order getOrderByNumber(int number);

    OrderDTO getOrderByUsername(String username);

    void saveDriversToOrder(List<Driver> drivers, OrderDTO orderDTO);

    void orderComplete(int orderId);

    List<OrderDTO> getAllCompletedAndUncompletedOrders(List<OrderDTO> orders);
}
