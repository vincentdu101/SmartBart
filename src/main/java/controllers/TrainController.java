package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import models.Train;
import java.util.List;


@Controller
public class TrainController extends MainController {

    @Autowired
    private SimpMessagingTemplate template;

    public void updateTrains() {
        try {
            this.template.convertAndSend("/topic/updateTrains", new Greeting("Fire"));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
