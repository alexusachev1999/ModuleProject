package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;

import java.util.List;
/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface UserService {
    List<User> getAllUsers();

    void saveUser(UserDTO userDTO);

    void deleteUser(User user);

    User getUserByUsername(String userName);
}
