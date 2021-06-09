package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "waypoints")
public class Waypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "city_loading_id")
    private City cityLoading;

    @ManyToOne
    @JoinColumn(name = "city_unloading_id")
    private City cityUnloading;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Waypoint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCityLoading() {
        return cityLoading;
    }

    public void setCityLoading(City cityLoading) {
        this.cityLoading = cityLoading;
    }

    public City getCityUnloading() {
        return cityUnloading;
    }

    public void setCityUnloading(City cityUnloading) {
        this.cityUnloading = cityUnloading;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
