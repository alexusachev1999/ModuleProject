package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "map")
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @OneToOne
    @JoinColumn(name = "city1_id")
    private City city1;

    @OneToOne
    @JoinColumn(name = "city2_id")
    private City city2;

    @Column(name = "distance")
    private int distance;

    public Distance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
