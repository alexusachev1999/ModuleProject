package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;

import java.util.List;
/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface WaypointService {
    /**
     * Calling WaypointDAO to get all waypoints from DB
     * Calling WaypointConverter to convert list of waypoint to list of waypointDTO
     * @return List
     */
    List<WaypointDTO> getAllWaypoints();

    /**
     * Calling WaypointConverter to convert waypoint to waypointDTO
     * Calling WaypointDAO to save waypoint to DB
     * @param waypoint
     */
    void saveWaypoint(WaypointDTO waypoint);

    /**
     * Calling WaypointDAO to get waypoint from DB by waypointId
     * Calling WaypointConverter to convert waypoint to waypointDTO
     * @param id
     * @return Waypoint
     */
    Waypoint getWaypoint(int id);

    /**
     * Calling WaypointDAO to delete waypoint from DB by waypointId
     * @param id
     */
    void deleteWaypoint(int id);

    /**
     * Calling WaypointDAO to get waypoints from DB by waypointIds
     * Calling WaypointConverter to convert list of waypoint to list of waypointDTO
     * @param ids
     * @return List
     */
    List getWaypointListByIds(List<Integer> ids);

    /**
     * Calling WaypointDAO to get list of free waypoint from DB
     * Calling WaypointConverter to convert list of waypoint to list of waypointDTO
     * @return List
     */
    List<WaypointDTO> getAllFreeWaypoints();

    /**
     * Calling WaypointDAO to save waypoint to DB
     * @param waypoint
     */
    void saveWaypointEntity(Waypoint waypoint);

    /**
     * Calling WaypointDAO to delete waypoint from DB byCargoId
     * @param id
     */
    void deleteWaypointByCargoId(int id);
}
