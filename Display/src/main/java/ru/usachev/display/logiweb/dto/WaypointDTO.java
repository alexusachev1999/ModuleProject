package ru.usachev.display.logiweb.dto;

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
}
