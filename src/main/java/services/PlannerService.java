package services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import models.Planner;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

@Service
public class PlannerService {

    @Autowired
    private RequestService requestService;

    private String path = "http://api.bart.gov/api/sched.aspx";

    private Map<String, String> convertPlannerParams(String orig, String dest) {
        Map<String, String> input = new HashMap<>();
        input.put("orig", orig);
        input.put("dest", dest);
        return input;
    }

    public Planner getAllArrivalPlans(String orig, String dest) {
        try {
            Map<String, String> input = convertPlannerParams(orig, dest);
            String fullPath = path + "?" + requestService.getParamsStringWithMap("arrive", input);
            String content = requestService.getRequestContent(fullPath);
            System.out.println(content);
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = jsonObject.getJSONObject("root");
            return new Planner(root);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Planner getAllDepartingPlans(String orig, String dest) {
        try {
            Map<String, String> input = convertPlannerParams(orig, dest);
            String fullPath = path + "?" + requestService.getParamsStringWithMap("depart", input);
            String content = requestService.getRequestContent(fullPath);
            System.out.println(content);
            JSONObject jsonObject = new JSONObject(content);
            JSONObject root = jsonObject.getJSONObject("root");
            return new Planner(root);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
