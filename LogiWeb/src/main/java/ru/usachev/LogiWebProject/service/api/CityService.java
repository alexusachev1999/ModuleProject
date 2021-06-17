package ru.usachev.LogiWebProject.service;

import ru.usachev.LogiWebProject.entity.City;

import java.util.List;

public interface CityService {
    public City getCityById(int id);

    public List<City> getCities();

    City getCityByName(String cityName);
}
