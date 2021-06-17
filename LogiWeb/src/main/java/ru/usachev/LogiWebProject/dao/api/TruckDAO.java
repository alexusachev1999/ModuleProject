package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.dto.restDTO.TruckRestDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;

public interface TruckDAO {
    List<Truck> getAllTrucks();

    List<Truck> getAllEnabledTrucks();

    Truck getTruck(int id);

    void deleteTruck(int id);

    void saveTruck(Truck truck);

    List<Truck> getValidTrucksForOrder(List<WaypointDTO> waypoints);

    Truck getTruckByRegistrationNumber(String registrationNumber);

    Truck getTruckByOrderId(int orderId);

    Truck getTruckByOrderNumber(int number);

    Truck getTruckByDriverId(int id);

    TruckRestDTO getTruckRestDTO();

}
