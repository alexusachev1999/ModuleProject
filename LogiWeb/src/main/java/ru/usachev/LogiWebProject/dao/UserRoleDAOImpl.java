package ru.usachev.LogiWebProject.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.entity.UserRole;

import javax.transaction.Transactional;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public UserRole getUserRoleByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        UserRole userRole = (UserRole) session.createQuery("from UserRole where user.username=:username")
                .setParameter("username", username)
                .getSingleResult();
        return userRole;
    }
}
