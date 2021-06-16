package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cargoes")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "cargo")
    private List<Waypoint> waypoints;

    public Cargo() {
    }

    public void addWaypointToWaypoints(Waypoint waypoint){
        if (waypoints == null)
            waypoints = new ArrayList<>();
        waypoints.add(waypoint);
        waypoint.setCargo(this);
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return id == cargo.id && number == cargo.number && weight == cargo.weight && Objects.equals(name, cargo.name) && Objects.equals(status, cargo.status) && Objects.equals(waypoints, cargo.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, weight, status, waypoints);
    }
}
