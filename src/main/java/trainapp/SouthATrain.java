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
public class SouthATrain extends Train {

    public SouthATrain(Station startingStation) {
        this.name = "South A Train";
        this.description = "South A Train";
        this.direction = Direction.NORTH;
        this.startingStationId = startingStation.getId();
        this.trainState = TrainState.STOPPED;
    }

}
