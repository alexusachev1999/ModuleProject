package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;

import java.util.List;

public interface WaypointService {
    List<WaypointDTO> getAllWaypoints();

    void saveWaypoint(WaypointDTO waypoint);

    Waypoint getWaypoint(int id);

    void deleteWaypoint(int id);

    WaypointDTO getWaypointByCargoName(String waypointToString);

    List getWaypointListByIds(List<Integer> ids);

    List<WaypointDTO> getAllFreeWaypoints();

    void saveWaypointEntity(Waypoint waypoint);

    void deleteWaypointByCargoId(int id);
}
