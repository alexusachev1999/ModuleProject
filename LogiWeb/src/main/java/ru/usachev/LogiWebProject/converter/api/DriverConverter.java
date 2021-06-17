package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;
/**
 * Class for converting Driver to DriverDTO and vice versa
 * @author Alex Usachev
 */
public interface DriverConverter {
    /**
     * Converting driverDTO to driver
     * @param driverDTO
     * @return Driver
     */
    Driver convertDriverDTOToDriver(DriverDTO driverDTO);

    /**
     * Converting driver to driverDTO
     * @param driver
     * @return DriverDTO
     */
    DriverDTO convertDriverToDriverDTO(Driver driver);

    /**
     * Converting list of driverDTO to list of driver
     * @param driversDTO
     * @return List
     */
    List<Driver> convertDriverDTOListToDriverList(List<DriverDTO> driversDTO);

    /**
     * Converting list of driver to list of driverDTO
     * @param drivers
     * @return List
     */
    List<DriverDTO> convertDriverListToDriverDTOList(List<Driver> drivers);
}
