package models;

public class Route {

    private String name;
    private String abbr;
    private String routeID;
    private int number;
    private Station origin;
    private Station destination;
    private int holidays;
    private int num_stns;

    public Route() {}

    public Route clone(Route route) {
        this.name = route.getName();
        this.abbr = route.getAbbr();
        this.routeID = route.getRouteID();
        this.number = route.getNumber();
        this.origin = route.getOrigin();
        this.destination = route.getDestination();
        this.holidays = route.getHolidays();
        this.num_stns = route.getNumStns();

        return this;
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

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public int getHolidays() {
        return holidays;
    }

    public void setHolidays(int holidays) {
        this.holidays = holidays;
    }

    public int getNumStns() {
        return num_stns;
    }

    public void setNum_stns(int num_stns) {
        this.num_stns = num_stns;
    }
}
