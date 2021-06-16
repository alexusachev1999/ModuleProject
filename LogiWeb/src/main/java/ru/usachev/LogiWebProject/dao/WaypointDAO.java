package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.entity.Waypoint;

import java.util.List;

public interface WaypointDAO {
    List<Waypoint> getAllWaypoints();

    void saveWaypoint(Waypoint waypoint);

    Waypoint getWaypoint(int id);

    void deleteWaypoint(int id);

    Waypoint getWaypointByCargoName(String waypointToString);

    List getWaypointListByIds(List<Integer> ids);

    void saveWaypointEntity(Waypoint waypoint);

    List<Waypoint> getAllFreeWaypoints();

    void deleteWaypointByCargoId(int id);
}
