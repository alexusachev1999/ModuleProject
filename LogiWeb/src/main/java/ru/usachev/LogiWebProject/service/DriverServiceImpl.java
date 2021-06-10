package ru.usachev.LogiWebProject.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.aop.UpdateAnnotation;
import ru.usachev.LogiWebProject.converter.DriverConverter;
import ru.usachev.LogiWebProject.dao.DriverDAO;
import ru.usachev.LogiWebProject.dto.DriverDTO;
import ru.usachev.LogiWebProject.entity.Driver;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    static Logger log = Logger.getLogger(DriverServiceImpl.class.getName());

    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    private DriverConverter driverConverter;

    @Override
    @Transactional
    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverDAO.getAllDrivers();
        List<DriverDTO> driversDTO = driverConverter.convertDriverListToDriverDTOList(drivers);
        return driversDTO;
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void saveDriver(DriverDTO driver) {
        Driver convertedDriver = driverConverter.convertDriverDTOToDriver(driver);
        driverDAO.saveDriver(convertedDriver);
        log.info("Save new driver" + driver.getId());
    }

    @Override
    @Transactional
    public Driver getDriver(int id) {
        return driverDAO.getDriver(id);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void deleteDriver(int id) {
        driverDAO.deleteDriver(id);
    }

    @Override
    @Transactional
    public List<Driver> getDriversByOrderId(int orderId) {
        return driverDAO.getDriversByOrderId(orderId);
    }

    @Override
    @Transactional
    public List<DriverDTO> getValidDriversByOrderId(int orderId) {
        List<Driver> drivers = driverDAO.getValidDriversByOrderId(orderId);
        List<DriverDTO> convertedDrivers = driverConverter.convertDriverListToDriverDTOList(drivers);
        return convertedDrivers;
    }

    @Override
    @Transactional
    public DriverDTO getDriverByUsername(String username) {
        Driver driver = driverDAO.getDriverByUsername(username);
        DriverDTO driverDTO = driverConverter.convertDriverToDriverDTO(driver);
        return driverDTO;
    }

    @Override
    @Transactional
    public List<DriverDTO> getCoDriverListByUsername(String username) {
        List<Driver> driverList = driverDAO.getCoDriverListByUsername(username);

        if (driverList != null){
            List<DriverDTO> driverDTOList = driverConverter.convertDriverListToDriverDTOList(driverList);
            return driverDTOList;
        } else
            return null;
    }

    @Override
    @Transactional
    public List<Driver> getDriverListByIds(List<Integer> driverIds) {
        List<Driver> drivers = driverDAO.getDriverListByIds(driverIds);
        return drivers;
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void saveEntityDriver(Driver driver) {
        driverDAO.saveEntityDriver(driver);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void updateDriver(DriverDTO driver) {
        driverDAO.updateDriver(driver);
    }

    @Override
    @Transactional
    public Driver getDriverByPhoneNumber(String phoneNumber) {
        return driverDAO.getDriverByPhoneNumber(phoneNumber);
    }
}
