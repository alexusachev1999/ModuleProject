package ru.usachev.LogiWebProject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.usachev.LogiWebProject.dao.api.CityDAO;
import ru.usachev.LogiWebProject.entity.City;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CityDAOImpl implements CityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public City getCityById(int id) {
        Session session = sessionFactory.getCurrentSession();
        City city = session.get(City.class, id);

        try{
            return city;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<City> getCities() {
        Session session = sessionFactory.getCurrentSession();
        List<City> cities = session.createQuery("from City").getResultList();

        try {
            return cities;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public City getCityByName(String cityName) {
        Session session = sessionFactory.getCurrentSession();

        City city = (City) session.createQuery("FROM City c where c.name=:cityName")
                .setParameter("cityName", cityName)
                .getSingleResult();
        try {
            return city;
        } catch (NoResultException e){
            return null;
        }
    }
}
