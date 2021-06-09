package ru.usachev.LogiWebProject.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.usachev.LogiWebProject.converter.WaypointConverter;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.entity.City;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.CargoService;
import ru.usachev.LogiWebProject.service.CityService;
import ru.usachev.LogiWebProject.service.WaypointService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminWaypointController {
    @Autowired
    private WaypointService waypointService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CityService cityService;

    @Autowired
    private WaypointConverter waypointConverter;

    @RequestMapping("/waypoints")
    public String getAllCargoes(Model model){
        List<WaypointDTO> waypoints = waypointService.getAllWaypoints();
        model.addAttribute("waypoints", waypoints);
        return "admin/all-waypoints";
    }

    @GetMapping("/addWaypoint")
    public String addCargo(@ModelAttribute(name = "cargo") CargoDTO cargo, Model model){
        WaypointDTO waypoint = new WaypointDTO();
        waypoint.setCargo(cargo.getName());
        List<City> cities = cityService.getCities();
        model.addAttribute("waypoint", waypoint);
        model.addAttribute("cities", cities);
        return "admin/add-waypoint";
    }

    @PostMapping("/saveWaypoint")
    public String saveWaypoint(@Valid @ModelAttribute("waypoint") WaypointDTO waypoint, BindingResult bindingResult
            , Model model){
        if (bindingResult.hasErrors()) {
            List<Cargo> cargoes = cargoService.getAllCargoes();
            List<City> cities = cityService.getCities();
            model.addAttribute("cargoes", cargoes);
            model.addAttribute("cities", cities);
            return "admin/add-waypoint";
        } else {
            waypointService.saveWaypoint(waypoint);
            return "redirect:/admin/waypoints";
        }
    }

    @RequestMapping("/updateWaypoint")
    public String updateWaypoint(@RequestParam("waypointId") int id, Model model){
        List<Cargo> cargoes = cargoService.getAllCargoes();
        List<City> cities = cityService.getCities();

        Waypoint waypoint = waypointService.getWaypoint(id);
        WaypointDTO waypointDTO = waypointConverter.convertWaypointToWaypointDTO(waypoint);

        model.addAttribute("cargoes", cargoes);
        model.addAttribute("cities", cities);
        model.addAttribute("waypoint", waypointDTO);
        return "admin/add-waypoint";
    }

    @RequestMapping("/deleteWaypoint")
    public String deleteWaypoint(@RequestParam(name = "waypointId") int id){
        waypointService.deleteWaypoint(id);
        return "redirect:/admin/waypoints";
    }

}
