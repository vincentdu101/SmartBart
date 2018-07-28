package services;

import models.Route;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class RouteService {

    @Autowired
    private RequestService requestService;

    private String path = "https://api.bart.gov/api/route.aspx";

    private List<Route> convertRoutesToList(JSONArray route) {
        List<Route> convertedRoutes = new ArrayList<>();
        System.out.println(route);
        for (int i = 0; i < route.length(); i++) {
            JSONObject routeRow = (JSONObject) route.get(i);
            convertedRoutes.add(new Route(routeRow));
        }
        return convertedRoutes;
    }

    public List<Route> getActiveRoutes() {
        try {
            String fullPath = path + "?" + requestService.getParamsString("routes");
            String content = requestService.getRequestContent(fullPath);
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = (JSONObject) jsonObject.get("root");
            JSONObject routes = (JSONObject) root.get("routes");
            JSONArray route = routes.getJSONArray("route");
            return convertRoutesToList(route);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Optional<Route> getSpecificRoute(String id) {
        try {
            String fullPath = path + "?" + requestService.getParamsStringWithExtraParam(
                    "routeinfo", "route", id
            );
            String content = requestService.getRequestContent(fullPath);
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = (JSONObject) jsonObject.get("root");
            JSONObject routes = (JSONObject) root.get("routes");
            JSONObject route = (JSONObject) routes.get("route");
            return Optional.of(new Route(route));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }


}
