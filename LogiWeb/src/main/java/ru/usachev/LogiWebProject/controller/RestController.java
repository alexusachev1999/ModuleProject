package ru.usachev.LogiWebProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.restDTO.DisplayDTO;
import ru.usachev.LogiWebProject.service.DriverService;
import ru.usachev.LogiWebProject.service.OrderService;
import ru.usachev.LogiWebProject.service.RestService;
import ru.usachev.LogiWebProject.service.TruckService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    private RestService restService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public DisplayDTO getDisplayDTO(){
        return restService.getDisplayDTO();
    }

    @GetMapping("/drivers")
    public List<DriverDTO> getDrivers(){
        return driverService.getAllDrivers();
    }

    @GetMapping("/trucks")
    public List<TruckDTO> getTrucks(){
        return truckService.getAllTrucks();
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders(){
        return orderService.getAllOrders();
    }
}
