package ru.usachev.LogiWebProject.dto;

import javax.validation.constraints.*;
import java.util.Objects;

public class DriverDTO {
    private int id;

    @Pattern(regexp = "[А-Я]{1}[а-я]*", message = "Например - \"Александр\"")
    private String name;

    @Pattern(regexp = "[А-Я]{1}[а-я]*", message = "Например - \"Иванов\"")
    private String surname;

    @Pattern(regexp = "^\\+[7][-]\\d{3}-\\d{3}-\\d{2}-\\d{2}$",
            message = "Например - +7-953-146-23-60")
    private String phoneNumber;


    @Max(value = 176, message = "Водитель не может работать больше 176 часов в месяц")
    @PositiveOrZero(message = "Рабочие часы не могут быть отрицательными")
    private int workedHours;

    /* It's help field for driver menu. When driver finish his order it sets into his worked hours */
    private int timeForOrderExecution;

    @NotNull
    private boolean workType;

    @NotBlank
    private String status;

    @NotBlank
    private String city;

    private String truck;

    @NotBlank
    private String user;

    public DriverDTO() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public boolean isWorkType() {
        return workType;
    }

    public void setWorkType(boolean workType) {
        this.workType = workType;
    }

    public int getTimeForOrderExecution() {
        return timeForOrderExecution;
    }

    public void setTimeForOrderExecution(int timeForOrderExecution) {
        this.timeForOrderExecution = timeForOrderExecution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDTO driverDTO = (DriverDTO) o;
        return id == driverDTO.id && workedHours == driverDTO.workedHours && timeForOrderExecution == driverDTO.timeForOrderExecution && workType == driverDTO.workType && Objects.equals(name, driverDTO.name) && Objects.equals(surname, driverDTO.surname) && Objects.equals(phoneNumber, driverDTO.phoneNumber) && Objects.equals(status, driverDTO.status) && Objects.equals(city, driverDTO.city) && Objects.equals(truck, driverDTO.truck) && Objects.equals(user, driverDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber, workedHours, timeForOrderExecution, workType, status, city, truck, user);
    }
}
