package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.entity.Waypoint;

import java.util.List;
/**
 * DAO for operations for table 'waypoints' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface WaypointDAO {
    /**
     * Getting all waypoints from DB
     * @return List
     */
    List<Waypoint> getAllWaypoints();

    /**
     * Saving new waypoint to DB
     * @param waypoint
     */
    void saveWaypoint(Waypoint waypoint);

    /**
     * Getting waypoint from DB by waypointId
     * @param id
     * @return Waypoint
     */
    Waypoint getWaypoint(int id);

    /**
     * Deleting waypoint and cargo for this waypoint from DB
     * @param id
     */
    void deleteWaypoint(int id);

    /**
     * Getting waypoint from DB by cargoName
     * @param cargoName
     * @return Waypoint
     */
    Waypoint getWaypointByCargoName(String cargoName);

    /**
     * Getting list of Waypoints by waypoint ids.
     * @param ids
     * @return List
     */
    List getWaypointListByIds(List<Integer> ids);

    /**
     * Saving waypoint to DB
     * It uses for AdminOrderController
     * @param waypoint
     */
    void saveWaypointEntity(Waypoint waypoint);

    /**
     * Getting all waypoints which have order = null
     * @return List
     */
    List<Waypoint> getAllFreeWaypoints();

    /**
     * Deleting waypoint and cargo of this waypoint from DB by cargoId
     * @param id
     */
    void deleteWaypointByCargoId(int id);
}
