package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.usachev.LogiWebProject.dto.DisplayDTO;

public class RestServiceImpl implements RestService {

    private DriverService driverService = new DriverServiceImpl();

    private TruckService truckService = new TruckServiceImpl();

    private OrderService orderService = new OrderServiceImpl();

    @Override
    public DisplayDTO getDisplayDTO() {
        DisplayDTO displayDTO = new DisplayDTO();
        displayDTO.setDriverDTOList(driverService.getAllDrivers());
        displayDTO.setTruckDTOList(truckService.getAllTrucks());
        displayDTO.setOrderDTOList(orderService.getAllOrders());

        return displayDTO;
    }
}
