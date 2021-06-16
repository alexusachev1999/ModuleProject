package ru.usachev.LogiWebProject.calculating;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.converter.api.WaypointConverter;
import ru.usachev.LogiWebProject.dao.DistanceDAO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Distance;
import ru.usachev.LogiWebProject.entity.Order;
import ru.usachev.LogiWebProject.entity.Waypoint;
import ru.usachev.LogiWebProject.service.OrderService;

import java.util.*;

@Component
public class BusinessCalculating {

    private static Logger logger = Logger.getLogger(BusinessCalculating.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private WaypointConverter waypointConverter;

    @Autowired
    private DistanceDAO distanceDAO;

    private static final int driverWorkedHoursLimit = 176;

    private static final double approximateSpeedOfTruckInKmPerHour = 80;



    public int calculateNeedingCapacityByWaypointList(List<WaypointDTO> waypointsDTO) {
        int capacity = 0;

        List<Waypoint> waypoints = waypointConverter.convertWaypointDTOListToWaypointList(waypointsDTO);

        List<WaypointBusiness> waypointBusinessList = convertWaypointListIntoWaypointBusinessList(waypoints);

        for (WaypointBusiness waypoint: waypointBusinessList){
            if (waypoint.isLoading())
                capacity += waypoint.getCargo().getWeight();
        }

        // Cast capacity from kg to t
        double capacityDouble = ((double) capacity)/1000;
        capacity = (int) Math.round(capacityDouble);

        logger.info("capacity for order is: " + capacity);
        return capacity;

    }


    /* Max number of hours which driver may has before execution the order */
    public int calculateDriverWorkedHoursLimitForOrderByOrderId(int orderId) {
        return driverWorkedHoursLimit - calculateApproximateTimeOfOrderExecution(orderId);
    }

    public int calculateApproximateTimeOfOrderExecution(int orderId){
        int distanceOfOrderInKm = calculateDistanceOfOrderByOrderId(orderId);

        double approximateTimeInHours = distanceOfOrderInKm / approximateSpeedOfTruckInKmPerHour;

        logger.info("approximate time for order with id: " + orderId + " is " + approximateTimeInHours);
        return (int) Math.round(approximateTimeInHours);
    }

    private int calculateDistanceOfOrderByOrderId(int orderId) {
        Order order = orderService.getOrder(orderId);
        List<Waypoint> waypoints = order.getWaypoints();

        /* Get list if ids for loading cities and unloading */
        List<Integer> cityLoadingIds = new ArrayList<>();
        List<Integer> cityUnloadingIds = new ArrayList<>();
        for (Waypoint waypoint: waypoints) {
            cityLoadingIds.add(waypoint.getCityLoading().getId());
            cityUnloadingIds.add(waypoint.getCityUnloading().getId());
        }

        Collections.sort(cityLoadingIds);
        Collections.sort(cityUnloadingIds);

        List<Distance> distances = distanceDAO.getAllDistances();
        int distanceForOrder = 0;

        /* Get max distance if it's a straight way (from SPb to Sochi) */
        Distance distanceInStraightWay = distances
                    .stream()
                    .filter(distance -> distance.getCity1().getId() == (cityLoadingIds.get(0)))
                    .filter(distance -> distance.getCity2().getId() == (cityUnloadingIds.get(cityUnloadingIds.size() - 1)))
                    .findFirst()
                    .get();

        /* Get max distance if it's a reverse way (from Sochi to SPb) */
        Distance distanceInReverseWay = distances
                .stream()
                .filter(distance -> distance.getCity1().getId() == (cityLoadingIds.get(cityLoadingIds.size() - 1)))
                .filter(distance -> distance.getCity2().getId() == (cityUnloadingIds.get(0)))
                .findFirst()
                .get();

        /* Which one is more is right shortest way */
        if (distanceInStraightWay.getDistance() > distanceInReverseWay.getDistance())
            distanceForOrder = distanceInStraightWay.getDistance();
        else
            distanceForOrder = distanceInReverseWay.getDistance();

        logger.info("shortest distance for order №" + order.getNumber() + " is: " + distanceForOrder);

        return distanceForOrder;
    }


    private List<WaypointBusiness> convertWaypointListIntoWaypointBusinessList(List<Waypoint> waypoints){
        List<WaypointBusiness> waypointBusinessList = new ArrayList<>();

        /* Set waypoint for WaypointBusiness which considers type of loading */
        for (Waypoint waypoint: waypoints){
            WaypointBusiness waypointBusinessLoading = new WaypointBusiness();
            waypointBusinessLoading.setLoading(true);
            waypointBusinessLoading.setCargo(waypoint.getCargo());
            waypointBusinessLoading.setCity(waypoint.getCityLoading());

            WaypointBusiness waypointBusinessUnloading = new WaypointBusiness();
            waypointBusinessUnloading.setLoading(false);
            waypointBusinessUnloading.setCargo(waypoint.getCargo());
            waypointBusinessUnloading.setCity(waypoint.getCityUnloading());

            waypointBusinessList.add(waypointBusinessLoading);
            waypointBusinessList.add(waypointBusinessUnloading);
        }

        return waypointBusinessList;
    }
}
