package ru.usachev.LogiWebProject.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.usachev.LogiWebProject.converter.DriverConverter;
import ru.usachev.LogiWebProject.converter.OrderConverterImpl;
import ru.usachev.LogiWebProject.converter.WaypointConverter;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.OrderDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.*;
import ru.usachev.LogiWebProject.service.TruckService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderConverterTest {
    @Mock
    private TruckService truckService;

    @Mock
    private DriverConverter driverConverter;

    @Mock
    private WaypointConverter waypointConverter;

    @InjectMocks
    private OrderConverterImpl orderConverter;

    private OrderDTO orderDTO;

    private Order order;

    private Truck truck;

    private List<Driver> driverList;

    private List<DriverDTO> driverDTOList;

    private List<Waypoint> waypointList;

    private List<WaypointDTO> waypointDTOList;

    private List<Order> orderList;

    private List<OrderDTO> orderDTOList;

    @Before
    public void setup(){
        order = SetupClass.setupOrder();
        orderDTO = SetupClass.setupOrderDTO();

        driverList = SetupClass.setupDriverList();
        driverDTOList = SetupClass.setupDriverDTOList();

        truck = SetupClass.setupTruck();

        waypointList = SetupClass.setupWaypointList();
        waypointDTOList = SetupClass.setupWaypointDTOList();


        orderList = SetupClass.setupOrderList();
        orderDTOList = SetupClass.setupOrderDTOList();

        when(truckService.getTruckByRegistrationNumber("АА12345")).thenReturn(truck);
        when(driverConverter.convertDriverDTOListToDriverList(orderDTO.getDrivers())).thenReturn(driverList);
        when(driverConverter.convertDriverListToDriverDTOList(order.getDrivers())).thenReturn(driverDTOList);
        when(waypointConverter.convertWaypointDTOListToWaypointList(orderDTO.getWaypoints())).thenReturn(waypointList);
        when(waypointConverter.convertWaypointListToWaypointDTOList(order.getWaypoints())).thenReturn(waypointDTOList);
    }

    @Test
    public void TestOfConversionOrderToOrderDTO(){
        OrderDTO convertedOrderDTO = orderConverter.convertOrderToOrderDTO(order);
        Assert.assertEquals(orderDTO, convertedOrderDTO);
    }

    @Test
    public void TestOfConversionOrderDTOToOrder(){
        Order convertedOrder = orderConverter.convertOrderDTOToOrder(orderDTO);
        Assert.assertEquals(order, convertedOrder);
    }

    @Test
    public void TestOfConversionOrderListToOrderDTOList(){
        List<OrderDTO> convertedOrderDTOList = orderConverter.convertOrderListToOrderDTOList(orderList);
        Assert.assertEquals(orderDTOList.size(), convertedOrderDTOList.size());
        Assert.assertEquals(orderDTOList.get(0), convertedOrderDTOList.get(0));
    }
}
