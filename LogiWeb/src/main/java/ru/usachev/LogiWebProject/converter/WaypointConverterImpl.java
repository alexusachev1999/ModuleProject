package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.CargoService;
import ru.usachev.LogiWebProject.service.CityService;

import java.util.ArrayList;
import java.util.List;


@Component
public class WaypointConverterImpl implements WaypointConverter{

    @Autowired
    private CityService cityService;

    @Autowired
    private CargoService cargoService;

    public Waypoint convertWaypointDTOToWaypoint(WaypointDTO waypoint) {
        Waypoint convertedWaypoint = new Waypoint();

        convertedWaypoint.setId(waypoint.getId());
        convertedWaypoint.setCityLoading(cityService.getCityByName(waypoint.getCityLoading()));
        convertedWaypoint.setCityUnloading(cityService.getCityByName(waypoint.getCityUnloading()));
        convertedWaypoint.setCargo(cargoService.getCargoByName(waypoint.getCargo()));

        return convertedWaypoint;
    }

    @Override
    public WaypointDTO convertWaypointToWaypointDTO(Waypoint waypoint) {
        WaypointDTO waypointDTO = new WaypointDTO();

        waypointDTO.setId(waypoint.getId());
        waypointDTO.setCityLoading(waypoint.getCityLoading().getName());
        waypointDTO.setCityUnloading(waypoint.getCityUnloading().getName());
        waypointDTO.setCargo(waypoint.getCargo().getName());
        waypointDTO.setCargoStatus(waypoint.getCargo().getStatus());

        return waypointDTO;
    }

    @Override
    public List<Waypoint> convertWaypointDTOListToWaypointList(List<WaypointDTO> waypointsDTO) {
        List<Waypoint> waypoints = new ArrayList<>();
        for(WaypointDTO waypointDTO: waypointsDTO){
            waypoints.add(convertWaypointDTOToWaypoint(waypointDTO));
        }
        return waypoints;
    }

    @Override
    public List<WaypointDTO> convertWaypointListToWaypointDTOList(List<Waypoint> waypoints) {
        List<WaypointDTO> waypointsDTO = new ArrayList<>();
        for (Waypoint waypoint: waypoints){
            waypointsDTO.add(convertWaypointToWaypointDTO(waypoint));
        }
        return waypointsDTO;
    }
}
