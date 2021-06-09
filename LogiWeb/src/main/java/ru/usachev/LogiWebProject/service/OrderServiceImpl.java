package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.converter.OrderConverter;
import ru.usachev.LogiWebProject.dao.OrderDAO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderConverter orderConverter;

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        List<OrderDTO> convertedOrders = orderConverter.convertOrderListToOrderDTOList(orders);

        return convertedOrders;
    }

    @Override
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        Order order = orderConverter.convertOrderDTOToOrder(orderDTO);
        orderDAO.saveOrder(order);
    }

    @Override
    @Transactional
    public Order getOrder(int id) {
        return orderDAO.getOrder(id);
    }

    @Override
    @Transactional
    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }

    @Override
    @Transactional
    public Order getOrderByNumber(int number) {
        return orderDAO.getOrderByNumber(number);
    }

    @Override
    @Transactional
    public OrderDTO getOrderByUsername(String username) {
        Order order = orderDAO.getOrderByUsername(username);
        OrderDTO orderDTO = orderConverter.convertOrderToOrderDTO(order);
        return orderDTO;
    }

    @Override
    @Transactional
    public void saveDriversToOrder(List<Driver> drivers, OrderDTO orderDTO) {
        Order order = orderConverter.convertOrderDTOToOrder(orderDTO);
        for (Driver driver: drivers){
            driver.setOrder(order);
            driver.setTruck(order.getTruck());
        }
        orderDAO.saveDriversToOrder(drivers, order);
    }

    @Override
    @Transactional
    public void orderComplete(int orderId) {
        orderDAO.orderComplete(orderId);
    }
}
