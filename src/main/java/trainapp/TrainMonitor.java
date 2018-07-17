/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincentdu
 */
public class TrainMonitor {

    List<Train> trains;
    List<Station> stations;
    
    public TrainMonitor() {
        trains = new ArrayList<>();
    }

    public void update(Train train, TrainState newTrainState) {
//        if (newTrainState.equals(TrainState.STARTED)) {
//            train.getCurrentStation().trainLeft();
//        } else if (newTrainState.equals(TrainState.STOPPED)){
//            train.getCurrentStation()
//                    .getNextStation(train.getDirection())
//                    .trainArrived(train);
//        }
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void addStation(Station station) {
        stations.add(station);
    }

}
