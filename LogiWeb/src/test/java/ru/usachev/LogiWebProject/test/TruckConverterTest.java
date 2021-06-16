package ru.usachev.LogiWebProject.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.usachev.LogiWebProject.converter.TruckConverterImpl;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.City;
import ru.usachev.LogiWebProject.entity.Truck;
import ru.usachev.LogiWebProject.service.CityService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TruckConverterTest {
    @Mock
    private CityService cityService;

    @InjectMocks
    private TruckConverterImpl truckConverter;

    private Truck truck;

    private TruckDTO truckDTO;

    private List<Truck> truckList;

    private List<TruckDTO> truckDTOList;

    private City city;

    @Before
    public void setup(){
        truck = SetupClass.setupTruck();
        truckDTO = SetupClass.setupTruckDTO();
        truckList = SetupClass.setupTruckList();
        truckDTOList = SetupClass.setupTruckDTOList();
        city = SetupClass.setupCity();

        when(cityService.getCityByName(truckDTO.getCity())).thenReturn(city);
    }

    @Test
    public void TestOfConversionTruckToTruckDTO(){
        TruckDTO convertedTruckDTO = truckConverter.convertTruckToTruckDTO(truck);
        Assert.assertEquals(truckDTO, convertedTruckDTO);
    }

    @Test
    public void TestOfConversionTruckDTOToTruck(){
        Truck convertedTruck = truckConverter.convertTruckDTOToTruck(truckDTO);
        Assert.assertEquals(truck, convertedTruck);
    }

    @Test
    public void TestOfConversionTruckListToTruckDTOList(){
        List<TruckDTO> convertedTruckDTOList = truckConverter.convertTruckListToTruckDTOList(truckList);
        Assert.assertEquals(truckDTOList.size(), convertedTruckDTOList.size());
        Assert.assertEquals(truckDTOList.get(0), convertedTruckDTOList.get(0));
    }

}
