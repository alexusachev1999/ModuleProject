package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;

public interface TruckDAO {
    public List<Truck> getAllTrucks();

    public Truck getTruck(int id);

    public void deleteTruck(int id);

    public void saveTruck(Truck truck);

    public List<Truck> getValidTrucksForOrder(List<WaypointDTO> waypoints);

    Truck getTruckByRegistrationNumber(String registrationNumber);

    Truck getTruckByOrderId(int orderId);

    Truck getTruckByOrderNumber(int number);

    Truck getTruckByDriverId(int id);
}
