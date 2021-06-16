package ru.usachev.LogiWebProject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.UserConverter;
import ru.usachev.LogiWebProject.dao.UserRoleDAO;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;
import ru.usachev.LogiWebProject.entity.UserRole;

import java.util.*;

@Component
public class UserConverterImpl implements UserConverter {


    @Autowired
    private UserRoleDAO userRoleDAO;

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

    @Override
    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setUserRole(user.getUserRole().get(0).getRole());

        return userDTO;
    }

    @Override
    public List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User u: users){
            usersDTO.add(convertUserToUserDTO(u));
        }

        return usersDTO;
    }
}
