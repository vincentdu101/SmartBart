package models;

import org.json.JSONObject;

public class Planner {

    private String origin;
    private String destination;
    private int schedNum;
    private Schedule schedule;

    public Planner() {}

    public Planner(JSONObject root) {
        this.origin = root.getString("origin");
        this.destination = root.getString("destination");
        this.schedNum = Integer.parseInt(root.getString("sched_num"));
        this.schedule = new Schedule(root.getJSONObject("schedule"));
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

    public int getSchedNum() {
        return schedNum;
    }

    public void setSchedNum(int schedNum) {
        this.schedNum = schedNum;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
