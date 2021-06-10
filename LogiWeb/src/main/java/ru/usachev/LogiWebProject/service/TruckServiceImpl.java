package ru.usachev.LogiWebProject.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.aop.UpdateAnnotation;
import ru.usachev.LogiWebProject.controller.admin.AdminOrderController;
import ru.usachev.LogiWebProject.converter.TruckConverter;
import ru.usachev.LogiWebProject.dao.TruckDAO;
import ru.usachev.LogiWebProject.dto.TruckDTO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Truck;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TruckServiceImpl implements TruckService{

    @Autowired
    private TruckDAO truckDAO;

    @Autowired
    private TruckConverter truckConverter;

    @Override
    @Transactional
    public List<TruckDTO> getAllTrucks() {
        List<Truck> trucks = truckDAO.getAllTrucks();
        List<TruckDTO> trucksDTO = truckConverter.convertTruckListToTruckDTOList(trucks);
        return trucksDTO;
    }

    @Override
    @Transactional
    public Truck getTruck(int id) {
        return truckDAO.getTruck(id);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void deleteTruck(int id) {
        truckDAO.deleteTruck(id);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void saveTruck(TruckDTO truck) {
        Truck convertedTruck = truckConverter.convertTruckDTOToTruck(truck);
        truckDAO.saveTruck(convertedTruck);
    }

    @Override
    @Transactional
    public List<TruckDTO> getValidTrucksForOrder(List<WaypointDTO> waypoints) {
        List<Truck> trucks = truckDAO.getValidTrucksForOrder(waypoints);
        List<TruckDTO> convertedTrucks = new ArrayList<>();
        for (Truck truck: trucks){
            convertedTrucks.add(truckConverter.convertTruckToTruckDTO(truck));
        }
        return convertedTrucks;
    }

    @Override
    @Transactional
    public Truck getTruckByRegistrationNumber(String registrationNumber) {
        return truckDAO.getTruckByRegistrationNumber(registrationNumber);
    }

    @Override
    @Transactional
    public TruckDTO getTruckByOrderId(int orderId) {
        TruckDTO truckDTO = truckConverter.convertTruckToTruckDTO(truckDAO.getTruckByOrderId(orderId));
        return truckDTO;
    }

    @Override
    @Transactional
    public TruckDTO getTruckByOrderNumber(int number) {
        Truck truck = truckDAO.getTruckByOrderNumber(number);
        TruckDTO truckDTO = truckConverter.convertTruckToTruckDTO(truck);

        return truckDTO;
    }

    @Override
    @Transactional
    public Truck getTruckByDriverId(int id) {
        return truckDAO.getTruckByDriverId(id);
    }
}
