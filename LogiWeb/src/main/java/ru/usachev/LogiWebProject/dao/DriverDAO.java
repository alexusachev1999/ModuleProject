package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;

public interface DriverDAO {
    public List<Driver> getAllDrivers();

    public void saveDriver(Driver driver);

    public Driver getDriver(int id);

    public void deleteDriver(int id);

    List<Driver> getDriversByOrderId(int orderId);

    List<Driver> getValidDriversByOrderId(int orderId);

    Driver getDriverByUsername(String username);

    List<Driver> getCoDriverListByUsername(String username);

    List<Driver> getDriverListByIds(List<Integer> driverIds);

    void saveEntityDriver(Driver driver);

    void updateDriver(DriverDTO driver);

    Driver getDriverByPhoneNumber(String phoneNumber);
}
