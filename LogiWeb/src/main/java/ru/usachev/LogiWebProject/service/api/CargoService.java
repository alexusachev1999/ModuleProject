package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;

import java.util.List;

/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface CargoService {

    List<Cargo> getAllCargoes();

    void saveCargo(CargoDTO cargo);

    Cargo getCargo(int id);

    Cargo getCargoByName(String cargoName);

    CargoDTO getCargoByWaypointId(int waypointId);

}
