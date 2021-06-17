package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.WaypointConverter;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.api.CargoService;
import ru.usachev.LogiWebProject.service.api.CityService;

import java.util.ArrayList;
import java.util.List;


@Component
public class WaypointConverterImpl implements WaypointConverter {

    @Autowired
    private CityService cityService;

    @Autowired
    private CargoService cargoService;

    public Waypoint convertWaypointDTOToWaypoint(WaypointDTO waypointDTO) {
        Waypoint convertedWaypoint = new Waypoint();

        convertedWaypoint.setId(waypointDTO.getId());
        convertedWaypoint.setCityLoading(cityService.getCityByName(waypointDTO.getCityLoading()));
        convertedWaypoint.setCityUnloading(cityService.getCityByName(waypointDTO.getCityUnloading()));
        convertedWaypoint.setCargo(cargoService.getCargoByName(waypointDTO.getCargo()));

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
