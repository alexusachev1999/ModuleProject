package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.Order;

import java.util.List;
/**
 * Class for converting Order to OrderDTO and vice versa
 * @author Alex Usachev
 */
public interface OrderConverter {
    /**
     * Converting orderDTO to order
     * @param orderDTO
     * @return Order
     */
    Order convertOrderDTOToOrder(OrderDTO orderDTO);

    /**
     * Converting cargoDTO to cargo
     * @param order
     * @return OrderDTO
     */
    OrderDTO convertOrderToOrderDTO(Order order);

    /**
     * Converting list of orders to list of ordersDTO
     * @param orders
     * @return List
     */
    List<OrderDTO> convertOrderListToOrderDTOList(List<Order> orders);
}
