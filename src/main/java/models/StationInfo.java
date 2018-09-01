/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.json.JSONArray;
import org.json.JSONObject;
import services.StationService;
import services.TrainStationProgressService;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincentdu
 */
public class StationInfo implements Serializable {

    private String name;
    private String abbr;
    private double gtfs_latitude;
    private double gtfs_longitude;
    private String address;
    private String city;
    private String county;
    private String state;
    private String zipcode;

    public StationInfo(){}

    public StationInfo(String name,
                       String abbr,
                       double gtfs_latitude,
                       double gtfs_longitude,
                       String address,
                       String city,
                       String county,
                       String state,
                       String zipcode) {
        this.name = name;
        this.abbr = abbr;
        this.gtfs_latitude = gtfs_latitude;
        this.gtfs_longitude = gtfs_longitude;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Station(JSONObject station) {
        this.name = station.getString("name");
        this.abbr = station.getString("abbr");

        JSONArray etds = station.getJSONArray("etd");
        for (int i = 0; i < etds.length(); i++) {
            JSONObject etdRow = etds.getJSONObject(i);
            this.etds.add(new Etd(etdRow));
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

    public double getGtfs_latitude() {
        return gtfs_latitude;
    }

    public void setGtfs_latitude(double gtfs_latitude) {
        this.gtfs_latitude = gtfs_latitude;
    }

    public double getGtfs_longitude() {
        return gtfs_longitude;
    }

    public void setGtfs_longitude(double gtfs_longitude) {
        this.gtfs_longitude = gtfs_longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
