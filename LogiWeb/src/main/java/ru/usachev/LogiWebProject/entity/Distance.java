package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return id == distance1.id && distance == distance1.distance && Objects.equals(city1, distance1.city1) && Objects.equals(city2, distance1.city2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city1, city2, distance);
    }
}
