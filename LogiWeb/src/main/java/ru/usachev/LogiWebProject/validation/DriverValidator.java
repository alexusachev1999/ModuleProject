package ru.usachev.LogiWebProject.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.service.DriverService;

import javax.persistence.NoResultException;

@Component
public class DriverValidator {
    @Autowired
    private DriverService driverService;

    public boolean validDriver(DriverDTO driverDTO){
        boolean isValid = false;

        isValid = isUniqueDriver(driverDTO);

        return isValid;
    }

    public boolean isUniqueDriver(DriverDTO driverDTO){
        boolean isUnique = false;

        Driver driver;
        try {
            driver = driverService.getDriverByPhoneNumber(driverDTO.getPhoneNumber());
        } catch (NoResultException e){
            driver = null;
        }

        if (driver == null)
            isUnique = true;

        return isUnique;
    }
}
