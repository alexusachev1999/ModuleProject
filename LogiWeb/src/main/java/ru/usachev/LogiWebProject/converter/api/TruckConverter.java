package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;

public interface TruckConverter {
    Truck convertTruckDTOToTruck(TruckDTO truckDTO);

    TruckDTO convertTruckToTruckDTO(Truck truck);

    List<TruckDTO> convertTruckListToTruckDTOList(List<Truck> trucks);
}
