package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.Order;

import java.util.List;

public interface OrderConverter {
    Order convertOrderDTOToOrder(OrderDTO orderDTO);
    OrderDTO convertOrderToOrderDTO(Order order);

    List<OrderDTO> convertOrderListToOrderDTOList(List<Order> orders);
}
