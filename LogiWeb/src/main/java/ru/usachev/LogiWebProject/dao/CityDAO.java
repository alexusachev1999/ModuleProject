package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.entity.City;

import java.util.List;

public interface CityDAO {
    public City getCityById(int id);

    public List<City> getCities();

    City getCityByName(String cityName);
}
