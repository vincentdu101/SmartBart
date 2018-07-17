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
public class NorthATrain extends Train {

    public NorthATrain(Station startingStation) {
        this.startingStationId = startingStation.getId();
        this.name = "North A Train";
        this.description = "North A Train";
        this.trainState = TrainState.STOPPED;
        this.direction = Direction.SOUTH;
    }

}
