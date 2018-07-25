package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Trip {

    private String origin;
    private String destination;
    private double fare;
    private LocalDateTime origTimeMin;
    private LocalDate origTimeDate;
    private LocalDateTime destTimeMin;
    private LocalDate destTimeDate;
    private double clipper;
    private double tripTime;
    private double co2;

    public Trip() {}

    public Trip clone(Trip trip) {
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.fare = trip.getFare();
        this.origTimeMin = trip.getOrigTimeMin();
        this.origTimeDate = trip.getOrigTimeDate();
        this.destTimeMin = trip.getDestTimeMin();
        this.destTimeDate = trip.getDestTimeDate();
        this.clipper = trip.getClipper();
        this.tripTime = trip.getTripTime();
        this.co2 = trip.getCo2();

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

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public LocalDateTime getOrigTimeMin() {
        return origTimeMin;
    }

    public void setOrigTimeMin(LocalDateTime origTimeMin) {
        this.origTimeMin = origTimeMin;
    }

    public LocalDate getOrigTimeDate() {
        return origTimeDate;
    }

    public void setOrigTimeDate(LocalDate origTimeDate) {
        this.origTimeDate = origTimeDate;
    }

    public LocalDateTime getDestTimeMin() {
        return destTimeMin;
    }

    public void setDestTimeMin(LocalDateTime destTimeMin) {
        this.destTimeMin = destTimeMin;
    }

    public LocalDate getDestTimeDate() {
        return destTimeDate;
    }

    public void setDestTimeDate(LocalDate destTimeDate) {
        this.destTimeDate = destTimeDate;
    }

    public double getClipper() {
        return clipper;
    }

    public void setClipper(double clipper) {
        this.clipper = clipper;
    }

    public double getTripTime() {
        return tripTime;
    }

    public void setTripTime(double tripTime) {
        this.tripTime = tripTime;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }
}
