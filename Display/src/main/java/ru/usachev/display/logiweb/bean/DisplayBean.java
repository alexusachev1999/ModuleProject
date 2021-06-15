package ru.usachev.display.logiweb.bean;

import ru.usachev.display.logiweb.dto.DisplayDTO;
import ru.usachev.display.logiweb.service.RestService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DisplayBean {
    @Inject
    private RestService restService;

    public DisplayDTO getDisplayDTO(){
        return restService.getDisplayDTO();
    }
}
