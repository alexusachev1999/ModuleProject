package ru.usachev.LogiWebProject.converter.api;

import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;

/**
 * Class for converting UserDTO to User
 * @author Alex Usachev
 */
public interface UserConverter {
    /**
     * Converting userDTO to user
     * @param userDTO
     * @return User
     */
    User convertUserDTOToUser(UserDTO userDTO);
}
