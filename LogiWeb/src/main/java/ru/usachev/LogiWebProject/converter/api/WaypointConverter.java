package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;

import java.util.List;
/**
 * Class for converting Waypoint to WaypointDTO and vice versa
 * @author Alex Usachev
 */
public interface WaypointConverter {
    /**
     * Converting waypointDTO to waypoint
     * @param waypointDTO
     * @return Waypoint
     */
    Waypoint convertWaypointDTOToWaypoint(WaypointDTO waypointDTO);

    /**
     * Converting waypoint to waypointDTO
     * @param waypoint
     * @return WaypointDTO
     */
    WaypointDTO convertWaypointToWaypointDTO(Waypoint waypoint);

    /**
     * Converting list of waypointDTO to list of waypoint
     * @param waypoints
     * @return List
     */
    List<Waypoint> convertWaypointDTOListToWaypointList(List<WaypointDTO> waypoints);

    /**
     * Converting list of waypoint to list of waypointDTO
     * @param waypoints
     * @return List
     */
    List<WaypointDTO> convertWaypointListToWaypointDTOList(List<Waypoint> waypoints);
}
