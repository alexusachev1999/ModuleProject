package ru.usachev.display.logiweb.service;


import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.usachev.display.logiweb.dto.DisplayDTO;
import ru.usachev.display.logiweb.dto.DriverDTO;
import ru.usachev.display.logiweb.dto.OrderDTO;
import ru.usachev.display.logiweb.dto.TruckDTO;

import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Named
public class RestServiceImpl implements RestService{

    @Override
    public List<DriverDTO> getDriversForDisplay() {
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(IOUtils.toString(new URL("http://localhost:8099/logiweb/rest/drivers"), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        List<DriverDTO> driverDTOList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject driverJson = jsonArray.getJSONObject(i);
            DriverDTO driverDTO = gson.fromJson(driverJson.toString(), DriverDTO.class);
            driverDTOList.add(driverDTO);
        }
        return driverDTOList;
    }

    @Override
    public List<TruckDTO> getTrucksForDisplay() {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersForDisplay() {
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(IOUtils.toString(new URL("http://localhost:8099/logiweb/rest/orders"), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject driverJson = jsonArray.getJSONObject(i);
            OrderDTO orderDTO = gson.fromJson(driverJson.toString(), OrderDTO.class);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Override
    public DisplayDTO getDisplayDTO() {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(IOUtils.toString(new URL("http://localhost:8099/logiweb/rest/"), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        DisplayDTO displayDTO = gson.fromJson(jsonObject.toString(), DisplayDTO.class);
        return displayDTO;
    }
}
