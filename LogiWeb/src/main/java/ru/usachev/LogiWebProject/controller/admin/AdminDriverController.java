package ru.usachev.LogiWebProject.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.usachev.LogiWebProject.converter.DriverConverter;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.service.CityService;
import ru.usachev.LogiWebProject.service.DriverService;
import ru.usachev.LogiWebProject.service.UserService;
import ru.usachev.LogiWebProject.validation.DriverValidator;
import ru.usachev.LogiWebProject.validation.UserValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDriverController {

    private static Logger logger = Logger.getLogger(AdminDriverController.class);

    @Autowired
    private DriverService driverService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverConverter driverConverter;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private DriverValidator driverValidator;

    @RequestMapping("/")
    public String showAdminMenu(){
        return "admin/admin-main";
    }

    @RequestMapping("/drivers")
    public String getAllDrivers(Model model){
        List<DriverDTO> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "admin/all-drivers";
    }

    @GetMapping("/addUserForNewDriver")
    public String addUserDriver(Model model){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserRole("ROLE_DRIVER");
        userDTO.setEnabled(true);
        model.addAttribute("user", userDTO);
        model.addAttribute("uniqueError", "no error");
        return "admin/add-driver-user";
    }

    @PostMapping("/saveUserForNewDriver")
    public String saveUserForNewDriver(@Valid @ModelAttribute(name = "user") UserDTO userDTO,
                                BindingResult bindingResult, Model model){
        boolean isValidUser = userValidator.validUser(userDTO);
        if (bindingResult.hasErrors() || !isValidUser){
            if (!isValidUser)
                model.addAttribute("uniqueError", "Пользователь " +
                        "с таким именем существует!");
            else
                model.addAttribute("uniqueError", "no error");

            userDTO.setUserRole("ROLE_DRIVER");
            userDTO.setEnabled(true);
            model.addAttribute("user", userDTO);
            return "admin/add-driver-user";
        } else {
            userService.saveUser(userDTO);

            DriverDTO driver = new DriverDTO();
            driver.setUser(userDTO.getUsername());
            driver.setStatus("Отдых");
            model.addAttribute("driver", driver);
            model.addAttribute("cityList", cityService.getCities());
            model.addAttribute("uniqueDriverError", "no error");
            return "admin/add-driver";
        }
    }

    @PostMapping(value = "/saveDriver", produces = "text/plain;charset=UTF-8")
    public String saveDriver(@Valid @ModelAttribute("driver") DriverDTO driver, BindingResult bindingResult
    , Model model){
        boolean isValidDriver = driverValidator.validDriver(driver);
        if (bindingResult.hasErrors() || !isValidDriver){
            if (!isValidDriver)
                model.addAttribute("uniqueDriverError", "Водитель " +
                        "с таким номером уже существует!");
            else
                model.addAttribute("uniqueDriverError", "no error");

            model.addAttribute("driver", driver);
            model.addAttribute("cityList", cityService.getCities());
            return "admin/add-driver";}
        else {
            driverService.saveDriver(driver);
            return "redirect:/admin/drivers";
        }
    }

    @GetMapping("/updateDriver")
    public String updateDriver(@RequestParam("driverId") int id, Model model){
        DriverDTO driver = driverConverter.convertDriverToDriverDTO(driverService.getDriver(id));
        model.addAttribute("cityList", cityService.getCities());
        model.addAttribute("driver", driver);
        model.addAttribute("uniqueDriverError", "no error");

        return "admin/update-driver";
    }

    @PostMapping("/updateDriver")
    public String updateDriver(@Valid @ModelAttribute("driver") DriverDTO driver,
                               BindingResult bindingResult, Model model){
        Driver actualDriver = driverService.getDriver(driver.getId());
        boolean isValidDriver = driverValidator.validDriver(driver);
        if (bindingResult.hasErrors() || !isValidDriver && !driver.getPhoneNumber().equals(actualDriver.getPhoneNumber())){
            if (!isValidDriver)
                model.addAttribute("uniqueDriverError", "Водитель " +
                        "с таким номером уже существует!");
            else
                model.addAttribute("uniqueDriverError", "no error");

            model.addAttribute("cityList", cityService.getCities());
            model.addAttribute("driver", driver);
            return "admin/update-driver";
        } else {
            driverService.saveDriver(driver);
            return getAllDrivers(model);
        }
    }

    @RequestMapping("/deleteDriver")
    public String deleteDriver(@RequestParam(name = "driverId") int id){
        driverService.deleteDriver(id);
        return "redirect:/admin/drivers";
    }
}
