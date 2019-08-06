/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincentdu
 */
public class Station implements Serializable {

    private Integer id;
    private String description = "Train Station";
    private Integer nextNorthStationId;
    private Integer nextSouthStationId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Etd> etds;
    private String name;
    private String abbr;

    public Station(){}

    public Station(Integer id,
                   String description,
                   Integer nextNorthStationId,
                   Integer nextSouthStationId,
                   LocalDateTime createdAt,
                   LocalDateTime modifiedAt) {
        this.id = id;
        this.description = description;
        this.nextNorthStationId = nextNorthStationId;
        this.nextSouthStationId = nextSouthStationId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Station(JSONObject station) {
        this.name = station.getString("name");
        this.abbr = station.getString("abbr");
        this.etds = new ArrayList<>();

        JSONArray etds = station.getJSONArray("etd");
        for (int i = 0; i < etds.length(); i++) {
            JSONObject etdRow = etds.getJSONObject(i);
            this.etds.add(new Etd(etdRow));
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNextNorthStationId(Integer nextNorthStationId) {
        this.nextNorthStationId = nextNorthStationId;
    }

    public void setNextSouthStationId(Integer nextSouthStationId) {
        this.nextSouthStationId = nextSouthStationId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Integer getNextNorthStationId() {
        return nextNorthStationId;
    }
    
    public Integer getNextSouthStationId() {
        return nextSouthStationId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void loadStation(Station station) {
        id = station.getId();
        description = station.getDescription();
        nextNorthStationId = station.getNextNorthStationId();
        nextSouthStationId = station.getNextSouthStationId();
        createdAt = station.getCreatedAt();
        modifiedAt = station.getModifiedAt();
    }

    public Integer getId() {
        return id;
    }

    public void
    setId(Integer id){
        this.id = id;
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

    public List<Etd> getEtds() {
        return etds;
    }

    public void setEtds(List<Etd> etds) {
        this.etds = etds;
    }

}
