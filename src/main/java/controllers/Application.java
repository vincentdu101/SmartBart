package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import services.*;
import models.*;

@SpringBootApplication
@ComponentScan ({"services", "models", "controllers"})
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    private StationController stationController;
    private TrainController trainController;
    private RoutesController routesController;
    private StationService stationService;
    private TrainMonitor trainMonitor;
    private TrainStationProgressService trainStationProgressService;
    private TrainService trainService;
    private SeatService seatService;
    private TrainFactory northFactory;
    private TrainFactory southFactory;
    private RouteService routeService;
    private RequestService requestService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ApplicationContext context;

    @Override
    public void run(String... strings) throws Exception {
        routeService = (RouteService) context.getBean("routeService");
        requestService = (RequestService) context.getBean("requestService");

        //stationController = (StationController) context.getBean("stationController");
        //trainController = (TrainController) context.getBean("trainController");
        routesController = (RoutesController) context.getBean("routesController");

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/train_app?verifyServerCertificate=false&useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        log.info("Creating tables");
        jdbcTemplate = new JdbcTemplate(dataSource);

        setupTables();
        setupStations();
        setupTrains();
        startTrains();
    }

    public void setupTables() {
        jdbcTemplate.execute("DROP table if exists customer");
        jdbcTemplate.execute("CREATE TABLE customer(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        jdbcTemplate.execute("DROP table if exists station");
        jdbcTemplate.execute("CREATE TABLE station(" +
                "id SERIAL, description VARCHAR(255), " +
                "next_north_station_id SMALLINT UNSIGNED, " +
                "next_south_station_id SMALLINT UNSIGNED, " +
                "created_at datetime DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at datetime DEFAULT CURRENT_TIMESTAMP)");

        jdbcTemplate.execute("DROP table if exists train_station_progress");
        jdbcTemplate.execute("CREATE TABLE train_station_progress(" +
                "id SERIAL, " +
                "station_id SMALLINT UNSIGNED, " +
                "train_id SMALLINT UNSIGNED NOT NULL REFERENCES train(id), " +
                "active bool, " +
                "direction VARCHAR(255), " +
                "created_at datetime DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at datetime DEFAULT CURRENT_TIMESTAMP)");

        jdbcTemplate.execute("DROP table if exists train");
        jdbcTemplate.execute("CREATE TABLE train(" +
                "id SERIAL, name VARCHAR(255), description VARCHAR(255), " +
                "start_station_id SMALLINT UNSIGNED NOT NULL REFERENCES station(id), " +
                "train_state VARCHAR(255), " +
                "created_at datetime DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at datetime DEFAULT CURRENT_TIMESTAMP)");

        jdbcTemplate.execute("DROP table if exists seat");
        jdbcTemplate.execute("CREATE TABLE seat(" +
                "id SERIAL, description VARCHAR(255), taken bool, " +
                "train_id SMALLINT UNSIGNED NOT NULL, " +
                "seat_type VARCHAR(255), " +
                "created_at datetime DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at datetime DEFAULT CURRENT_TIMESTAMP)");
    }

    public void setupStations() {
        Map namedParameters = new HashMap();
        namedParameters.put("created_at", new Date());
        namedParameters.put("modified_at", new Date());

        jdbcTemplate.execute("insert into station(description) values('North A Station')");
        jdbcTemplate.execute("insert into station(description) values('North B Station')");
        jdbcTemplate.execute("insert into station(description) values('North C Station')");
        jdbcTemplate.execute("insert into station(description) values('South A Station')");
        jdbcTemplate.execute("insert into station(description) values('South B Station')");
        jdbcTemplate.execute("insert into station(description) values('South C Station')");

        jdbcTemplate.execute("update station set next_north_station_id=null, next_south_station_id=2 where id = 1");
        jdbcTemplate.execute("update station set next_north_station_id=1, next_south_station_id=3 where id = 2");
        jdbcTemplate.execute("update station set next_north_station_id=2, next_south_station_id=4 where id = 3");
        jdbcTemplate.execute("update station set next_north_station_id=3, next_south_station_id=5 where id = 4");
        jdbcTemplate.execute("update station set next_north_station_id=4, next_south_station_id=6 where id = 5");
        jdbcTemplate.execute("update station set next_north_station_id=5, next_south_station_id=null where id = 6");
    }

    public void setupTrains() {
        northFactory = new NorthTrainFactory(trainStationProgressService, trainService, stationService, seatService);
        southFactory = new SouthTrainFactory(trainStationProgressService, trainService, stationService, seatService);

        northFactory.setStationController(stationController);
        southFactory.setStationController(stationController);

        northFactory.setTrainController(trainController);
        southFactory.setTrainController(trainController);

        northFactory.prepareTrain(TrainModel.A, StationService.findStationByDescription("North A Station"), Direction.SOUTH);
        northFactory.prepareTrain(TrainModel.B, StationService.findStationByDescription("North B Station"), Direction.NORTH);
        northFactory.prepareTrain(TrainModel.C, StationService.findStationByDescription("North C Station"), Direction.NORTH);

        southFactory.prepareTrain(TrainModel.A, StationService.findStationByDescription("South A Station"), Direction.NORTH);
        southFactory.prepareTrain(TrainModel.B, StationService.findStationByDescription("South B Station"), Direction.NORTH);
        southFactory.prepareTrain(TrainModel.C, StationService.findStationByDescription("South C Station"), Direction.SOUTH);
    }

    public void startTrains() throws InterruptedException {
        northFactory.beginTravelForAllTrains();
        southFactory.beginTravelForAllTrains();

        Thread.sleep(3000);

        northFactory.beginTravelForAllTrains();
        southFactory.beginTravelForAllTrains();
    }
}