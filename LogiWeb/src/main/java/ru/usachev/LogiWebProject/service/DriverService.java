package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.restDTO.DriverRestDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;

public interface DriverService {

    List<DriverDTO> getAllDrivers ();

    List<DriverDTO> getAllEnabledDrivers ();

    void saveDriver(DriverDTO driver);

    Driver getDriver(int id);

    void deleteDriver(int id);

    List<Driver> getDriversByOrderId(int orderId);

    List<DriverDTO> getValidDriversByOrderId(int orderId);

    DriverDTO getDriverByUsername(String username);

    List<DriverDTO> getCoDriverListByUsername(String username);

    List<Driver> getDriverListByIds(List<Integer> driverIds);

    void saveEntityDriver(Driver driver);

    void updateDriver(DriverDTO driver);

    Driver getDriverByPhoneNumber(String phoneNumber);

    DriverRestDTO getDriverRestDTO();

    void zeroingWorkHoursOfDriversOneTimeInMonth();

    List<DriverDTO> getDriversDTOForCompletedOrderByOrderId(int id);
}
