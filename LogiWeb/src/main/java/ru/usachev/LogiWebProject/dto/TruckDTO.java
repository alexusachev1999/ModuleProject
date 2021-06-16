package ru.usachev.LogiWebProject.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.util.Objects;

public class TruckDTO {
    private int id;

    @Pattern(regexp = "[А-Я]{2}\\d{5}", message = "Используйте следующий шаблон \"АБ12345\"")
    private String registrationNumber;

    @Positive(message = "Должно быть > 0")
    @NotNull(message = "Не может быть пустым!")
    private int driverShiftDuration;

    @Positive(message = "Должно быть > 0")
    @NotNull(message = "Не может быть пустым!")
    private int capacity;

    @NotBlank(message = "Не может быть пустым!")
    private String city;

    @NotNull(message = "Не может быть пустым!")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckDTO truckDTO = (TruckDTO) o;
        return id == truckDTO.id && driverShiftDuration == truckDTO.driverShiftDuration && capacity == truckDTO.capacity && state == truckDTO.state && Objects.equals(registrationNumber, truckDTO.registrationNumber) && Objects.equals(city, truckDTO.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, driverShiftDuration, capacity, city, state);
    }
}
