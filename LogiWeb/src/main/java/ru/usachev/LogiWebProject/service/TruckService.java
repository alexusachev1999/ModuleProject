package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.dto.restDTO.TruckRestDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;

public interface TruckService {
    List<TruckDTO> getAllTrucks();

    List<TruckDTO> getAllEnabledTrucks();

    Truck getTruck(int id);

    void deleteTruck(int id);

    void saveTruck(TruckDTO truck);

    List<TruckDTO> getValidTrucksForOrder(List<WaypointDTO> waypoints);

    Truck getTruckByRegistrationNumber(String registrationNumber);

    TruckDTO getTruckByOrderId(int orderId);

    TruckDTO getTruckByOrderNumber(int number);

    Truck getTruckByDriverId(int id);

    TruckRestDTO getTruckRestDTO();
}
