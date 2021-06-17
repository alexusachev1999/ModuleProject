package ru.usachev.LogiWebProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.dao.api.CargoDAO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.entity.Waypoint;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CargoDAOImpl implements CargoDAO {

    private static Logger logger = Logger.getLogger(CargoDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Cargo> getAllCargoes() {
        Session session = sessionFactory.getCurrentSession();

        try {
            return session.createQuery("from Cargo").getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void saveCargo(Cargo cargo) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cargo);

        String message = ("Save or update cargo: " + cargo.getName());

        logger.info(message);
    }

    @Override
    public void deleteCargo(int id) {
        Session session = sessionFactory.getCurrentSession();
        Cargo cargo = session.get(Cargo.class, id);
        session.delete(cargo);

        String message = "delete cargo: " + cargo.getName();

        logger.info(message);
    }

    @Override
    public Cargo getCargo(int id) {
        Session session = sessionFactory.getCurrentSession();
        Cargo cargo = session.get(Cargo.class, id);

        try {
            return cargo;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Cargo getCargoByName(String cargoName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cargo where name=:cargoName");
        query.setParameter("cargoName", cargoName);
        Cargo cargo = (Cargo) query.getSingleResult();

        try {
            return cargo;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Cargo getCargoByWaypointId(int waypointId) {
        Session session = sessionFactory.getCurrentSession();
        Waypoint waypoint = session.get(Waypoint.class, waypointId);

        try {
            return waypoint.getCargo();
        } catch (NoResultException e){
            return null;
        }
    }
}
