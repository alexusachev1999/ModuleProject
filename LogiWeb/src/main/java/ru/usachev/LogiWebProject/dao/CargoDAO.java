package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.entity.Cargo;

import java.util.List;

public interface CargoDAO {
    public List<Cargo> getAllCargoes();

    public void saveCargo(Cargo cargo);

    public void deleteCargo(int id);

    public Cargo getCargo(int id);

    Cargo getCargoByName(String cargoName);

    Cargo getCargoByWaypointId(int waypointId);
}
