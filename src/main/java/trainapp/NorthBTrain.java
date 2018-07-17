/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vdu
 */
public class NorthBTrain extends Train {

    public NorthBTrain(Station startingStation) {
        this.name = "North B Train";
        this.description = "North B Train";
        this.startingStationId = startingStation.getId();
        this.trainState = TrainState.STOPPED;
        this.direction = Direction.NORTH;
    }
    
}
