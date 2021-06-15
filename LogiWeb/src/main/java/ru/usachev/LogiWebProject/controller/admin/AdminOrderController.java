package ru.usachev.LogiWebProject.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.usachev.LogiWebProject.businessCalculating.BusinessCalculating;
import ru.usachev.LogiWebProject.converter.DriverConverter;
import ru.usachev.LogiWebProject.converter.OrderConverter;
import ru.usachev.LogiWebProject.converter.TruckConverter;
import ru.usachev.LogiWebProject.converter.WaypointConverter;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.entity.Truck;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private WaypointService waypointService;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private DriverConverter driverConverter;

    @Autowired
    private TruckConverter truckConverter;

    @Autowired
    private WaypointConverter waypointConverter;


    private OrderDTO orderDTOInMemory = new OrderDTO();

    private int timeOfOrderExecution;



    @GetMapping("/orders")
    public String getAllOrders(Model model){
        List<OrderDTO> orders = orderService.getAllOrders();
        orders = orderService.getAllCompletedAndUncompletedOrders(orders);

        model.addAttribute("orders", orders);

        return "admin/all-orders";
    }

    @GetMapping("/addOrder")
    public String addOrder(Model model){
        int randomInt = (int) (Math.random() * 1000);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(false);
        orderDTO.setNumber(randomInt);

        orderDTOInMemory.setStatus(false);
        orderDTOInMemory.setNumber(randomInt);

        List<WaypointDTO> waypoints = waypointService.getAllFreeWaypoints();

        if (waypoints.isEmpty()){
            return "admin/no-waypoints";
        }

        orderDTO.setWaypoints(waypoints);
        model.addAttribute("waypoints", waypoints);
        model.addAttribute("order", orderDTO);
        return "admin/add-order";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@RequestParam("waypoints") List<Integer> cargoIds){

        List<WaypointDTO> waypointsDTO = waypointService.getWaypointListByIds(cargoIds);

        orderDTOInMemory.setWaypoints(waypointsDTO);

        return "redirect:/admin/order/addTruck";

    }

    @RequestMapping("/updateOrder")
    public String updateOrder(@RequestParam("orderId") int id, Model model){
        OrderDTO order = orderConverter.convertOrderToOrderDTO(orderService.getOrder(id));
        model.addAttribute("cityList", cityService.getCities());
        model.addAttribute("order", order);
        return "admin/add-order";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder(@RequestParam(name = "orderId") int id){
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/order/addTruck")
    public String addOrderTruck(Model model){
        List<TruckDTO> trucks = truckService.getValidTrucksForOrder(orderDTOInMemory.getWaypoints());

        if (trucks.isEmpty())
            return "admin/no-truck";

        model.addAttribute("order", orderDTOInMemory);
        model.addAttribute("trucks", trucks);
        return "admin/order-add-truck";
    }

    @PostMapping("/order/saveTruck")
    public String saveOrderTruck(@RequestParam("truck") String truckNumber,
                                 RedirectAttributes redirectAttributes){

        orderDTOInMemory.setTruck(truckNumber);
        redirectAttributes.addFlashAttribute("order", orderDTOInMemory);

        orderService.saveOrder(orderDTOInMemory);

        Order order = orderService.getOrderByNumber(orderDTOInMemory.getNumber());
        orderDTOInMemory.setId(order.getId());

        List<Waypoint> waypoints = waypointConverter
                .convertWaypointDTOListToWaypointList(orderDTOInMemory.getWaypoints());

        for (Waypoint waypoint: waypoints){
            waypoint.setOrder(orderConverter.convertOrderDTOToOrder(orderDTOInMemory));
            waypointService.saveWaypointEntity(waypoint);
        }


        return "redirect:/admin/order/addDrivers";
    }

    @GetMapping("/order/addDrivers")
    public String addDriversToOrder(Model model){
        List<DriverDTO> drivers = driverService.getValidDriversByOrderId(orderDTOInMemory.getId());

        /* We are get the time of execution, set it to this class field. And set zero to avoid bags*/
        timeOfOrderExecution = drivers.get(0).getTimeForOrderExecution();


        if (drivers.isEmpty()) {
            orderService.deleteOrder(orderDTOInMemory.getId());
            orderDTOInMemory = new OrderDTO();
            return "admin/no-driver";
        }

        model.addAttribute("order", orderDTOInMemory);
        model.addAttribute("drivers", drivers);
        return "admin/add-driver-to-order";
    }


    @PostMapping("/order/saveDrivers")
    public String saveDriversToOrder(@RequestParam("drivers") List<Integer> driverIds){
        List<Driver> drivers = driverService.getDriverListByIds(driverIds);
        for (Driver driver: drivers){
            driver.setTimeForOrderExecution(0);
        }

        TruckDTO truckDTO = truckService.getTruckByOrderNumber(orderDTOInMemory.getNumber());

        Truck truck = truckConverter.convertTruckDTOToTruck(truckDTO);

        Order order = orderConverter.convertOrderDTOToOrder(orderDTOInMemory);

        for (Driver driver: drivers){
            driver.setTruck(truck);
            driver.setOrder(order);
            driver.setTimeForOrderExecution(timeOfOrderExecution);

            driverService.saveEntityDriver(driver);
        }


        List<DriverDTO> driversDTO = driverConverter.convertDriverListToDriverDTOList(drivers);
        orderDTOInMemory.setDrivers(driversDTO);

        orderService.saveOrder(orderDTOInMemory);

        orderDTOInMemory = new OrderDTO();

        return "redirect:/admin/orders";
    }
}
