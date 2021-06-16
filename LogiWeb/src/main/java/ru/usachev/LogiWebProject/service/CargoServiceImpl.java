package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.aop.UpdateAnnotation;
import ru.usachev.LogiWebProject.converter.api.CargoConverter;
import ru.usachev.LogiWebProject.dao.CargoDAO;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargoDAO cargoDAO;

    @Autowired
    private CargoConverter cargoConverter;

    @Override
    @Transactional
    public List<Cargo> getAllCargoes() {
        return cargoDAO.getAllCargoes();
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void saveCargo(CargoDTO cargo) {
        Cargo convertedCargo = cargoConverter.convertCargoDTOToCargo(cargo);
        cargoDAO.saveCargo(convertedCargo);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void deleteCargo(int id) {
        cargoDAO.deleteCargo(id);
    }

    @Override
    @Transactional
    public Cargo getCargo(int id) {
        return cargoDAO.getCargo(id);
    }

    @Override
    @Transactional
    public Cargo getCargoByName(String cargoName) {
        return cargoDAO.getCargoByName(cargoName);
    }

    @Override
    @Transactional
    public CargoDTO getCargoByWaypointId(int waypointId) {
        Cargo cargo = cargoDAO.getCargoByWaypointId(waypointId);
        return cargoConverter.convertCargoToCargoDTO(cargo);
    }
}
