package ru.usachev.LogiWebProject.converter;

import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.UserConverter;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;
import ru.usachev.LogiWebProject.entity.UserRole;

import java.util.*;

@Component
public class UserConverterImpl implements UserConverter {
    @Override
    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.isEnabled());

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(userDTO.getUserRole());
        List <UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        user.setUserRole(userRoles);
        return user;
    }
}
