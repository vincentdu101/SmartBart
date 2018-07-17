/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import services.StationService;
import services.TrainStationProgressService;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author vincentdu
 */
public class Station implements Serializable {

    Integer id;
    String description = "Train Station";
    Integer nextNorthStationId;
    Integer nextSouthStationId;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    StationMonitor stationMonitor;
    StationService stationService;
    TrainMonitor trainMonitor;
    TrainStationProgressService trainStationProgressService;
    Boolean trainStationed = false;
    Train currentTrain;


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

    public Station getNextStation(Direction direction) {
        if (direction.equals(Direction.NORTH)) {
            return stationService.findStationById(nextNorthStationId);
        } else {
            return stationService.findStationById(nextSouthStationId);
        }
    }

    public void setupStation(String description) {
        Station currentStation = stationService.findStationByDescription(description);
        trainMonitor.addStation(this);
        loadStation(currentStation);
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
}
