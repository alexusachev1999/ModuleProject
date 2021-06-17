package ru.usachev.LogiWebProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.dao.api.WaypointDAO;
import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.entity.City;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.entity.Waypoint;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WaypointDAOImpl implements WaypointDAO {

    private static Logger logger = Logger.getLogger(WaypointDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Waypoint> getAllWaypoints() {
        Session session = sessionFactory.getCurrentSession();
        List<Waypoint> waypoints = session.createQuery("from Waypoint", Waypoint.class).getResultList();

        try {
            return waypoints;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void saveWaypoint(Waypoint waypoint) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Cargo where name=:cargoName");
        query.setParameter("cargoName", waypoint.getCargo().getName());
        Cargo cargo = (Cargo) query.getSingleResult();
        cargo.addWaypointToWaypoints(waypoint);


        Query query2 = session.createQuery("from City where name=:cityLoadingName ");
        query2.setParameter("cityLoadingName", waypoint.getCityLoading().getName());
        City cityLoading = (City) query2.getSingleResult();
        cityLoading.addWaypointToLoadingWaypointList(waypoint);

        Query query3 = session.createQuery("from City where name=:cityUnloadingName ");
        query3.setParameter("cityUnloadingName", waypoint.getCityUnloading().getName());
        City cityUnloading = (City) query3.getSingleResult();
        cityUnloading.addWaypointToUnloadingWaypointList(waypoint);

        if (waypoint.getOrder() != null) {
            Query query4 = session.createQuery("from Order where id=:orderId");
            query4.setParameter("orderId", waypoint.getOrder().getId());
            Order order = (Order) query4.getSingleResult();
            order.setWaypoints((List<Waypoint>) waypoint);
        }

        session.saveOrUpdate(waypoint);

        if (waypoint.getId() != 0)
            logger.info("update waypoint for cargo: " + waypoint.getCargo().getName());
        else
            logger.info("save waypoint for cargo: " + waypoint.getCargo().getName());
    }

    @Override
    public Waypoint getWaypoint(int id) {
        Session session = sessionFactory.getCurrentSession();
        Waypoint waypoint = session.get(Waypoint.class, id);
        try {
            return waypoint;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void deleteWaypoint(int id) {
        Session session = sessionFactory.getCurrentSession();
        Waypoint waypoint = session.get(Waypoint.class, id);
        Cargo cargo = waypoint.getCargo();
        session.delete(waypoint);

        logger.info("delete waypoint for cargo: " + cargo.getName());
        session.delete(cargo);

        logger.info("delete cargo: " + cargo.getName());
    }

    @Override
    public Waypoint getWaypointByCargoName(String cargoName) {
        Session session = sessionFactory.getCurrentSession();

        Waypoint waypoint = session.createQuery("from Waypoint where cargo.name=:name", Waypoint.class)
                .setParameter("name", cargoName)
                .getSingleResult();
        try {
            return waypoint;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List getWaypointListByIds(List<Integer> ids) {
        Session session = sessionFactory.getCurrentSession();

        List waypoints = new ArrayList<>();

        for (Integer id: ids){
            Waypoint waypoint = session.get(Waypoint.class, id);
            waypoints.add(waypoint);
        }

        try {
            return waypoints;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void saveWaypointEntity(Waypoint waypoint) {
        Session session = sessionFactory.getCurrentSession();

        Waypoint waypointFromDB = session.get(Waypoint.class, waypoint.getId());

        waypointFromDB.setOrder(waypoint.getOrder());

        session.saveOrUpdate(waypointFromDB);
    }

    @Override
    public List<Waypoint> getAllFreeWaypoints() {
        Session session = sessionFactory.getCurrentSession();

        List<Waypoint> waypoints = session.createQuery("from Waypoint where order=null")
                .getResultList();
        return waypoints;
    }

    @Override
    public void deleteWaypointByCargoId(int id) {
        Session session = sessionFactory.getCurrentSession();

        Cargo cargo = session.get(Cargo.class, id);

        if (cargo.getWaypoints().size() > 0){
            Waypoint waypoint = cargo.getWaypoints().get(0);
            waypoint.setCargo(null);
            cargo.setWaypoints(null);
            session.delete(waypoint);
            logger.info("delete waypoint for cargo: " + cargo.getName());
        }
        session.delete(cargo);
        logger.info("delete cargo: " + cargo.getName());
    }
}
