package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import services.SeatService;
import services.StationService;
import services.TrainService;
import services.TrainStationProgressService;

/**
 * Created by vincentdu on 5/7/17.
 */
public class MainController {

    protected StationService stationService;
    protected TrainService trainService;
    protected SeatService seatService;
    protected TrainStationProgressService trainStationProgressService;

}
