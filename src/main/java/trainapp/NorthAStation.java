/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import services.StationService;
import services.TrainStationProgressService;

/**
 *
 * @author vincentdu
 */
public class NorthAStation extends Station {

    public NorthAStation(StationService stationService,
                         StationMonitor stationMonitor,
                         TrainMonitor trainMonitor,
                         TrainStationProgressService trainStationProgressService) {
        this.trainMonitor = trainMonitor;
        this.stationService = stationService;
        this.stationMonitor = stationMonitor;
        this.trainStationProgressService = trainStationProgressService;
        setupStation("North A Station");
    }



}
