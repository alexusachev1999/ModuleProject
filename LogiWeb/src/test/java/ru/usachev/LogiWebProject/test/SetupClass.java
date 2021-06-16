package ru.usachev.LogiWebProject.test;

import ru.usachev.LogiWebProject.dto.*;
import ru.usachev.LogiWebProject.entity.*;

import java.util.ArrayList;
import java.util.List;

public class SetupClass {

    public static City setupCity(){
        City city = new City();
        city.setName("test");
        return city;
    }

    public static City setupCityOfLoading() {
        City city = new City();
        city.setName("CityOfLoading");
        return city;
    }

    public static City setupCityOfUnloading() {
        City city = new City();
        city.setName("CityOfUnloading");
        return city;
    }

    public static User setupUser(){
        User user = new User();
        user.setUsername("test");
        return user;
    }

    public static Truck setupTruck(){
        Truck truck = new Truck();
        truck.setId(1);
        truck.setRegistrationNumber("АА12345");
        truck.setCapacity(9);
        truck.setState(true);
        truck.setDriverShiftDuration(9);
        truck.setCity(setupCity());
        truck.setOrder(setupNewOrder());
        return truck;
    }

    public static TruckDTO setupTruckDTO() {
        TruckDTO truckDTO = new TruckDTO();
        truckDTO.setId(1);
        truckDTO.setRegistrationNumber("АА12345");
        truckDTO.setCapacity(9);
        truckDTO.setState(true);
        truckDTO.setDriverShiftDuration(9);
        truckDTO.setCity(setupCity().getName());
        return truckDTO;
    }

    public static List<Truck> setupTruckList(){
        List<Truck> truckList = new ArrayList<>();
        truckList.add(setupTruck());
        return truckList;
    }

    public static List<TruckDTO> setupTruckDTOList(){
        List<TruckDTO> truckDTOList = new ArrayList<>();
        truckDTOList.add(setupTruckDTO());
        return truckDTOList;
    }

    public static Order setupNewOrder(){
        Order order = new Order();
        return order;
    }

    public static Driver setupDriver(){
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("test");
        driver.setSurname("test");
        driver.setStatus("test");
        driver.setPhoneNumber("+7-953-146-23-60");
        driver.setTimeForOrderExecution(0);
        driver.setWorkType(true);
        driver.setTruck(setupTruck());
        driver.setOrder(setupNewOrder());
        driver.setWorkedHours(0);
        driver.setUser(setupUser());
        driver.setCity(setupCity());
        driver.setEnabled(false);
        return driver;
    }

    public static DriverDTO setupDriverDTO(){
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(1);
        driverDTO.setName("test");
        driverDTO.setSurname("test");
        driverDTO.setStatus("test");
        driverDTO.setPhoneNumber("+7-953-146-23-60");
        driverDTO.setTimeForOrderExecution(0);
        driverDTO.setWorkType(true);
        driverDTO.setTruck(setupTruck().getRegistrationNumber());
        driverDTO.setWorkedHours(0);
        driverDTO.setUser(setupUser().getUsername());
        driverDTO.setCity(setupCity().getName());
        return driverDTO;
    }

    public static Cargo setupCargo(){
        Cargo cargo = new Cargo();
        cargo.setId(1);
        cargo.setName("test");
        cargo.setStatus("test");
        cargo.setWeight(100);
        cargo.setNumber(1000);
        cargo.setWaypoints(null);
        return cargo;
    }

    public static CargoDTO setupCargoDTO(){
        CargoDTO cargoDTO = new CargoDTO();
        cargoDTO.setId(1);
        cargoDTO.setName("test");
        cargoDTO.setStatus("test");
        cargoDTO.setWeight(100);
        cargoDTO.setNumber(1000);
        return cargoDTO;
    }

    public static Order setupOrder(){
        Order order = new Order();
        order.setId(1);
        order.setNumber(1);
        order.setTruck(setupTruck());
        order.setDrivers(setupDriverList());
        order.setStatus(true);
        order.setWaypoints(setupWaypointList());
        return order;
    }

    public static OrderDTO setupOrderDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1);
        orderDTO.setNumber(1);
        orderDTO.setTruck(setupTruck().getRegistrationNumber());
        orderDTO.setDrivers(setupDriverDTOList());
        orderDTO.setStatus(true);
        orderDTO.setWaypoints(setupWaypointDTOList());
        return orderDTO;
    }

    public static List<Driver> setupDriverList(){
        List<Driver> driverList = new ArrayList<>();
        Driver driver = new Driver();
        driverList.add(driver);
        return driverList;
    }

    public static List<DriverDTO> setupDriverDTOList(){
        List<DriverDTO> driverDTOList = new ArrayList<>();
        DriverDTO driverDTO = new DriverDTO();
        driverDTOList.add(driverDTO);
        return driverDTOList;
    }

    public static List<Waypoint> setupWaypointList(){
        List<Waypoint> waypointList = new ArrayList<>();
        Waypoint waypoint = new Waypoint();
        waypointList.add(waypoint);
        return waypointList;
    }

    public static List<WaypointDTO> setupWaypointDTOList(){
        List<WaypointDTO> waypointDTOList = new ArrayList<>();
        WaypointDTO waypointDTO = new WaypointDTO();
        waypointDTOList.add(waypointDTO);
        return waypointDTOList;
    }

    public static List<Order> setupOrderList(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(setupOrder());
        return orderList;
    }

    public static List<OrderDTO> setupOrderDTOList(){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList.add(setupOrderDTO());
        return orderDTOList;
    }

    public static Waypoint setupWaypoint() {
        Waypoint waypoint = new Waypoint();
        waypoint.setId(1);
        waypoint.setCityUnloading(setupCityOfUnloading());
        waypoint.setCityLoading(setupCityOfLoading());
        waypoint.setCargo(setupCargo());
        return waypoint;
    }

    public static WaypointDTO setupWaypointDTO() {
        WaypointDTO waypoint = new WaypointDTO();
        waypoint.setId(1);
        waypoint.setCityUnloading(setupCityOfUnloading().getName());
        waypoint.setCityLoading(setupCityOfLoading().getName());
        waypoint.setCargo(setupCargo().getName());
        waypoint.setCargoStatus(setupCargo().getStatus());
        return waypoint;
    }
}
