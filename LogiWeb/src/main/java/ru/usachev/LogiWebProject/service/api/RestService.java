package ru.usachev.LogiWebProject.service.api;

import ru.usachev.LogiWebProject.dto.restDTO.DisplayDTO;
/**
 * Service class for getting data from DB by DAO class
 * Convert it by converter class
 * And Send to controller
 *
 * @author Alex Usachev
 */
public interface RestService {
    /**
     * This method return DTO which will converts to JSON and send by JMS
     * @return DisplayDTO
     */
    DisplayDTO getDisplayDTO();
}
