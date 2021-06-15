package ru.usachev.LogiWebProject.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.usachev.LogiWebProject.converter.TruckConverter;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Truck;
import ru.usachev.LogiWebProject.service.CityService;
import ru.usachev.LogiWebProject.service.TruckService;
import ru.usachev.LogiWebProject.validation.TruckValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTruckController {

    private static Logger logger = Logger.getLogger(AdminTruckController.class);

    @Autowired
    private TruckService truckService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TruckConverter truckConverter;

    @Autowired
    private TruckValidator truckValidator;

    @RequestMapping("/trucks")
    private String getAllTrucks(Model model){
        List<TruckDTO> trucks = truckService.getAllEnabledTrucks();
        model.addAttribute("trucks", trucks);
        return "/admin/all-trucks";
    }

    @GetMapping("/addTruck")
    public String addTruck(Model model){
        TruckDTO truck = new TruckDTO();
        model.addAttribute("truck", truck);
        model.addAttribute("cityList", cityService.getCities());
        model.addAttribute("uniqueError", "no error");
        return "admin/add-new-truck";
    }

    @PostMapping("/saveTruck")
    private String saveTruck(@Valid @ModelAttribute("truck") TruckDTO truck, BindingResult bindingResult
            , Model model){
        boolean isValidTruck = truckValidator.validTruck(truck);
        if (bindingResult.hasErrors() || !isValidTruck){
            if (!isValidTruck)
                model.addAttribute("uniqueError", "Фура с таким номером " +
                        "уже существует!");
            else
                model.addAttribute("uniqueError", "no error");
            model.addAttribute("cityList", cityService.getCities());
            return "admin/add-new-truck";}
        else {
            truckService.saveTruck(truck);
            return "redirect:/admin/trucks";
        }
    }

    @GetMapping("/updateTruck")
    private String updateTruck(@RequestParam("truckId") int id, Model model){
        TruckDTO truck = truckConverter.convertTruckToTruckDTO(truckService.getTruck(id));
        model.addAttribute("cityList", cityService.getCities());
        model.addAttribute("truck", truck);
        return "admin/update-truck";
    }

    @PostMapping("/updateTruck")
    private String updateTruck(@Valid @ModelAttribute("truck") TruckDTO truck, BindingResult bindingResult
            , Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("cityList", cityService.getCities());
            return "admin/add-new-truck";
        }
        else {
            truckService.saveTruck(truck);
            return "redirect:/admin/trucks";
        }
    }


    @RequestMapping("/deleteTruck")
    public String deleteDriver(@RequestParam(name = "truckId") int id){
        truckService.deleteTruck(id);

        return "redirect:/admin/trucks";
    }
}
