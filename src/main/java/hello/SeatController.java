package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import trainapp.Seat;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vincentdu on 5/7/17.
 */
@Controller
public class SeatController extends MainController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/seats/all")
    @SendTo("/topic/allSeats")
    public List<Seat> allSeats(Message message) throws Exception {
        return seatService.findAllSeats()
                .stream()
                .filter(e -> e.getTrainId() == Integer.parseInt(message.getTableId()))
                .collect(Collectors.toList());
    }

    @MessageMapping("/seats/update")
    @SendTo("/topic/updateSeats")
    public List<Seat> updateSeats(Message message) throws Exception {
        return seatService.findAllSeats()
                .stream()
                .filter(e -> e.getTrainId() == Integer.parseInt(message.getTableId()))
                .collect(Collectors.toList());
    }

    public void updateSeats() {
        try {
            this.template.convertAndSend("/topic/updateSeats");
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
