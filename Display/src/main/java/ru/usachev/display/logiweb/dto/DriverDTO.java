package ru.usachev.display.logiweb.dto;


public class DriverDTO {
    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private int workedHours;

    /* It's help field for driver menu. When driver finish his order it sets into his worked hours */
    private int timeForOrderExecution;

    private boolean workType;

    private String status;

    private String city;

    private String truck;

    private String user;

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

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public int getTimeForOrderExecution() {
        return timeForOrderExecution;
    }

    public void setTimeForOrderExecution(int timeForOrderExecution) {
        this.timeForOrderExecution = timeForOrderExecution;
    }

    public boolean isWorkType() {
        return workType;
    }

    public void setWorkType(boolean workType) {
        this.workType = workType;
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
}
