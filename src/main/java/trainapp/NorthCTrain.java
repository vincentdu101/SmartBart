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
public class NorthCTrain extends Train {

    public NorthCTrain(Station startingStation) {
        this.name = "North C Train";
        this.description = "North C Train";
        this.startingStationId = startingStation.getId();
        this.direction = Direction.NORTH;
        this.trainState = TrainState.STOPPED;
    }
    
}
