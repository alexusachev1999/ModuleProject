package ru.usachev.LogiWebProject.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.usachev.LogiWebProject.converter.CargoConverter;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.service.CargoService;
import ru.usachev.LogiWebProject.service.WaypointService;
import ru.usachev.LogiWebProject.validation.CargoValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCargoController {

    private static Logger logger = Logger.getLogger(AdminCargoController.class);

    @Autowired
    private CargoService cargoService;

    @Autowired
    private WaypointService waypointService;

    @Autowired
    private CargoConverter cargoConverter;

    @Autowired
    private CargoValidator cargoValidator;

    @RequestMapping("/cargoes")
    public String getAllCargoes(Model model){
        List<Cargo> cargoes = cargoService.getAllCargoes();
        model.addAttribute("cargoes", cargoes);
        return "admin/all-cargoes";
    }

    @GetMapping("/addCargo")
    public String addCargo(Model model){
        CargoDTO cargo = new CargoDTO();
        cargo.setNumber((int) (Math.random() * 1000));
        cargo.setStatus("Подготовлен");
        model.addAttribute("cargo", cargo);
        model.addAttribute("uniqueErrorMsg", "no error");
        return "admin/add-cargo";
    }

    @PostMapping("/saveCargo")
    public String saveCargo(@Valid @ModelAttribute("cargo") CargoDTO cargo, BindingResult bindingResult
            , Model model, RedirectAttributes redirectAttributes){
        boolean isValidCargo = cargoValidator.validCargo(cargo);

        if (bindingResult.hasErrors() || !isValidCargo){

            if (!isValidCargo)
                model.addAttribute("uniqueErrorMsg", "Груз с таким " +
                        "именем уже существует");

            cargo.setNumber((int) (Math.random() * 1000));
            cargo.setStatus("Подготовлен");
            model.addAttribute("cargo", cargo);
            return "admin/add-cargo";}
        else {
            redirectAttributes.addFlashAttribute("cargo", cargo);
            cargoService.saveCargo(cargo);


            if (cargo.getId() == 0){
                return "redirect:/admin/addWaypoint";
            }
            else{
                return "redirect:/admin/cargoes";
            }
        }
    }

    @RequestMapping("/updateCargo")
    public String updateCargo(@RequestParam("cargoId") int id, Model model){
        CargoDTO cargo = cargoConverter.convertCargoToCargoDTO(cargoService.getCargo(id));
        model.addAttribute("cargo", cargo);
        logger.info("Want to update cargo: " + cargo.getName());
        return "admin/add-cargo";
    }

    @RequestMapping("/deleteCargo")
    public String deleteCargo(@RequestParam(name = "cargoId") int id){
        waypointService.deleteWaypointByCargoId(id);
        return "redirect:/admin/cargoes";
    }
}
