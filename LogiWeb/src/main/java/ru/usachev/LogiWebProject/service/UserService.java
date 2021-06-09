package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(UserDTO userDTO);

    User getUser(int id);

    void deleteUser(User user);

    User getUserByUsername(String userName);

    List<User> freeUserForDrivers();
}
