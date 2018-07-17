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
public class SouthBTrain extends Train {

    public SouthBTrain(Station startingStation) {
        this.name = "South B Train";
        this.description = "South B Train";
        this.direction = Direction.NORTH;
        this.startingStationId = startingStation.getId();
        this.trainState = TrainState.STOPPED;
    }
    
}
