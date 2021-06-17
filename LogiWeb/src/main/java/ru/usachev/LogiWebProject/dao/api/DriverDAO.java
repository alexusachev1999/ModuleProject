package ru.usachev.LogiWebProject.dao.api;

import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.dto.restDTO.DriverRestDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import java.util.List;
/**
 * DAO for operations for table 'drivers' from DB 'logiWeb'
 * @author Alex Usachev
 */
public interface DriverDAO {
    /**
     * Getting all drivers from DB
     * @return List
     */
    List<Driver> getAllDrivers();

    /**
     * Getting all drivers who have enabled = true
     * @return List
     */
    List<Driver> getAllEnabledDrivers();

    /**
     * Save driver to DB
     * @param driver
     */
    void saveDriver(Driver driver);

    /**
     * Getting driver from DB by driverId
     * @param id
     * @return Driver
     */
    Driver getDriver(int id);

    /**
     * Setting driver enabled = false and updating him by driverId
     * @param id
     */
    void deleteDriver(int id);

    /**
     * Getting all drivers from DB who execute order with this orderId
     * @param orderId
     * @return List
     */
    List<Driver> getDriversByOrderId(int orderId);

    /**
     * Getting all valid drivers for order with this orderId
     * Criteria of valid driver:
     * 1. Driver doesn't execute any order now
     * 2. Driver is enabled (enabled = true)
     * 3. Driver has the same city as the truck for this order
     * 4. Driver has enough free worked hours in this month for executing the order
     * @param orderId
     * @return List
     */
    List<Driver> getValidDriversByOrderId(int orderId);

    /**
     * Getting driver from DB who has this 'username'
     * @param username
     * @return Driver
     */
    Driver getDriverByUsername(String username);

    /**
     * Getting all drivers who work with driver who has this 'username' on the one order
     * @param username
     * @return List
     */
    List<Driver> getCoDriverListByUsername(String username);

    /**
     * Getting drivers from DB by their driverIds.
     * This method uses for AdminDriverController because of jsp can't return List of Driver
     * @param driverIds
     * @return List
     */
    List<Driver> getDriverListByIds(List<Integer> driverIds);

    /**
     * Saving entity Driver to DB
     * This method uses for AdminOrderController to save driver to the order
     * @param driver
     */
    void saveEntityDriver(Driver driver);

    /**
     * Updating driver to DB
     * @param driver
     */
    void updateDriver(DriverDTO driver);

    /**
     * Getting driver from DB by him phoneNumber
     * @param phoneNumber
     * @return Driver
     */
    Driver getDriverByPhoneNumber(String phoneNumber);

    /**
     * Getting all actual statistic about drivers
     * 1. Number of all enabled drivers
     * 2. Number of free drivers
     * 3. Number of drivers who work now
     * @return DriverRestDTO
     */
    DriverRestDTO getDriverRestDTO();

    /**
     * This method zeroing drivers worked hours one time in month.
     * It works with Spring Schedules
     */
    void zeroingWorkHoursOfDriversOneTimeInMonth();

    /**
     * This method get Drivers from help table 'completed_orders' which
     * saves drivers and trucks Ids for completed orders.
     * It helps for viewing data in front
     * @param id
     * @return List
     */
    List<Driver> getDriversForCompletedOrderByOrderId(int id);
}
