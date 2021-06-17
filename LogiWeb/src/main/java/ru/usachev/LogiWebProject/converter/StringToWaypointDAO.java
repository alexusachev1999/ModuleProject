package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.WaypointConverter;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.api.WaypointService;

@Component
public class StringToWaypointDAO implements Converter<String, WaypointDTO> {

    @Autowired
    private WaypointService waypointService;

    @Autowired
    private WaypointConverter waypointConverter;

    @Override
    public WaypointDTO convert(String s) {
        Waypoint waypoint = waypointService.getWaypoint(Integer.getInteger(s));
        WaypointDTO waypointDTO = waypointConverter.convertWaypointToWaypointDTO(waypoint);
        return waypointDTO;
    }
}
