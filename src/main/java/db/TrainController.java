package db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by vincentdu on 4/30/17.
 */
public class TrainController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public TrainController() {

    }

    public void createTrain() {

    }

}
