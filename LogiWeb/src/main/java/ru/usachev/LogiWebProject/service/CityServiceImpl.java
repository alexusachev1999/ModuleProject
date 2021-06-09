package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.dao.CityDAO;
import ru.usachev.LogiWebProject.entity.City;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityDAO cityDAO;

    @Override
    @Transactional
    public City getCityById(int id) {
        return cityDAO.getCityById(id);
    }

    @Override
    @Transactional
    public List<City> getCities() {
        return cityDAO.getCities();
    }

    @Override
    @Transactional
    public City getCityByName(String cityName) {
        return cityDAO.getCityByName(cityName);
    }
}
