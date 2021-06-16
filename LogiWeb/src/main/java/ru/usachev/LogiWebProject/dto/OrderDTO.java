package ru.usachev.LogiWebProject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return id == orderDTO.id && number == orderDTO.number && status == orderDTO.status && Objects.equals(truck, orderDTO.truck) && Objects.equals(drivers, orderDTO.drivers) && Objects.equals(waypoints, orderDTO.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, status, truck, drivers, waypoints);
    }
}
