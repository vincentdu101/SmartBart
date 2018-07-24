package services;

import models.Route;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RequestService requestService;

    private String path = "https://api.bart.gov/api/route.aspx";

    private List<Route> convertRoutesToList(String content) {
        JSONObject jsonObject = new JSONObject(content);
        JSONObject root = (JSONObject) jsonObject.get("root");
        System.out.println(root.get("routes"));
        return null;
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
        return null;
    }


}
