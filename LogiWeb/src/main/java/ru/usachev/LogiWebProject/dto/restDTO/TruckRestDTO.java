package ru.usachev.LogiWebProject.dto.restDTO;

public class TruckRestDTO {
    private int numberOfTruckNow;

    private int numberOfFreeTruck;

    private int numberOfInOrderTruck;

    private int numberOfBrokenTruck;

    public int getNumberOfTruckNow() {
        return numberOfTruckNow;
    }

    public void setNumberOfTruckNow(int numberOfTruckNow) {
        this.numberOfTruckNow = numberOfTruckNow;
    }

    public int getNumberOfFreeTruck() {
        return numberOfFreeTruck;
    }

    public void setNumberOfFreeTruck(int numberOfFreeTruck) {
        this.numberOfFreeTruck = numberOfFreeTruck;
    }

    public int getNumberOfInOrderTruck() {
        return numberOfInOrderTruck;
    }

    public void setNumberOfInOrderTruck(int numberOfInOrderTruck) {
        this.numberOfInOrderTruck = numberOfInOrderTruck;
    }

    public int getNumberOfBrokenTruck() {
        return numberOfBrokenTruck;
    }

    public void setNumberOfBrokenTruck(int numberOfBrokenTruck) {
        this.numberOfBrokenTruck = numberOfBrokenTruck;
    }
}
