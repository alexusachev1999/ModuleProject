package ru.usachev.LogiWebProject.converter;//package ru.usachev.LogiWebProject.converter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomCollectionEditor;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import ru.usachev.LogiWebProject.dto.WaypointDTO;
//import ru.usachev.LogiWebProject.entity.Waypoint;
//import ru.usachev.LogiWebProject.service.WaypointService;
//
//import java.util.List;
//
//@Component
//public class StringArrayToWaypointDTOList implements Converter<String[], List<WaypointDTO>>  {
//
//    @Autowired
//    private WaypointService waypointService;
//
////    @Override
////    public List<WaypointDTO> convert(String[] s) {
////        int [] ids = new int[s.length];
////        int i = 0;
////        for (String string: s){
////            ids [i] = Integer.parseInt(string);
////            i++;
////        }
////        return waypointService.getWaypointListByIds(ids);
////    }
//
//}
