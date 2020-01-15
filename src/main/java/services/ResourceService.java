package services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import models.TrainStationProgress;
import models.Station;
import models.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by vincentdu on 5/7/17.
 */
public class ResourceService {

    protected static LocalDateTime toLocalDateTime(String complete) {
        return LocalDateTime.parse(complete.replace(" ", "T"));
    }

    protected static Station createStationFromMapObject(Map<String, Object> objectMap) {
        String nextNorthId = objectMap.get("next_north_station_id") == null ? "-1" : objectMap.get("next_north_station_id").toString();
        String nextSouthId = objectMap.get("next_south_station_id") == null ? "-1" : objectMap.get("next_south_station_id").toString();

        return new Station(
                Integer.parseInt(objectMap.get("id").toString()),
                objectMap.get("description").toString(),
                Integer.parseInt(nextNorthId),
                Integer.parseInt(nextSouthId),
                toLocalDateTime(objectMap.get("created_at").toString()),
                toLocalDateTime(objectMap.get("modified_at").toString())
        );
    }

    protected static TrainStationProgress createTrainStationProgressFromMapObject(Map<String, Object> objectMap) {
        return new TrainStationProgress(
                Integer.parseInt(objectMap.get("id").toString()),
                Integer.parseInt(objectMap.get("train_id").toString()),
                Integer.parseInt(objectMap.get("station_id").toString()),
                objectMap.get("direction").toString(),
                objectMap.get("active").toString().equals("true"),
                toLocalDateTime(objectMap.get("created_at").toString()),
                toLocalDateTime(objectMap.get("modified_at").toString())
        );
    }

    protected static final class StationMapper implements RowMapper<Station> {
        public Station mapRow(ResultSet rs, int rowNum) throws SQLException         {
            Station station = new Station();
            station.setId(rs.getInt("id"));
            station.setDescription(rs.getString("description"));
            station.setNextNorthStationId(rs.getInt("next_north_station_id"));
            station.setNextSouthStationId(rs.getInt("next_south_station_id"));
            station.setCreatedAt(toLocalDateTime(rs.getString("created_at")));
            station.setModifiedAt(toLocalDateTime(rs.getString("created_at")));
            return station;
        }
    }

}
