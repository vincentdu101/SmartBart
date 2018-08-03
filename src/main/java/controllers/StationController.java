package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import models.Station;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.StationService;

import java.util.List;

@Controller
@RequestMapping("/stations")
public class StationController extends MainController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private StationService stationService;

    @MessageMapping("/all")
    @SendTo("/topic/allStations")
    public List<Station> allStations() throws Exception {
        return stationService.findAllStations();
    }

    @MessageMapping("/update")
    @SendTo("/topic/updateStations")
    public List<Station> updateStations() throws Exception {
        return stationService.findAllStations();
    }

    public void updateStationMessage() {
        try {
            this.template.convertAndSend("/topic/updateStations", new Greeting("Fire"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
