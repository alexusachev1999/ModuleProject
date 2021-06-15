package ru.usachev.display.logiweb.dto;

public class DriverRestDTO {
    private int numberOfDriverNow;

    private int numberOfEnabledDriver;

    private int numberOfDisabledDriver;

    public int getNumberOfDriverNow() {
        return numberOfDriverNow;
    }

    public void setNumberOfDriverNow(int numberOfDriverNow) {
        this.numberOfDriverNow = numberOfDriverNow;
    }

    public int getNumberOfEnabledDriver() {
        return numberOfEnabledDriver;
    }

    public void setNumberOfEnabledDriver(int numberOfEnabledDriver) {
        this.numberOfEnabledDriver = numberOfEnabledDriver;
    }

    public int getNumberOfDisabledDriver() {
        return numberOfDisabledDriver;
    }

    public void setNumberOfDisabledDriver(int numberOfDisabledDriver) {
        this.numberOfDisabledDriver = numberOfDisabledDriver;
    }
}
