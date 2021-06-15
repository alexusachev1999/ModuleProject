package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.dto.restDTO.TruckRestDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;

public interface TruckService {
    public List<TruckDTO> getAllTrucks();

    public Truck getTruck(int id);

    public void deleteTruck(int id);

    public void saveTruck(TruckDTO truck);

    public List<TruckDTO> getValidTrucksForOrder(List<WaypointDTO> waypoints);

    Truck getTruckByRegistrationNumber(String registrationNumber);

    TruckDTO getTruckByOrderId(int orderId);

    TruckDTO getTruckByOrderNumber(int number);

    Truck getTruckByDriverId(int id);

    TruckRestDTO getTruckRestDTO();
}
