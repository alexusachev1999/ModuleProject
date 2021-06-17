package ru.usachev.LogiWebProject.calculating;

import ru.usachev.LogiWebProject.entity.Cargo;
import ru.usachev.LogiWebProject.entity.City;

/**
 * Utility class for BusinessCalculating class
 */
public class WaypointBusiness {
    private City city;
    private boolean isLoading;
    private Cargo cargo;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
