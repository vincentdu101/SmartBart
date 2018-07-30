package models;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trip {

    private String origin;
    private String destination;
    private double fare;
    private LocalDateTime origDateTime;
    private LocalDateTime destDateTime;
    private double clipper;
    private int tripTime;
    private double co2;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/dd/yyyy hh:mm a");

    public Trip() {}

    public Trip(JSONObject trip) {
        this.origin = trip.getString("@origin");
        this.destination = trip.getString("@destination");
        this.fare = trip.getDouble("@fare");
        this.clipper = trip.getDouble("@clipper");
        this.tripTime = trip.getInt("@tripTime");
        this.co2 = trip.getDouble("@co2");
        this.origDateTime = LocalDateTime.parse(trip.getString("@origTimeDate") + trip.getString("@origTimeMin"));
        this.destDateTime = LocalDateTime.parse(trip.getString("@destTimeDate") + trip.getString("@destTimeMin"));

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
        return origDateTime.toLocalTime().toString();
    }

    public void setOrigDateTime(LocalDateTime origDateTime) {
        this.origDateTime = origDateTime;
    }

    public String getDestDateTime() {
        return destDateTime.toLocalTime().toString();
    }

    public void setDestDateTime(LocalDateTime destDateTime) {
        this.destDateTime = destDateTime;
    }
}
