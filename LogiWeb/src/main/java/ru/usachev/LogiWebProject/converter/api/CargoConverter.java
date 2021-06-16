package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;

public interface CargoConverter {
    Cargo convertCargoDTOToCargo(CargoDTO cargoDTO);
    CargoDTO convertCargoToCargoDTO(Cargo cargo);
}
