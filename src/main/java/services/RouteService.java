package services;

import models.Route;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class RouteService {

    @Autowired
    private RequestService requestService;

    private String path = "https://api.bart.gov/api/route.aspx";

    private List<Route> convertRoutesToList(String content) {
        JSONObject jsonObject = new JSONObject(content);
        JSONObject root = (JSONObject) jsonObject.get("root");
        JSONObject routes = (JSONObject) root.get("routes");
        JSONArray route = routes.getJSONArray("route");
        List<Route> convertedRoutes = new ArrayList<>();
        while(route.iterator().hasNext()) {
            JSONObject routeRow = (JSONObject) route.iterator().next();
            convertedRoutes.add(new Route(routeRow));
        }
        return convertedRoutes;
    }

    public List<Route> getActiveRoutes() {
        try {
            String fullPath = path + "?" + requestService.getParamsString("routes");
            String content = requestService.getRequestContent(fullPath);
            return convertRoutesToList(content);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Route> getAllRoutes() {
        try {
            String fullPath = path + "?" + requestService.getParamsString("routeinfo");
            String content = requestService.getRequestContent(fullPath);
            return convertRoutesToList(content);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }


}
