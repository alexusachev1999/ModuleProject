package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;

import java.util.List;
/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface OrderService {
    List<OrderDTO> getAllOrders();

    void saveOrder(OrderDTO order);

    Order getOrder(int id);

    void deleteOrder(int id);

    Order getOrderByNumber(int number);

    OrderDTO getOrderByUsername(String username);

    void orderComplete(int orderId);

    List<OrderDTO> getAllCompletedAndUncompletedOrders(List<OrderDTO> orders);
}
