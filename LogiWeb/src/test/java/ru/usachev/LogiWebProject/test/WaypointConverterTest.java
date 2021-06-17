package ru.usachev.LogiWebProject.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.usachev.LogiWebProject.converter.WaypointConverterImpl;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.entity.City;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.api.CargoService;
import ru.usachev.LogiWebProject.service.api.CityService;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WaypointConverterTest {
    @InjectMocks
    private WaypointConverterImpl waypointConverter;

    @Mock
    private CityService cityService;

    @Mock
    private CargoService cargoService;

    private Waypoint waypoint;

    private WaypointDTO waypointDTO;

    private City cityOfLoading;

    private City cityOfUnloading;

    private Cargo cargo;

    @Before
    public void setup(){
        waypoint = SetupClass.setupWaypoint();
        waypointDTO = SetupClass.setupWaypointDTO();
        cityOfLoading = SetupClass.setupCityOfLoading();
        cityOfUnloading = SetupClass.setupCityOfUnloading();
        cargo = SetupClass.setupCargo();


        when(cityService.getCityByName("CityOfLoading")).thenReturn(cityOfLoading);
        when(cityService.getCityByName("CityOfUnloading")).thenReturn(cityOfUnloading);
        when(cargoService.getCargoByName(waypointDTO.getCargo())).thenReturn(cargo);
    }

    @Test
    public void TestOfConversionWaypointToWaypointDTO(){
        WaypointDTO convertedWaypointDTO = waypointConverter.convertWaypointToWaypointDTO(waypoint);
        Assert.assertEquals(waypointDTO, convertedWaypointDTO);
    }

    @Test
    public void TestOfConversionWaypointDTOToWaypoint(){
        Waypoint convertedWaypoint = waypointConverter.convertWaypointDTOToWaypoint(waypointDTO);
        Assert.assertEquals(waypoint, convertedWaypoint);
    }
}
