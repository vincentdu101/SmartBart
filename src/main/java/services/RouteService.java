package services;

import models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

@Service
public class RouteService {

    @Autowired
    private RequestService requestService;

    private String path = "https://api.bart.gov/api/route.aspx";

    public List<Route> getActiveRoutes() {
        try {
            String fullPath = path + "?" + requestService.getParamsString("routes");
            URL url = new URL(fullPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed http error code " +
                    connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream()
            ));

            String output;
            String content = "";
            while ((output = br.readLine()) != null) {
                content += output;
            }
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = (JSONObject) jsonObject.get("root");
            System.out.println(root.get("routes"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
