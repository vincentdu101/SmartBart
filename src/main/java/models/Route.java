package models;

import org.json.JSONObject;

public class Route {

    private String name;
    private String abbr;
    private String routeID;
    private int number;
    private String origin;
    private String destination;
    private int holidays;
    private int numStns;

    public Route() {}

    public Route(JSONObject route) {
        this.name = (String) route.get("name");
        this.abbr = (String) route.get("abbr");
        this.routeID = (String) route.get("routeID");
        this.number = Integer.parseInt((String)route.get("number"));

        if (route.has("origin")) {
            this.origin = (String) route.get("origin");
        }

        if (route.has("destination")) {
            this.destination = (String) route.get("destination");
        }

        if (route.has("holidays")) {
            this.holidays = Integer.parseInt((String)route.get("holidays"));
        }

        if (route.has("num_stns")) {
            this.numStns = Integer.parseInt((String)route.get("num_stns"));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getHolidays() {
        return holidays;
    }

    public void setHolidays(int holidays) {
        this.holidays = holidays;
    }

    public int getNumStns() {
        return numStns;
    }

    public void setNumStns(int num_stns) {
        this.numStns = num_stns;
    }
}
