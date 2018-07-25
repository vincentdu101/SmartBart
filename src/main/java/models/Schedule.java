package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Schedule {

    private LocalDate date;
    private LocalDateTime time;
    private int before;
    private int after;
    private List<Train> request;

    public Schedule() {}

    public Schedule clone(Schedule schedule) {
        this.date = schedule.getDate();
        this.time = schedule.getTime();
        this.before = schedule.getBefore();
        this.after = schedule.getAfter();
        this.request = schedule.getRequest();
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public int getAfter() {
        return after;
    }

    public void setAfter(int after) {
        this.after = after;
    }

    public List<Train> getRequest() {
        return request;
    }

    public void setRequest(List<Train> request) {
        this.request = request;
    }
}
