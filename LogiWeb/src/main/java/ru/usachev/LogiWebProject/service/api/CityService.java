package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.entity.City;

import java.util.List;

/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface CityService {

    List<City> getCities();

    City getCityByName(String cityName);
}
