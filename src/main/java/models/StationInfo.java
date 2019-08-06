/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

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

    public StationInfo(JSONObject stationInfo) {
        this.name = stationInfo.getString("name");
        this.abbr = stationInfo.getString("abbr");
        this.gtfs_latitude = stationInfo.getDouble("gtfs_latitude");
        this.gtfs_longitude = stationInfo.getDouble("gtfs_longitude");
        this.address = stationInfo.getString("address");
        this.city = stationInfo.getString("city");
        this.county = stationInfo.getString("county");
        this.state = stationInfo.getString("state");
        this.zipcode = stationInfo.getString("zipcode");
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

    public double getGtfsLatitude() {
        return gtfs_latitude;
    }

    public void setGtfsLatitude(double gtfs_latitude) {
        this.gtfs_latitude = gtfs_latitude;
    }

    public double getGtfsLongitude() {
        return gtfs_longitude;
    }

    public void setGtfsLongitude(double gtfs_longitude) {
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
