package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "cityLoading")
    private List<Waypoint> loadingWaypoints;

    @OneToMany(mappedBy = "cityUnloading")
    private List<Waypoint> unloadingWaypoints;

    @OneToMany(mappedBy = "city")
    private List<Truck> trucks;

    @OneToMany(mappedBy = "city")
    private List<Driver> drivers;

    public City() {
    }

    public void addDriverToDriverList(Driver driver){
        if (drivers == null)
            drivers = new ArrayList<>();
        drivers.add(driver);
        driver.setCity(this);
    }

    public void addTruckToTruckList(Truck truck) {
        if (trucks == null)
            trucks = new ArrayList<>();
        trucks.add(truck);
        truck.setCity(this);
    }

    public void addWaypointToLoadingWaypointList(Waypoint waypoint) {
        if (loadingWaypoints == null)
            loadingWaypoints = new ArrayList<>();
        loadingWaypoints.add(waypoint);
        waypoint.setCityLoading(this);
    }

    public void addWaypointToUnloadingWaypointList(Waypoint waypoint) {
        if (unloadingWaypoints == null)
            unloadingWaypoints = new ArrayList<>();
        unloadingWaypoints.add(waypoint);
        waypoint.setCityUnloading(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Waypoint> getLoadingWaypoints() {
        return loadingWaypoints;
    }

    public void setLoadingWaypoints(List<Waypoint> loadingWaypoints) {
        this.loadingWaypoints = loadingWaypoints;
    }

    public List<Waypoint> getUnloadingWaypoints() {
        return unloadingWaypoints;
    }

    public void setUnloadingWaypoints(List<Waypoint> unloadingWaypoints) {
        this.unloadingWaypoints = unloadingWaypoints;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
