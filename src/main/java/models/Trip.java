package models;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Trip {

    private String origin;
    private String destination;
    private double fare;
    private Date origDateTime;
    private Date destDateTime;
    private double clipper;
    private int tripTime;
    private double co2;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/dd/yyyy hh:mm a");
    private SimpleDateFormat format = new SimpleDateFormat("M/dd/yyyy hh:mm aaa", Locale.US);

    public Trip() {}

    public Trip(JSONObject trip) {
        try {
            this.origin = trip.getString("@origin");
            this.destination = trip.getString("@destination");
            this.fare = trip.getDouble("@fare");
            this.clipper = trip.getDouble("@clipper");
            this.tripTime = trip.getInt("@tripTime");
            this.co2 = trip.getDouble("@co2");
            this.origDateTime = format.parse(trip.getString("@origTimeDate") + " " + trip.getString("@origTimeMin"));
            this.destDateTime = format.parse(trip.getString("@destTimeDate") + " " + trip.getString("@destTimeMin"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
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

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getClipper() {
        return clipper;
    }

    public void setClipper(double clipper) {
        this.clipper = clipper;
    }

    public int getTripTime() {
        return tripTime;
    }

    public void setTripTime(int tripTime) {
        this.tripTime = tripTime;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public String getOrigDateTime() {
        return origDateTime.toString();
    }

    public void setOrigDateTime(Date origDateTime) {
        this.origDateTime = origDateTime;
    }

    public String getDestDateTime() {
        return destDateTime.toString();
    }

    public void setDestDateTime(Date destDateTime) {
        this.destDateTime = destDateTime;
    }
}
