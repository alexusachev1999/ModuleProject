package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.TruckConverter;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.Truck;
import ru.usachev.LogiWebProject.service.CityService;

import java.util.ArrayList;
import java.util.List;

@Component
public class TruckConverterImpl implements TruckConverter {

    @Autowired
    private CityService cityService;
    @Override
    public Truck convertTruckDTOToTruck(TruckDTO truckDTO) {
        Truck truck = new Truck();

        truck.setId(truckDTO.getId());
        truck.setRegistrationNumber(truckDTO.getRegistrationNumber());
        truck.setDriverShiftDuration(truckDTO.getDriverShiftDuration());
        truck.setCapacity(truckDTO.getCapacity());
        truck.setCity(cityService.getCityByName(truckDTO.getCity()));
        truck.setState(truckDTO.isState());
        return truck;
    }

    @Override
    public TruckDTO convertTruckToTruckDTO(Truck truck) {
        TruckDTO truckDTO = new TruckDTO();

        truckDTO.setId(truck.getId());
        truckDTO.setRegistrationNumber(truck.getRegistrationNumber());
        truckDTO.setDriverShiftDuration(truck.getDriverShiftDuration());
        truckDTO.setCapacity(truck.getCapacity());
        truckDTO.setCity(truck.getCity().getName());
        truckDTO.setState(truck.isState());
        return truckDTO;
    }

    @Override
    public List<TruckDTO> convertTruckListToTruckDTOList(List<Truck> trucks) {
        List<TruckDTO> convertedTruckList = new ArrayList<>();

        for (Truck truck: trucks){
            convertedTruckList.add(convertTruckToTruckDTO(truck));
        }

        return convertedTruckList;
    }
}
