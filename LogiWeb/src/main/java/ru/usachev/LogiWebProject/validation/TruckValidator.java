package ru.usachev.LogiWebProject.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.entity.Truck;
import ru.usachev.LogiWebProject.service.TruckService;

import javax.persistence.NoResultException;

@Component
public class TruckValidator {
    @Autowired
    private TruckService truckService;

    public boolean validTruck(TruckDTO truckDTO){
        boolean isValid = false;

        isValid = isUniqueTruck(truckDTO);

        return isValid;
    }

    public boolean isUniqueTruck(TruckDTO truckDTO){
        boolean isUnique = false;

        Truck truck;
        try {
            truck = truckService.getTruckByRegistrationNumber(truckDTO.getRegistrationNumber());
        } catch (NoResultException e){
            truck = null;
        }

        if (truck == null)
            isUnique = true;

        return isUnique;
    }
}
