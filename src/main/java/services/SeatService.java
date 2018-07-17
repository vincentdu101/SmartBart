package services;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import trainapp.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by vincentdu on 5/7/17.
 */
public class SeatService extends ResourceService {

    private static Seat createSeatFromMapObject(Map<String, Object> objectMap) {

        return new Seat(
                Integer.parseInt(objectMap.get("id").toString()),
                Integer.parseInt(objectMap.get("train_id").toString()),
                objectMap.get("description").toString(),
                objectMap.get("taken").equals("true"),
                objectMap.get("seat_type").toString(),
                toLocalDateTime(objectMap.get("created_at").toString()),
                toLocalDateTime(objectMap.get("modified_at").toString())
        );
    }

    public static Seat create(Seat seat) {
        String SQL = "INSERT INTO seat (train_id, description, taken, seat_type) " +
                "VALUES ('" + seat.getTrainId() + "', '" + seat.getDescription() +
                "', " + seat.isTaken() + ", '" + seat.getSeatType() + "')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        runJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst = con.prepareStatement(SQL, new String[] {"id"});
                        return pst;
                    }
                }, keyHolder
        );
        seat.setId(keyHolder.getKey().intValue());
        return seat;
    }

    public static void setupSeats(Train train) {
        List<Seat> seats = new ArrayList<>();

        Random generator = new Random();

        for (int i = 0; i < 6; i++) {
            String specialValue = SeatType.SPECIAL_RESERVED_SEAT.getSeat();
            seats.add(create(new Seat(train.getId(), specialValue, generator.nextBoolean(), specialValue)));
        }

        for (int i = 0; i < 10; i++) {
            String regularValue = SeatType.REGULAR_SEAT.getSeat();
            seats.add(create(new Seat(train.getId(), regularValue, generator.nextBoolean(), regularValue)));
        }

        for (int i = 0; i < 4; i++) {
            String tableValue = SeatType.TABLE_SEAT.getSeat();
            seats.add(create(new Seat(train.getId(), tableValue, generator.nextBoolean(), tableValue)));
        }
    }

    public static List<Seat> findAllSeats() {
        List<Seat> output = new ArrayList<>();
        String SQL = "SELECT * FROM seat";
        List<Map<String, Object>> seatObject = runJdbcTemplate().queryForList(SQL);
        for (Map<String, Object> objectMap : seatObject) {
            output.add(createSeatFromMapObject(objectMap));
        }
        return output;
    }

}
