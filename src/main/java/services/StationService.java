package services;

import models.Station;
import models.StationInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by vincentdu on 4/30/17.
 */
@Service
public class StationService extends ResourceService {

    @Autowired
    private RequestService requestService;

    private String path = "https://api.bart.gov/api/etd.aspx";
    private String stationsPath = "https://api.bart.gov/api/stn.aspx";

    private Map<String, String> convertStationParams(String orig, String dir) {
        Map<String, String> input = new HashMap<>();
        input.put("orig", orig);
        input.put("dir", dir);
        return input;
    }

    private List<Station> convertRoutesToList(JSONArray route) {
        List<Station> convertedRoutes = new ArrayList<>();
        for (int i = 0; i < route.length(); i++) {
            JSONObject stationRow = (JSONObject) route.get(i);
            convertedRoutes.add(new Station(stationRow));
        }
        return convertedRoutes;
    }

    private List<StationInfo> convertStationsToList(JSONArray route) {
        List<StationInfo> convertedStations = new ArrayList<>();
        for (int i = 0; i < route.length(); i++) {
            JSONObject stationRow = (JSONObject) route.get(i);
            convertedStations.add(new StationInfo(stationRow));
        }
        return convertedStations;
    }

    public List<Station> getStationsEstimate(String orig) {
        try {
            String fullPath = path + "?" + requestService.getParamsStringWithExtraParam("etd", "orig", orig);
            String content = requestService.getRequestContent(fullPath);
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = (JSONObject) jsonObject.get("root");
            JSONArray stations = root.getJSONArray("station");
            return convertRoutesToList(stations);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<StationInfo> getAllStationsInfo() {
        try {
            String fullPath = stationsPath + "?" + requestService.getParamsString("stns");
            String content = requestService.getRequestContent(fullPath);
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = (JSONObject) jsonObject.get("root");
            JSONArray stations = root.getJSONObject("stations").getJSONArray("station");
            return convertStationsToList(stations);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Station> getStationsFilteredEstimate(String orig, String direction) {
        try {
            Map<String, String> params = convertStationParams(orig, direction);
            String fullPath = path + "?" + requestService.getParamsStringWithMap("etd", params);
            String content = requestService.getRequestContent(fullPath);
            JSONObject jsonObject = new JSONObject(content);
            System.out.println(content);
            JSONObject root = (JSONObject) jsonObject.get("root");
            JSONArray stations = root.getJSONArray("station");
            return convertRoutesToList(stations);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

}
