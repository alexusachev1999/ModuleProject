package ru.usachev.display.logiweb.view;

import org.primefaces.model.chart.PieChartModel;
import ru.usachev.display.logiweb.bean.DisplayBean;
import ru.usachev.display.logiweb.dto.DisplayDTO;
import ru.usachev.display.logiweb.dto.DriverRestDTO;
import ru.usachev.display.logiweb.service.RestService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
public class PieChart implements Serializable {
    private PieChartModel pieModel;

    @Inject
    private RestService restService;

    DisplayDTO displayDTO;

    DriverRestDTO driverRestDTO;

    @PostConstruct
    public void init() {
        displayDTO = restService.getDisplayDTO();
        driverRestDTO = displayDTO.getDriverRestDTO();
        createPieModels();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
    private void createPieModels() {
        createPieModel();
    }
    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Свобоные", driverRestDTO.getNumberOfEnabledDriver());
        pieModel.set("Занятые", driverRestDTO.getNumberOfDisabledDriver());
        pieModel.setTitle("Водители");
        pieModel.setLegendPosition("c");
    }
}
