package ru.usachev.LogiWebProject.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.usachev.LogiWebProject.converter.DriverConverterImpl;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.*;
import ru.usachev.LogiWebProject.service.api.CityService;
import ru.usachev.LogiWebProject.service.api.TruckService;
import ru.usachev.LogiWebProject.service.api.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverConverterTest {
    @Mock
    private TruckService truckService;

    @Mock
    private CityService cityService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DriverConverterImpl driverConverter;

    private Driver driver;

    private DriverDTO driverDTO;

    private Truck truck;

    private User user;

    private City city;

    @Before
    public void setup(){
        driver = SetupClass.setupDriver();
        driverDTO = SetupClass.setupDriverDTO();
        truck = SetupClass.setupTruck();
        city = SetupClass.setupCity();
        user = SetupClass.setupUser();

        when(truckService.getTruckByRegistrationNumber("АА12345")).thenReturn(truck);
        when(cityService.getCityByName("test")).thenReturn(city);
        when(userService.getUserByUsername("test")).thenReturn(user);
    }

    @Test
    public void TestOfConversionDriverToDriverDTO(){
        DriverDTO convertedDriverDTO = driverConverter.convertDriverToDriverDTO(driver);
        Assert.assertEquals(driverDTO, convertedDriverDTO);
    }

    @Test
    public void TestOfConversionDriverDTOToDriver(){
        Driver convertedDriver = driverConverter.convertDriverDTOToDriver(driverDTO);
        Assert.assertEquals(driver, convertedDriver);
    }

    @Test
    public void TestOfConversionDriverDTOListToDriverList(){
        List<DriverDTO> driverDTOList = new ArrayList<>();
        driverDTOList.add(driverDTO);

        List<Driver> driverList = driverConverter.convertDriverDTOListToDriverList(driverDTOList);
        Assert.assertEquals(driverDTOList.size(), driverList.size());

        Assert.assertEquals(driver, driverList.get(0));

    }

    @Test
    public void TestOfConversionDriverListToDriverDTOList(){
        List<Driver> driverList = new ArrayList<>();
        driverList.add(driver);

        List<DriverDTO> driverDTOList = driverConverter.convertDriverListToDriverDTOList(driverList);
        Assert.assertEquals(driverDTOList.size(), driverList.size());

        Assert.assertEquals(driverDTO, driverDTOList.get(0));
    }
}
