package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.dto.restDTO.TruckRestDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;
/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface TruckService {
    /**
     * Calling TruckDAO to get all trucks from DB
     * Calling TruckConverter to convert list of truck to list of truckDTO
     * @return List
     */
    List<TruckDTO> getAllTrucks();

    /**
     * Calling TruckDAO to get all enabled trucks from DB
     * Calling TruckConverter to convert list of truck to list of truckDTO
     * @return List
     */
    List<TruckDTO> getAllEnabledTrucks();

    /**
     * Calling TruckDAO to get truck from DB by truckId
     * Calling TruckConverter to convert truck to truckDTO
     * @param id
     * @return Truck
     */
    Truck getTruck(int id);

    /**
     * Calling TruckDAO to delete truck from DB by truckId
     * @param id
     */
    void deleteTruck(int id);

    /**
     * Calling TruckConverter to convert truckDTO to truck
     * Calling TruckDAO to save truck to DB
     * @param truck
     */
    void saveTruck(TruckDTO truck);

    /**
     * Calling TruckDAO to get list of valid truck from DB by list of WaypointDTO
     * Calling TruckConverter to convert list of truck to list of truckDTO
     * @param waypoints
     * @return List
     */
    List<TruckDTO> getValidTrucksForOrder(List<WaypointDTO> waypoints);

    /**
     * Calling TruckDAO to get truck from DB by registration number
     * @param registrationNumber
     * @return Truck
     */
    Truck getTruckByRegistrationNumber(String registrationNumber);

    /**
     * Calling TruckDAO to get truck from DB by orderId
     * Calling TruckConverter to convert truck to truckDTO
     * @param orderId
     * @return TruckDTO
     */
    TruckDTO getTruckByOrderId(int orderId);

    /**
     * Calling TruckDAO to get truck from DB by orderNumber
     * Calling TruckConverter to convert truck to truckDTO
     * @param number
     * @return TruckDTO
     */
    TruckDTO getTruckByOrderNumber(int number);

    /**
     * Calling TruckDAO to get truckRestDTO from DB
     * @return TruckRestDTO
     */
    TruckRestDTO getTruckRestDTO();
}
