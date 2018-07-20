package services;

import models.Direction;
import models.TrainStationProgress;

import java.util.List;
import java.util.Map;

/**
 * Created by vincentdu on 4/30/17.
 */
public class TrainStationProgressService extends ResourceService {

    public static void delete(Integer stationId) {
        String SQL = "update train_station_progress set active = false where station_id = " + stationId;
        runJdbcTemplate().update(SQL);
    }

    public static void create(Integer trainId, Integer stationId, Direction direction) {
        String SQL = "INSERT INTO train_station_progress (station_id, train_id, active, direction) " +
                "VALUES (" + stationId + ", " + trainId + ", true, '" + direction.getDirection() + "')";
        runJdbcTemplate().update(SQL);
    }

    public static TrainStationProgress findTrainStationByTrainId(Integer trainId) {
        String SQL = "SELECT * FROM train_station_progress WHERE train_id = '" + trainId + "' and active=true LIMIT 1";
        List<Map<String, Object>> stationObject = runJdbcTemplate().queryForList(SQL);
        for (Map<String, Object> objectMap : stationObject) {
            return createTrainStationProgressFromMapObject(objectMap);
        }
        return null;
    }

}
