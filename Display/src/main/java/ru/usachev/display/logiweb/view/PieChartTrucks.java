package ru.usachev.display.logiweb.view;

import org.primefaces.model.chart.PieChartModel;
import ru.usachev.display.logiweb.dto.DisplayDTO;
import ru.usachev.display.logiweb.dto.DriverRestDTO;
import ru.usachev.display.logiweb.dto.TruckRestDTO;
import ru.usachev.display.logiweb.service.RestService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
public class PieChartTrucks implements Serializable {
    private PieChartModel pieModel;

    @Inject
    private RestService restService;

    DisplayDTO displayDTO;

    TruckRestDTO truckRestDTO;

    @PostConstruct
    public void init() {
        displayDTO = restService.getDisplayDTO();
        truckRestDTO = displayDTO.getTruckRestDTO();
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
        pieModel.set("Свобоные", truckRestDTO.getNumberOfFreeTruck());
        pieModel.set("Занятые", truckRestDTO.getNumberOfInOrderTruck());
        pieModel.set("Сломанные", truckRestDTO.getNumberOfBrokenTruck());
        pieModel.setTitle("Статистика фур");
        pieModel.setLegendPosition("c");
    }
}
