package ru.usachev.display.logiweb.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class TruckDTO {
    private int id;

    @NotBlank
    @Pattern(regexp = "[А-Я]{2}\\d{5}", message = "Используйте следующий шаблон \"АБ12345\"")
    private String registrationNumber;

    @Positive
    @NotNull
    private int driverShiftDuration;

    @Positive
    @NotNull
    private int capacity;

    @NotBlank
    private String city;

    @NotNull
    private boolean state;

    public TruckDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getDriverShiftDuration() {
        return driverShiftDuration;
    }

    public void setDriverShiftDuration(int driverShiftDuration) {
        this.driverShiftDuration = driverShiftDuration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Рег. номер: " + registrationNumber;
    }
}
