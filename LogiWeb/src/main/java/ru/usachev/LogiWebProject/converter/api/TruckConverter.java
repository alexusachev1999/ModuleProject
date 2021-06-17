package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import java.util.List;
/**
 * Class for converting Truck to TruckDTO and vice versa
 * @author Alex Usachev
 */
public interface TruckConverter {
    /**
     * Converting truckDTO to truck
     * @param truckDTO
     * @return Truck
     */
    Truck convertTruckDTOToTruck(TruckDTO truckDTO);

    /**
     * Converting truck to truckDTO
     * @param truck
     * @return TruckDTO
     */
    TruckDTO convertTruckToTruckDTO(Truck truck);

    /**
     * Converting list of trucks to list of trucksDTO
     * @param trucks
     * @return List
     */
    List<TruckDTO> convertTruckListToTruckDTOList(List<Truck> trucks);
}
