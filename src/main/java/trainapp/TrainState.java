/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vincentdu
 */
public enum TrainState {
    
    STARTED("Started"),
    STOPPED("Stopped"),
    REPAIRING("REPAIRING");
    
    String state;
    
    TrainState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }

    public static TrainState findTrainState(String value) {
        if (value.equals(TrainState.STARTED.getState())) {
            return TrainState.STARTED;
        } else if (value.equals(TrainState.STOPPED.getState())) {
            return TrainState.STOPPED;
        } else {
            return TrainState.REPAIRING;
        }
    }
    
}
