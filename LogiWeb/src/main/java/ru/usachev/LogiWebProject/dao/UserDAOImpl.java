package ru.usachev.LogiWebProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.dao.api.UserDAO;
import ru.usachev.LogiWebProject.entity.Driver;
import ru.usachev.LogiWebProject.entity.User;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public User findByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user;

		user = (User) session.createQuery("from User where username=:username")
				.setParameter("username", username)
				.getSingleResult();

		return user;
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();

		List users = session.createQuery("from User", User.class)
				.getResultList();

		try {
			return users;
		} catch (NoResultException e){
			return null;
		}
	}

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);

		logger.info("save user: " + user.getUsername());
	}

	@Override
	public User getUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setEnabled(false);

		session.update(user);
		logger.info("disable user: " + user.getUsername());


		List<Driver> drivers = session.createQuery("from Driver where user=:user")
				.setParameter("user", user)
				.getResultList();

		if (!drivers.isEmpty()) {
			Driver driver = drivers.get(0);
			driver.setEnabled(false);
			session.saveOrUpdate(driver);
			logger.info("disable driver: " + driver.getName() + " " + driver.getSurname());
		}
	}
}