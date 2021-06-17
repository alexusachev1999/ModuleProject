package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.entity.City;

import java.util.List;

/**
 * DAO for operations for table 'cities' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface CityDAO {
    /**
     * Getting city from DB by cityId
     * @param id
     * @return City
     */
    City getCityById(int id);

    /**
     * Getting all cities from DB
     * @return List
     */
    List<City> getCities();

    /**
     * Getting city from DB by cityName
     * @param cityName
     * @return City
     */
    City getCityByName(String cityName);
}
