package services;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import trainapp.Station;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;


/**
 * Created by vincentdu on 4/30/17.
 */
public class StationService extends ResourceService {

    public static Station findStationByDescription(String description) {
        String SQL = "SELECT * FROM station WHERE description = '" + description + "'";
        return runJdbcTemplate().queryForObject(SQL, new StationMapper());
    }

    public static Station findStationById(Integer id) {
        String SQL = "SELECT * FROM station WHERE id = '" + id + "' LIMIT 1";
        return runJdbcTemplate().queryForObject(SQL, new StationMapper());
    }

    public static List<Station> findAllStations() {
        List<Station> output = new ArrayList<>();
        String SQL = "SELECT * FROM station";
        List<Map<String, Object>> stationObject = runJdbcTemplate().queryForList(SQL);
        for (Map<String, Object> objectMap : stationObject) {
            output.add(createStationFromMapObject(objectMap));
        }
        return output;
    }

}
