package trainapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincentdu on 4/30/17.
 */
public class StationMonitor {

    StationState stationState;
    List<Station> stations;

    public StationMonitor() {
        stationState = StationState.CLOSED;
        stations = new ArrayList<>();
    }

    public void update(StationState newStationState) {
        stationState = newStationState;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

}
