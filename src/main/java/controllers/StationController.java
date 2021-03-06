package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import models.Station;
import models.StationInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.StationService;

import java.util.List;

@Controller
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value="/all")
    public ResponseEntity<List<StationInfo>> getAllStations() {
        List<StationInfo> stations = stationService.getAllStationsInfo();
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @RequestMapping(value="/estimates")
    public ResponseEntity<List<Station>> getStationsEstimate(@RequestParam String orig) {
        List<Station> stations = stationService.getStationsEstimate(orig);
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

    @RequestMapping(value="/estimates/filtered")
    public ResponseEntity<List<Station>> getStationsEstimate(
            @RequestParam String orig, @RequestParam String direction) {
        List<Station> stations = stationService.getStationsFilteredEstimate(orig, direction);
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }

}
