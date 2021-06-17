package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.entity.Cargo;

import java.util.List;

/**
 * DAO for operations for table 'cargoes' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface CargoDAO {
    /**
     * Getting all cargoes from D
     * @return List
     */
    List<Cargo> getAllCargoes();

    /**
     * Saving and updating cargo to DB
     * @param cargo
     */
    void saveCargo(Cargo cargo);

    /**
     * Delete cargo from DB by cargoId
     * @param id
     */
    void deleteCargo(int id);

    /**
     * Getting cargo from DB by cargoId
     * @param id
     * @return Cargo
     */
    Cargo getCargo(int id);

    /**
     * Getting cargo from DB by cargoName
     * @param cargoName
     * @return Cargo
     */
    Cargo getCargoByName(String cargoName);

    /**
     * Getting cargo from DB by waypointId
     * @param waypointId
     * @return Cargo
     */
    Cargo getCargoByWaypointId(int waypointId);
}
