package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;

import java.util.List;

public interface WaypointConverter {
    Waypoint convertWaypointDTOToWaypoint(WaypointDTO waypoint);

    WaypointDTO convertWaypointToWaypointDTO(Waypoint waypoint);

    List<Waypoint> convertWaypointDTOListToWaypointList(List<WaypointDTO> waypoints);

    List<WaypointDTO> convertWaypointListToWaypointDTOList(List<Waypoint> waypoints);
}
