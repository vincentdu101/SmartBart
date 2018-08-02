package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Schedule {

    private Date datetime;
    private int before;
    private int after;
    private List<Trip> request;
    private SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa", Locale.US);

    public Schedule() {}

    public Schedule(JSONObject schedule) {
        try {
            this.datetime = format.parse(schedule.getString("date") + " " + schedule.getString("time"));
            this.before = Integer.parseInt(schedule.getString("before"));
            this.after = Integer.parseInt(schedule.getString("after"));
            this.request = convertToTrips(schedule.getJSONObject("request").getJSONArray("trip"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Trip> convertToTrips(JSONArray trips) {
        List<Trip> convertedTrips = new ArrayList<>();
        for (int i = 0; i < trips.length(); i++) {
            JSONObject tripRow = trips.getJSONObject(i);
            convertedTrips.add(new Trip(tripRow));
        }
        return convertedTrips;
    }

    public String getDateTime() {
        return datetime.toString();
    }

    public void setDateTime(Date datetime) {
        this.datetime = datetime;
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

    public List<Trip> getRequest() {
        return request;
    }

    public void setRequest(List<Trip> request) {
        this.request = request;
    }
}
