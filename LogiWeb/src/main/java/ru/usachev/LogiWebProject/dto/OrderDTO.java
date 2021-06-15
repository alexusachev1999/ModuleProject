package ru.usachev.LogiWebProject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDTO {
    private int id;
    private int number;
    private boolean status;
    private String truck;
    private List<DriverDTO> drivers;
    @NotBlank(message = "Выберите точку(и)")
    private List<WaypointDTO> waypoints;

    public OrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public List<DriverDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDTO> drivers) {
        this.drivers = drivers;
    }

    public List<WaypointDTO> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WaypointDTO> waypoints) {
        this.waypoints = waypoints;
    }
}
