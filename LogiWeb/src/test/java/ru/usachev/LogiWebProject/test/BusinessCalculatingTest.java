package ru.usachev.LogiWebProject.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.usachev.LogiWebProject.calculating.BusinessCalculating;
import ru.usachev.LogiWebProject.converter.api.WaypointConverter;
import ru.usachev.LogiWebProject.dao.api.DistanceDAO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.api.OrderService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusinessCalculatingTest {
    @InjectMocks
    private BusinessCalculating businessCalculating;

    @Mock
    private OrderService orderService;

    @Mock
    private WaypointConverter waypointConverter;

    @Mock
    private DistanceDAO distanceDAO;

    private List<WaypointDTO> waypointDTOList;

    private List<Waypoint> waypointList;

    @Before
    public void setup(){
        waypointDTOList = SetupClass.setupWaypointDTOList();
        waypointList = SetupClass.setupWaypointListForBusinessCalculating();


        when(waypointConverter.convertWaypointDTOListToWaypointList(waypointDTOList)).thenReturn(waypointList);
    }

    @Test
    public void testCalculateNeedingCapacityByWaypointList(){
        int capacity = businessCalculating.calculateNeedingCapacityByWaypointList(waypointDTOList);
        Assert.assertEquals(1, capacity);
    }

    @Test
    public void testCalculateDistanceOfOrderByOrderId(){
    }

    @Test
    public void testNewMethod(){}
}
