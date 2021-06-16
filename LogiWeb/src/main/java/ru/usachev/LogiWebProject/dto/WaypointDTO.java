package ru.usachev.LogiWebProject.dto;

import java.util.Objects;

public class WaypointDTO {

    private int id;
    private String cargo;
    private String cargoStatus;
    private String cityLoading;
    private String cityUnloading;

    public WaypointDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCityLoading() {
        return cityLoading;
    }

    public void setCityLoading(String cityLoading) {
        this.cityLoading = cityLoading;
    }

    public String getCityUnloading() {
        return cityUnloading;
    }

    public void setCityUnloading(String cityUnloading) {
        this.cityUnloading = cityUnloading;
    }

    public String getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(String cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    @Override
    public String toString() {
        return cargo + " из " + cityLoading + ", в " + cityUnloading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaypointDTO that = (WaypointDTO) o;
        return id == that.id && Objects.equals(cargo, that.cargo) && Objects.equals(cargoStatus, that.cargoStatus) && Objects.equals(cityLoading, that.cityLoading) && Objects.equals(cityUnloading, that.cityUnloading);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargo, cargoStatus, cityLoading, cityUnloading);
    }
}
