package services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import trainapp.TrainStationProgress;
import trainapp.Station;
import trainapp.Train;

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

    public static JdbcTemplate runJdbcTemplate() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            dataSource.setDriver(new com.mysql.jdbc.Driver());
            dataSource.setUrl("jdbc:mysql://localhost:3306/train_app");
            dataSource.setUsername("root");
            dataSource.setPassword("");

            return new JdbcTemplate(dataSource);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int create(Train train) {
        String SQL = "INSERT INTO train (name, description, start_station_id, train_state) " +
                "VALUES ('" + train.getName() + "', '" + train.getDescription() + "', " + train.getStartingStationId() + ", " +
                "'" + train.getTrainState().getState() + "')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        runJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst = con.prepareStatement(SQL, new String[] {"id"});
                        return pst;
                    }
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
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
