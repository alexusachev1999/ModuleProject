package ru.usachev.LogiWebProject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.dao.api.DistanceDAO;
import ru.usachev.LogiWebProject.entity.Distance;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DistanceDAOImpl implements DistanceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Distance> getAllDistances() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Distance").getResultList();
    }
}
