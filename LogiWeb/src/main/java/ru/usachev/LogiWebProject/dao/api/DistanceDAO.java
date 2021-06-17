package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.entity.Distance;

import java.util.List;
/**
 * DAO for operations for table 'map' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface DistanceDAO {
    /**
     * Getting all distances from DB for all cities
     * @return List
     */
    List<Distance> getAllDistances();
}
