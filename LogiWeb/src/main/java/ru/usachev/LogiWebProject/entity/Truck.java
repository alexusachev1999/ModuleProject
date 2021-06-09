package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "driver_shift_duration")
    private int driverShiftDuration;

    @Column(name = "state")
    private boolean state;

    @Column(name = "capacity")
    private int capacity;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "truck")
    private List<Driver> drivers;

    @OneToOne(mappedBy = "truck")
    private Order order;

    public Truck() {
    }

    public Truck(String registrationNumber, int driverShiftDuration, boolean state, int capacity) {
        this.registrationNumber = registrationNumber;
        this.driverShiftDuration = driverShiftDuration;
        this.state = state;
        this.capacity = capacity;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
