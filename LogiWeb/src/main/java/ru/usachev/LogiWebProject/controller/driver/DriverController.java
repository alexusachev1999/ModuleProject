package ru.usachev.LogiWebProject.controller.driver;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.service.api.CargoService;
import ru.usachev.LogiWebProject.service.api.DriverService;
import ru.usachev.LogiWebProject.service.api.OrderService;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {

    private static Logger logger = Logger.getLogger(DriverController.class);

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CargoService cargoService;

    private String username;
    private DriverDTO driverDTO;

    @GetMapping("/")
    public String showDriverMenu(Model model){
        /* Getting driver by username which getting from security form
         * Set into private static object which will use in different
         * methods of this controller*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        username = userDetail.getUsername();
        driverDTO = driverService.getDriverByUsername(username);

        /* Get driver list by this username */
        List<DriverDTO> drivers = driverService.getCoDriverListByUsername(username);

        if (drivers != null)
            drivers.removeIf(driver -> driver.getUser().equals(username));

        /* Get order for this driver*/
        OrderDTO orderDTO = orderService.getOrderByUsername(username);

        /* Flags for JSP <c:if test = "isFlag"> */
        boolean isDriverListEmpty;
        if (drivers != null) {
            isDriverListEmpty = drivers.isEmpty();
        } else
            isDriverListEmpty = false;


        boolean isDriverHasOrder = !(orderDTO == null);
        boolean isOrderCompleted = true;

        List<WaypointDTO> waypoints;

        if (orderDTO != null) {
            waypoints = orderDTO.getWaypoints();
            for (WaypointDTO waypointDTO : waypoints) {
                Cargo cargo = cargoService.getCargoByName(waypointDTO.getCargo());
                if (!cargo.getStatus().equals("Доставлен"))
                    isOrderCompleted = false;
            }
        }

        model.addAttribute("isDriverHasOrder", isDriverHasOrder);
        model.addAttribute("isDriverListEmpty", isDriverListEmpty);
        model.addAttribute("isOrderCompleted", isOrderCompleted);
        model.addAttribute("driver", driverDTO);
        model.addAttribute("drivers", drivers);
        model.addAttribute("order", orderDTO);
        return "driver/driver-main";
    }

    @GetMapping("/changeWorkTimeStatus")
    public String changeWorkTimeStatus(Model model){
        model.addAttribute("driver", driverDTO);
        return "driver/change-work-time-status";
    }

    @PostMapping("/saveNewWorkTimeStatus")
    public String saveNewWorkTimeStatus(@ModelAttribute(name = "driver") DriverDTO driver){
        logger.info("Driver: " + driver.getId() + " " + driver.getUser() + " tries to " +
                "change work time status");
        driverService.updateDriver(driver);
        return "redirect:/driver/";
    }

    @GetMapping("/changeWorkType")
    public String changeWorkType(Model model){
        model.addAttribute("driver", driverDTO);
        return "driver/change-work-type";
    }

    @PostMapping("/saveNewWorkType")
    public String saveNewWorkType(@ModelAttribute(name = "driver") DriverDTO driver){
        logger.info("Driver: " + driver.getId() + " " + driver.getUser() + " tries to " +
                "change work type");

        driverService.updateDriver(driver);
        return "redirect:/driver/";
    }

    @GetMapping("/changeCargoStatus")
    public String changeCargoStatus(@RequestParam (name = "waypointId") int waypointId
            , Model model){
        CargoDTO cargoDTO = cargoService.getCargoByWaypointId(waypointId);
        model.addAttribute("cargo", cargoDTO);
        return "driver/change-cargo-status";
    }

    @PostMapping("/saveNewCargoStatus")
    public String saveNewCargoStatus(@ModelAttribute(name = "cargo") CargoDTO cargoDTO){
        logger.info("Driver tries to change cargo status");
        cargoService.saveCargo(cargoDTO);
        return "redirect:/driver/";
    }

    @RequestMapping("/orderComplete")
    public String orderComplete(@RequestParam(name = "orderId") int id){
        orderService.orderComplete(id);
        return "redirect:/driver/";
    }

}
