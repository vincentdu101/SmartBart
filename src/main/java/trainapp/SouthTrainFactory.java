/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import services.SeatService;
import services.StationService;
import services.TrainStationProgressService;
import services.TrainService;

/**
 *
 * @author vdu
 */
public class SouthTrainFactory extends TrainFactory {

    public SouthTrainFactory(TrainStationProgressService trainStationProgressService,
                             TrainService trainService,
                             StationService stationService,
                             SeatService seatService) {
        super(trainStationProgressService, trainService, stationService, seatService);
        trainIterator = new TrainIterator();
    }

    private Train train; 
    private Direction direction = Direction.SOUTH;
    
    @Override 
    public Train createTrain(TrainModel model, Station startingStation) {
        if (model.equals(TrainModel.A)) {
            train = new SouthATrain(startingStation);
        } else if (model.equals(TrainModel.B)) {
            train = new SouthBTrain(startingStation);
        } else if (model.equals(TrainModel.C)) {
            train = new SouthCTrain(startingStation);
        }

        train.setId(trainService.create(train));
        return train;
    }
    
}
