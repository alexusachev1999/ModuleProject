package ru.usachev.LogiWebProject.converter;

import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.CargoConverter;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;

@Component
public class CargoConverterImpl implements CargoConverter {
    @Override
    public Cargo convertCargoDTOToCargo(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();

        cargo.setId(cargoDTO.getId());
        cargo.setNumber(cargoDTO.getNumber());
        cargo.setName(cargoDTO.getName());
        cargo.setWeight(cargoDTO.getWeight());
        cargo.setStatus(cargoDTO.getStatus());
        cargo.setWaypoints(null);
        return cargo;
    }

    @Override
    public CargoDTO convertCargoToCargoDTO(Cargo cargo) {
        CargoDTO cargoDTO = new CargoDTO();

        cargoDTO.setId(cargo.getId());
        cargoDTO.setNumber(cargo.getNumber());
        cargoDTO.setName(cargo.getName());
        cargoDTO.setWeight(cargo.getWeight());
        cargoDTO.setStatus(cargo.getStatus());
        return cargoDTO;
    }
}
