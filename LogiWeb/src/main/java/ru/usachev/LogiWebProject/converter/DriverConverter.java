package ru.usachev.LogiWebProject.converter;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;

public interface DriverConverter {

    Driver convertDriverDTOToDriver(DriverDTO driver);
    DriverDTO convertDriverToDriverDTO(Driver driver);
    List<Driver> convertDriverDTOListToDriverList(List<DriverDTO> driversDTO);

    List<DriverDTO> convertDriverListToDriverDTOList(List<Driver> drivers);
}
