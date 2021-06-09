package ru.usachev.LogiWebProject.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.dto.CargoDTO;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.entity.User;
import ru.usachev.LogiWebProject.service.CargoService;
import ru.usachev.LogiWebProject.service.UserService;

import javax.persistence.NoResultException;

@Component
public class UserValidator {
    @Autowired
    private UserService userService;

    public boolean validUser(UserDTO userDTO){
        boolean isValid = false;

        isValid = isUniqueUser(userDTO);

        return isValid;
    }

    public boolean isUniqueUser(UserDTO userDTO){
        boolean isUnique = false;

            User user;
            try {
                user = userService.getUserByUsername(userDTO.getUsername());
            } catch (NoResultException e){
                user = null;
            }

            if (user == null)
                isUnique = true;

        return isUnique;
    }
}
