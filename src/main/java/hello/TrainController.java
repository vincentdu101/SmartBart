package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import trainapp.Train;
import java.util.List;


@Controller
public class TrainController extends MainController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/trains/all")
    @SendTo("/topic/allTrains")
    public List<Train> allTrainsAndSeats() throws Exception {
        return getTrainsProgress();
    }

    @MessageMapping("/trains/update")
    @SendTo("/topic/updateTrains")
    public List<Train> updateStations() throws Exception {
        return getTrainsProgress();
    }

    public void updateTrains() {
        try {
            this.template.convertAndSend("/topic/updateTrains", new Greeting("Fire"));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Train> getTrainsProgress() {
        List<Train> trains = trainService.findAllTrains();
        for(Train train : trains) {
            train.setTrainStationProgress(trainStationProgressService.findTrainStationByTrainId(train.getId()));
            train.setCurrentStation(stationService.findStationById(train.getStartingStationId()));
        }
        return trains;
    }

}
