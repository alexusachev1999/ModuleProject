package ru.usachev.LogiWebProject.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.service.CargoService;

import javax.persistence.NoResultException;

@Component
public class CargoValidator {

    @Autowired
    private CargoService cargoService;

    public boolean validCargo(CargoDTO cargoDTO){
        boolean isValid = false;

        isValid = isUniqueCargo(cargoDTO);

        return isValid;
    }

    public boolean isUniqueCargo(CargoDTO cargoDTO){
        boolean isUnique = false;

        /* It's for updating this cargo*/
        if (cargoDTO.getId() != 0){
            isUnique = true;
        } else {

            Cargo cargo;
            try {
                cargo = cargoService.getCargoByName(cargoDTO.getName());
            } catch (NoResultException e){
                cargo = null;
            }

            if (cargo == null)
                isUnique = true;
        }

        return isUnique;
    }
}
