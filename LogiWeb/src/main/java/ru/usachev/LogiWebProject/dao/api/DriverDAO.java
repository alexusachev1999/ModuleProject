package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.restDTO.DriverRestDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;

public interface DriverDAO {
    List<Driver> getAllDrivers();

    List<Driver> getAllEnabledDrivers();

    void saveDriver(Driver driver);

    Driver getDriver(int id);

    void deleteDriver(int id);

    List<Driver> getDriversByOrderId(int orderId);

    List<Driver> getValidDriversByOrderId(int orderId);

    Driver getDriverByUsername(String username);

    List<Driver> getCoDriverListByUsername(String username);

    List<Driver> getDriverListByIds(List<Integer> driverIds);

    void saveEntityDriver(Driver driver);

    void updateDriver(DriverDTO driver);

    Driver getDriverByPhoneNumber(String phoneNumber);

    DriverRestDTO getDriverRestDTO();

    void zeroingWorkHoursOfDriversOneTimeInMonth();

    List<Driver> getDriversForCompletedOrderByOrderId(int id);
}
