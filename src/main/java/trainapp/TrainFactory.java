/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import hello.StationController;
import hello.TrainController;
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

	public void beginTravelForAllTrains() {
		try {
			Iterator tempIterator = trainIterator;
			Iterator updatedIterator = new TrainIterator();
			while (tempIterator.hasNext()) {
				updatedIterator.add(startTrain(tempIterator.remove()));
			}
			trainIterator = updatedIterator;
			Thread.sleep(10000);
			arrivedAtStationProcess();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }


	public void arrivedAtStationProcess() {
		try {
			Iterator tempIterator = trainIterator;
			Iterator updatedIterator = new TrainIterator();
			while (tempIterator.hasNext()) {
				updatedIterator.add(stopTrain(tempIterator.remove()));
			}
			trainIterator = updatedIterator;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public Train startTrain(Train train) {
		try {
			train.setTrainState(TrainState.STARTED);
			Station currentStation = stationService.findStationById(train.getStartingStationId());
			trainStationProgressService.create(
					train.getId(),
					currentStation.getNextStation(train.getDirection()).getId(),
					determineDirection(train.getDirection(), currentStation)
			);
			stationController.updateStationMessage();
			trainController.updateTrains();
			return train;
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return train;
	}

	public Train stopTrain(Train train) {
		try {
			train.setTrainState(TrainState.STOPPED);
			trainStationProgressService.delete(train.getId());
			stationController.updateStationMessage();
			trainController.updateTrains();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
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
