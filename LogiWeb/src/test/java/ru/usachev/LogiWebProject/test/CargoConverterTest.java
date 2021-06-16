package ru.usachev.LogiWebProject.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ru.usachev.LogiWebProject.converter.CargoConverterImpl;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;

@RunWith(MockitoJUnitRunner.class)
public class CargoConverterTest {

    @InjectMocks
    public CargoConverterImpl cargoConverter;

    private Cargo cargo;
    private CargoDTO cargoDTO;

    @Before
    public void setup(){
        cargoDTO = SetupClass.setupCargoDTO();
        cargo = SetupClass.setupCargo();
    }

    @Test
    public void TestOfConversionFromCargoToCargoDTO(){
        CargoDTO convertedCargoDTO = cargoConverter.convertCargoToCargoDTO(cargo);
        Assert.assertEquals(cargoDTO, convertedCargoDTO);
    }

    @Test
    public void TestOfConversionFromCargoDTOToCargo(){
        Cargo convertedCargo = cargoConverter.convertCargoDTOToCargo(cargoDTO);
        Assert.assertEquals(cargo, convertedCargo);
    }
}
