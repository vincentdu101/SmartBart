package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import trainapp.Station;
import java.util.List;

@Controller
public class StationController extends MainController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/stations/all")
    @SendTo("/topic/allStations")
    public List<Station> allStations() throws Exception {
        return stationService.findAllStations();
    }

    @MessageMapping("/stations/update")
    @SendTo("/topic/updateStations")
    public List<Station> updateStations() throws Exception {
        return stationService.findAllStations();
    }

    public void updateStationMessage() {
        try {
            this.template.convertAndSend("/topic/updateStations", new Greeting("Fire"));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
