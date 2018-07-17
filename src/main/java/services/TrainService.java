package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;

import trainapp.Train;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Created by vincentdu on 5/7/17.
 */
public class TrainService extends ResourceService {

    private static Train createTrainFromMapObject(Map<String, Object> objectMap) {

        return new Train(
                Integer.parseInt(objectMap.get("id").toString()),
                objectMap.get("name").toString(),
                objectMap.get("description").toString(),
                Integer.parseInt(objectMap.get("start_station_id").toString()),
                objectMap.get("train_state").toString(),
                toLocalDateTime(objectMap.get("created_at").toString()),
                toLocalDateTime(objectMap.get("modified_at").toString())
        );
    }

    public static List<Train> findAllTrains() {
        List<Train> output = new ArrayList<>();
        String SQL = "SELECT * FROM train";
        List<Map<String, Object>> trainObject = runJdbcTemplate().queryForList(SQL);
        for (Map<String, Object> objectMap : trainObject) {
            output.add(createTrainFromMapObject(objectMap));
        }
        return output;
    }



}
