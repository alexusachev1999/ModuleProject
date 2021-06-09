package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;

public interface DriverService {

    public List<DriverDTO> getAllDrivers ();

    public void saveDriver(DriverDTO driver);

    public Driver getDriver(int id);

    public void deleteDriver(int id);

    List<Driver> getDriversByOrderId(int orderId);

    List<DriverDTO> getValidDriversByOrderId(int orderId);

    DriverDTO getDriverByUsername(String username);

    List<DriverDTO> getCoDriverListByUsername(String username);

    List<Driver> getDriverListByIds(List<Integer> driverIds);

    void saveEntityDriver(Driver driver);

    void updateDriver(DriverDTO driver);

    Driver getDriverByPhoneNumber(String phoneNumber);
}
