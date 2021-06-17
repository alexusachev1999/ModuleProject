package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.dto.restDTO.TruckRestDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;

/**
 * DAO for operations for table 'trucks' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface TruckDAO {
    /**
     * Getting all trucks from DB
     * @return List
     */
    List<Truck> getAllTrucks();

    /**
     * Getting all enabled trucks from DB
     * @return List
     */
    List<Truck> getAllEnabledTrucks();

    /**
     * Getting the truck from DB by truckId
     * @param id
     * @return Truck
     */
    Truck getTruck(int id);

    /**
     * Setting enabled = false for the truck with this id and updating it to DB
     * @param id
     */
    void deleteTruck(int id);

    /**
     * Saving the new truck to DB
     * @param truck
     */
    void saveTruck(Truck truck);

    /**
     * Getting all valid trucks for this order by List of WaypointDTO of this order
     * Criteria of valid truck:
     * 1. Truck state = true
     * 2. Truck enabled = true
     * 3. Truck order = null
     * 4. Truck capacity <= needingCapacity (it's calculate by BusinessCalculating class)
     * @param waypoints
     * @return List
     */
    List<Truck> getValidTrucksForOrder(List<WaypointDTO> waypoints);

    /**
     * Getting the truck from DB by its registration number
     * @param registrationNumber
     * @return Truck
     */
    Truck getTruckByRegistrationNumber(String registrationNumber);

    /**
     * Getting the truck from DB by orderId
     * @param orderId
     * @return Truck
     */
    Truck getTruckByOrderId(int orderId);

    /**
     * Getting the truck from DB by orderNumber
     * @param number
     * @return Truck
     */
    Truck getTruckByOrderNumber(int number);

    /**
     * Getting the truck from DB by driverId
     * @param id
     * @return Truck
     */
    Truck getTruckByDriverId(int id);

    /**
     * Getting all actual statistics for trucks
     * 1. Number of trucks now
     * 2. Number of free trucks
     * 3. Number of trucks are executing the order now
     * 4. Number of broken trucks
     * @return TruckRestDTO
     */
    TruckRestDTO getTruckRestDTO();

}
