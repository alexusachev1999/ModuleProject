package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;

/**
 * Class for converting Cargo to CargoDTO and vice versa
 * @author Alex Usachev
 */
public interface CargoConverter {
    /**
     * Converting cargoDTO to cargo
     * @param cargoDTO
     * @return Cargo
     */
    Cargo convertCargoDTOToCargo(CargoDTO cargoDTO);

    /**
     * Converting cargo to cargoDTO
     * @param cargo
     * @return CargoDTO
     */
    CargoDTO convertCargoToCargoDTO(Cargo cargo);
}
