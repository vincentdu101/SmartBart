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
public class SouthCTrain extends Train {

    public SouthCTrain(Station startingStation) {
        this.name = "South C Train";
        this.description = "South C Train";
        this.direction = Direction.NORTH;
        this.startingStationId = startingStation.getId();
        this.trainState = TrainState.STOPPED;
    }
    
}
