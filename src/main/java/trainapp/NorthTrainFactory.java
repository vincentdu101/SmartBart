/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import services.SeatService;
import services.StationService;
import services.TrainService;
import services.TrainStationProgressService;

/**
 *
 * @author vdu
 */
public class NorthTrainFactory extends TrainFactory {

    public NorthTrainFactory(TrainStationProgressService trainStationProgressService,
                             TrainService trainService,
                             StationService stationService,
                             SeatService seatService) {
        super(trainStationProgressService, trainService, stationService, seatService);
        trainIterator = new TrainIterator();
    }
    
    @Override 
    public Train createTrain(TrainModel model, Station startingStation) {
        Train train = new Train();
        if (model.equals(TrainModel.A)) {
            train = new NorthATrain(startingStation);
        } else if (model.equals(TrainModel.B)) {
            train = new NorthBTrain(startingStation);
        } else if (model.equals(TrainModel.C)) {
            train = new NorthCTrain(startingStation);
        }

        train.setId(trainService.create(train));

        return train;
    }
    
}
