package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.DriverConverter;
import ru.usachev.LogiWebProject.converter.api.OrderConverter;
import ru.usachev.LogiWebProject.converter.api.WaypointConverter;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.service.TruckService;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverterImpl implements OrderConverter {

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverConverter driverConverter;

    @Autowired
    private WaypointConverter waypointConverter;


    @Override
    public Order convertOrderDTOToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setNumber(orderDTO.getNumber());
        order.setStatus(orderDTO.isStatus());

        if (orderDTO.getTruck() != null)
            order.setTruck(truckService.getTruckByRegistrationNumber(orderDTO.getTruck()));

        if (orderDTO.getDrivers() != null)
            order.setDrivers(driverConverter.convertDriverDTOListToDriverList(orderDTO.getDrivers()));

        if (orderDTO.getWaypoints() != null)
            order.setWaypoints(waypointConverter.convertWaypointDTOListToWaypointList(orderDTO.getWaypoints()));

        return order;
    }

    @Override
    public OrderDTO convertOrderToOrderDTO(Order order) {

        if (order == null)
            return null;

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setNumber(order.getNumber());
        orderDTO.setStatus(order.isStatus());

        if (order.getTruck() != null)
            orderDTO.setTruck(order.getTruck().getRegistrationNumber());

        if (order.getDrivers() != null)
            orderDTO.setDrivers(driverConverter.convertDriverListToDriverDTOList(order.getDrivers()));

        if (order.getWaypoints() != null)
            orderDTO.setWaypoints(waypointConverter.convertWaypointListToWaypointDTOList(order.getWaypoints()));

        return orderDTO;
    }

    @Override
    public List<OrderDTO> convertOrderListToOrderDTOList(List<Order> orders) {
        List<OrderDTO> ordersDTO = new ArrayList<>();
        for (Order order: orders){
            ordersDTO.add(convertOrderToOrderDTO(order));
        }
        return ordersDTO;
    }
}
