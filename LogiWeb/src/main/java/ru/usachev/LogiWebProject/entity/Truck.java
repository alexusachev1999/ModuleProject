package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "is_enabled")
    private boolean isEnabled;

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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return id == truck.id && driverShiftDuration == truck.driverShiftDuration && state == truck.state && isEnabled == truck.isEnabled && capacity == truck.capacity && Objects.equals(registrationNumber, truck.registrationNumber) && Objects.equals(city, truck.city) && Objects.equals(drivers, truck.drivers) && Objects.equals(order, truck.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, driverShiftDuration, state, isEnabled, capacity, city, drivers, order);
    }
}
