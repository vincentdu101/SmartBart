/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.StationController;
import controllers.TrainController;
import services.StationService;
import services.TrainStationProgressService;
import services.TrainService;
import services.SeatService;

/**
 *
 * @author vincentdu
 */
public abstract class TrainFactory {

	TrainService trainService;
	TrainStationProgressService trainStationProgressService;
	SeatService seatService;
	StationService stationService;
	Iterator trainIterator;
	StationController stationController;
	TrainController trainController;

	public TrainFactory(TrainStationProgressService trainStationProgressService,
						TrainService trainService,
						StationService stationService,
						SeatService seatService) {
		this.trainService = trainService;
		this.stationService = stationService;
		this.trainStationProgressService = trainStationProgressService;
	}

	public Train prepareTrain(TrainModel model, Station startingStation, Direction direction) {
		Train train = createTrain(model, startingStation);
        trainStationProgressService.create(train.getId(), startingStation.getId(), direction);
		seatService.setupSeats(train);
        train.linkToStartingStation(startingStation);
		trainIterator.add(train);
		return train;
	}

	public void setStationController(StationController stationController) {
		this.stationController = stationController;
	}

	public void setTrainController(TrainController trainController) {
		this.trainController = trainController;
	}

	public Direction determineDirection(Direction direction, Station currentStation) {
		if (direction.equals(Direction.NORTH)) {
			return (currentStation.getNextNorthStationId() == -1) ? Direction.SOUTH : Direction.NORTH;
		} else {
			return (currentStation.getNextSouthStationId() == -1) ? Direction.NORTH : Direction.SOUTH;
		}
	}

	abstract Train createTrain(TrainModel model, Station startingStation);
    
}
