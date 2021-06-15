package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.restDTO.DisplayDTO;
import ru.usachev.LogiWebProject.entity.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private OrderService orderService;

    @Override
    public DisplayDTO getDisplayDTO() {
        DisplayDTO displayDTO = new DisplayDTO();

        /* If order is completed, checks data from table completed_order and set to this displayDTO*/
        List<OrderDTO> orders = orderService.getAllOrders();

        /* Reverse list to get last 10 orders*/
        Collections.reverse(orders);

        orderService.getAllCompletedAndUncompletedOrders(orders);


        displayDTO.setOrderDTOList(orders);
        displayDTO.setDriverRestDTO(driverService.getDriverRestDTO());
        displayDTO.setTruckRestDTO(truckService.getTruckRestDTO());
        return displayDTO;
    }
}
