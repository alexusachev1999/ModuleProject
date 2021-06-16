package ru.usachev.LogiWebProject.dao;


import ru.usachev.LogiWebProject.entity.User;

import java.util.List;

public interface UserDAO {

	User findByUserName(String username);

	List<User> getAllUsers();

    void saveUser(User user);

	User getUser(int id);

	void deleteUser(User user);

    List<User> freeUserForDrivers();
}