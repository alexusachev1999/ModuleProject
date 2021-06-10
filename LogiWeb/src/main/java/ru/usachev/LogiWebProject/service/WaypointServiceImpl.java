package ru.usachev.LogiWebProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.usachev.LogiWebProject.aop.UpdateAnnotation;
import ru.usachev.LogiWebProject.converter.WaypointConverter;
import ru.usachev.LogiWebProject.dao.WaypointDAO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaypointServiceImpl implements WaypointService{

    @Autowired
    private WaypointDAO waypointDAO;

    @Autowired
    private WaypointConverter waypointConverter;

    @Override
    @Transactional
    public List<WaypointDTO> getAllWaypoints() {
        List<Waypoint> waypoints = waypointDAO.getAllWaypoints();
        List<WaypointDTO> waypointsDTO = new ArrayList<>();
        for(Waypoint waypoint: waypoints){
            waypointsDTO.add(waypointConverter.convertWaypointToWaypointDTO(waypoint));
        }
        return waypointsDTO;
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void saveWaypoint(WaypointDTO waypoint) {
        Waypoint convertedWaypoint = waypointConverter.convertWaypointDTOToWaypoint(waypoint);
        waypointDAO.saveWaypoint(convertedWaypoint);
    }

    @Override
    @Transactional
    public Waypoint getWaypoint(int id) {
        return waypointDAO.getWaypoint(id);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void deleteWaypoint(int id) {
        waypointDAO.deleteWaypoint(id);
    }

    @Override
    @Transactional
    public WaypointDTO getWaypointByCargoName(String waypointToString) {
        return waypointConverter
                .convertWaypointToWaypointDTO(waypointDAO.getWaypointByCargoName(waypointToString));
    }

    @Override
    @Transactional
    public List getWaypointListByIds(List<Integer> ids) {
        List waypoints = waypointDAO.getWaypointListByIds(ids);
        List waypointsDTO = waypointConverter.convertWaypointListToWaypointDTOList(waypoints);
        return waypointsDTO;
    }

    @Override
    @Transactional
    public List<WaypointDTO> getAllFreeWaypoints() {
        List<Waypoint> freeWaypoints = waypointDAO.getAllFreeWaypoints();
        return waypointConverter.convertWaypointListToWaypointDTOList(freeWaypoints);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void saveWaypointEntity(Waypoint waypoint) {
        waypointDAO.saveWaypointEntity(waypoint);
    }

    @Override
    @Transactional
    @UpdateAnnotation
    public void deleteWaypointByCargoId(int id) {
        waypointDAO.deleteWaypointByCargoId(id);
    }
}
