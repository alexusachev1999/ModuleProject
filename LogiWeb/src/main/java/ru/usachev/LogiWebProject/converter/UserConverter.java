package ru.usachev.LogiWebProject.converter;

import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;

import java.util.List;

@Component
public interface UserConverter {
    User convertUserDTOToUser(UserDTO userDTO);
    UserDTO convertUserToUserDTO(User user);
    List<UserDTO> convertUserListToUserDTOList(List<User> users);
}
