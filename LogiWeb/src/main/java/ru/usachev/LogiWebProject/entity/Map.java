package ru.usachev.LogiWebProject.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "map")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "distance")
    private int distance;

    @OneToOne
    @JoinColumn(name = "city1_id")
    private City city1;

    @OneToOne
    @JoinColumn(name = "city2_id")
    private City city2;

    public Map() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return id == map.id && distance == map.distance && Objects.equals(city1, map.city1) && Objects.equals(city2, map.city2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, city1, city2);
    }
}
