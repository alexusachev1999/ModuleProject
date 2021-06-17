package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.aop.UpdateAnnotation;
import ru.usachev.LogiWebProject.converter.api.OrderConverter;
import ru.usachev.LogiWebProject.dao.api.OrderDAO;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.service.api.DriverService;
import ru.usachev.LogiWebProject.service.api.OrderService;
import ru.usachev.LogiWebProject.service.api.TruckService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckService truckService;

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        List<OrderDTO> convertedOrders = orderConverter.convertOrderListToOrderDTOList(orders);

        return convertedOrders;
    }

    @Override
    @Transactional
    @UpdateAnnotation
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
    @UpdateAnnotation
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
    @UpdateAnnotation
    public void orderComplete(int orderId) {
        orderDAO.orderComplete(orderId);
    }

    @Override
    public List<OrderDTO> getAllCompletedAndUncompletedOrders(List<OrderDTO> orders) {
        for (OrderDTO orderDTO : orders) {
            if (orderDTO.getDrivers().isEmpty()) {
                List<DriverDTO> driverDTOList = driverService
                        .getDriversDTOForCompletedOrderByOrderId(orderDTO.getId());
                TruckDTO truckDTO = truckService.getTruckByOrderId(orderDTO.getId());
                orderDTO.setTruck(truckDTO.getRegistrationNumber());
                orderDTO.setDrivers(driverDTOList);
            }
        }
        return orders;
    }
}
