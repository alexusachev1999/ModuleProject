package ru.usachev.LogiWebProject.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CargoDTO {
    private int id;

    @Positive(message = "Номер должен быть больше нуля")
    private int number;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Positive(message = "Вес должен быть положительным")
    private int weight;

    @NotBlank(message = "Статус не может быть пустым")
    private String status;

    public CargoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
