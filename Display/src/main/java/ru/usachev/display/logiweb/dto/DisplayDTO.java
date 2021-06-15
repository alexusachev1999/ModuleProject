package ru.usachev.display.logiweb.dto;

import java.io.Serializable;
import java.util.List;

public class DisplayDTO implements Serializable {
    private DriverRestDTO driverRestDTO;
    private TruckRestDTO truckRestDTO;
    private List<OrderDTO> orderDTOList;

    public DriverRestDTO getDriverRestDTO() {
        return driverRestDTO;
    }

    public void setDriverRestDTO(DriverRestDTO driverRestDTO) {
        this.driverRestDTO = driverRestDTO;
    }

    public TruckRestDTO getTruckRestDTO() {
        return truckRestDTO;
    }

    public void setTruckRestDTO(TruckRestDTO truckRestDTO) {
        this.truckRestDTO = truckRestDTO;
    }

    public List<OrderDTO> getOrderDTOList() {
        return orderDTOList;
    }

    public void setOrderDTOList(List<OrderDTO> orderDTOList) {
        this.orderDTOList = orderDTOList;
    }
}
