package models;

public class Planner {

    private String origin;
    private String destination;
    private int schedNum;
    private Schedule schedule;

    public Planner() {}

    public Planner clone(Planner planner) {
        this.origin = planner.getOrigin();
        this.destination = planner.getDestination();
        this.schedNum = planner.getSchedNum();
        this.schedule = planner.getSchedule();
        return this;
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
