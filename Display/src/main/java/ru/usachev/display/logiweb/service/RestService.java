package ru.usachev.display.logiweb.service;

import ru.usachev.display.logiweb.dto.DisplayDTO;
import ru.usachev.display.logiweb.dto.DriverDTO;
import ru.usachev.display.logiweb.dto.OrderDTO;
import ru.usachev.display.logiweb.dto.TruckDTO;

import java.util.List;

public interface RestService {
    List<DriverDTO> getDriversForDisplay();
    List<TruckDTO> getTrucksForDisplay();
    List<OrderDTO> getOrdersForDisplay();
    DisplayDTO getDisplayDTO();
}
