package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.converter.UserConverter;
import ru.usachev.LogiWebProject.dao.UserDAO;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        return users;
    }

    @Override
    @Transactional
    public void saveUser(UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = userConverter.convertUserDTOToUser(userDTO);
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    @Transactional
    public User getUserByUsername(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    @Transactional
    public List<User> freeUserForDrivers() {
        return userDAO.freeUserForDrivers();
    }
}
