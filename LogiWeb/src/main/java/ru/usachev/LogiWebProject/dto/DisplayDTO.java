package ru.usachev.LogiWebProject.dto;

import java.io.Serializable;
import java.util.List;

public class DisplayDTO implements Serializable {
    private List<DriverDTO> driverDTOList;
    private List<TruckDTO> truckDTOList;
    private List<OrderDTO> orderDTOList;

    public List<DriverDTO> getDriverDTOList() {
        return driverDTOList;
    }

    public void setDriverDTOList(List<DriverDTO> driverDTOList) {
        this.driverDTOList = driverDTOList;
    }

    public List<TruckDTO> getTruckDTOList() {
        return truckDTOList;
    }

    public void setTruckDTOList(List<TruckDTO> truckDTOList) {
        this.truckDTOList = truckDTOList;
    }

    public List<OrderDTO> getOrderDTOList() {
        return orderDTOList;
    }

    public void setOrderDTOList(List<OrderDTO> orderDTOList) {
        this.orderDTOList = orderDTOList;
    }
}
