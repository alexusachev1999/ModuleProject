package ru.usachev.LogiWebProject.dao.api;


import ru.usachev.LogiWebProject.entity.User;

import java.util.List;
/**
 * DAO for operations for table 'users' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface UserDAO {

	/**
	 * Getting the user frm DB by his username
	 * @param username
	 * @return User
	 */
	User findByUserName(String username);

	/**
	 * Getting all users from DB
	 * @return List
	 */
	List<User> getAllUsers();

	/**
	 * Saving the new user to DB
	 * @param user
	 */
    void saveUser(User user);

	/**
	 * Getting the user from DB by userId
	 * @param id
	 * @return
	 */
	User getUser(int id);

	/**
	 * Setting enabled = false for administrator
	 * Setting enabled = false and driver enabled = false for driver
	 * @param user
	 */
	void deleteUser(User user);
}