package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.DriverConverter;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.entity.Truck;
import ru.usachev.LogiWebProject.entity.User;
import ru.usachev.LogiWebProject.service.api.CityService;
import ru.usachev.LogiWebProject.service.api.TruckService;
import ru.usachev.LogiWebProject.service.api.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class DriverConverterImpl implements DriverConverter {

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TruckService truckService;



    @Override
    public Driver convertDriverDTOToDriver(DriverDTO driverDTO) {
        Driver convertedDriver = new Driver();

        convertedDriver.setId(driverDTO.getId());
        convertedDriver.setName(driverDTO.getName());
        convertedDriver.setSurname(driverDTO.getSurname());
        convertedDriver.setPhoneNumber(driverDTO.getPhoneNumber());
        convertedDriver.setStatus(driverDTO.getStatus());
        convertedDriver.setWorkedHours(driverDTO.getWorkedHours());
        convertedDriver.setWorkType(driverDTO.isWorkType());
        convertedDriver.setTimeForOrderExecution(driverDTO.getTimeForOrderExecution());


        if (driverDTO.getTruck() != null){
        Truck truck = truckService.getTruckByRegistrationNumber(driverDTO.getTruck());
        Order order = truck.getOrder();
        convertedDriver.setOrder(order);
        convertedDriver.setTruck(truck);
        }


        convertedDriver.setCity(cityService.getCityByName(driverDTO.getCity()));
        convertedDriver.setUser(userService.getUserByUsername(driverDTO.getUser()));
        return convertedDriver;
    }

    @Override
    public DriverDTO convertDriverToDriverDTO(Driver driver) {
        DriverDTO convertedDriver = new DriverDTO();

        convertedDriver.setId(driver.getId());
        convertedDriver.setName(driver.getName());
        convertedDriver.setSurname(driver.getSurname());
        convertedDriver.setPhoneNumber(driver.getPhoneNumber());
        convertedDriver.setStatus(driver.getStatus());
        convertedDriver.setCity(driver.getCity().getName());
        convertedDriver.setWorkedHours(driver.getWorkedHours());
        convertedDriver.setWorkType(driver.isWorkType());
        convertedDriver.setTimeForOrderExecution(driver.getTimeForOrderExecution());

        if (driver.getTruck() != null)
            convertedDriver.setTruck(driver.getTruck().getRegistrationNumber());

        User user = driver.getUser();
        if(user != null)
            convertedDriver.setUser(driver.getUser().getUsername());
        else
            convertedDriver.setUser(null);

        return convertedDriver;
    }

    @Override
    public List<Driver> convertDriverDTOListToDriverList(List<DriverDTO> driversDTO) {
        List<Driver> drivers = new ArrayList<>();
        for (DriverDTO driverDTO: driversDTO){
            drivers.add(convertDriverDTOToDriver(driverDTO));
        }
        return drivers;
    }

    @Override
    public List<DriverDTO> convertDriverListToDriverDTOList(List<Driver> drivers) {

        List<DriverDTO> driversDTO = new ArrayList<>();
        for(Driver driver: drivers){
            driversDTO.add(convertDriverToDriverDTO(driver));
        }
        return driversDTO;
    }
}
