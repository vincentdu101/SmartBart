package services;

import models.Station;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by vincentdu on 4/30/17.
 */
@Service
public class StationService extends ResourceService {

    @Autowired
    private RequestService requestService;

    private String path = "https://api.bart.gov/api/etd.aspx";

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

    public static Station findStationByDescription(String description) {
        String SQL = "SELECT * FROM station WHERE description = '" + description + "'";
        return runJdbcTemplate().queryForObject(SQL, new StationMapper());
    }

    public static Station findStationById(Integer id) {
        String SQL = "SELECT * FROM station WHERE id = '" + id + "' LIMIT 1";
        return runJdbcTemplate().queryForObject(SQL, new StationMapper());
    }

    public static List<Station> findAllStations() {
        List<Station> output = new ArrayList<>();
        String SQL = "SELECT * FROM station";
        List<Map<String, Object>> stationObject = runJdbcTemplate().queryForList(SQL);
        for (Map<String, Object> objectMap : stationObject) {
            output.add(createStationFromMapObject(objectMap));
        }
        return output;
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
        return null;
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
        return null;
    }

}
