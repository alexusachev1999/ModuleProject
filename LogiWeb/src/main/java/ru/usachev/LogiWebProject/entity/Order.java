package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "status")
    private boolean status;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Waypoint> waypoints;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Driver> drivers;

    public Order() {
    }

    public void addDriverToDriverList(Driver driver){
        if (drivers == null)
            drivers = new ArrayList<>();
        drivers.add(driver);
        driver.setOrder(this);
    }

    public void addWaypointToWaypointList(Waypoint waypoint){
        if (waypoints == null)
            waypoints = new ArrayList<>();
        waypoints.add(waypoint);
        waypoint.setOrder(this);
    }

    public Order(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
