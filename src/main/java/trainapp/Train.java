/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import java.time.LocalDateTime;

/**
 *
 * @author vincentdu
 */
public class Train {

    private Integer id;

    String description;
    String name;
    Integer startingStationId;
    Station currentStation;
    private TrainStationProgress trainStationProgress;
    TrainState trainState = TrainState.STOPPED;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    Direction direction;

    public Train() {

    }

    public Train(Integer id,
                   String name,
                   String description,
                   Integer startingStationId,
                   String trainStateValue,
                   LocalDateTime createdAt,
                   LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.trainState = TrainState.findTrainState(trainStateValue);
        this.startingStationId = startingStationId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    public void linkToStartingStation(Station startingStation) {
        this.startingStationId = startingStation.getId();
    }

    public Integer getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Integer getStartingStationId() { return startingStationId; }

    public Station getCurrentStation() {
        return currentStation;
    }

    public TrainState getTrainState() { return trainState; }

    public void setTrainState(TrainState trainState) {
        this.trainState = trainState;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public TrainStationProgress getTrainStationProgress() {
        return trainStationProgress;
    }

    public void setTrainStationProgress(TrainStationProgress trainStationProgress) {
        this.trainStationProgress = trainStationProgress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
