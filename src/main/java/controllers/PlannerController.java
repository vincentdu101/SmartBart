package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import models.Planner;
import org.springframework.web.bind.annotation.RequestParam;
import services.PlannerService;
import java.util.List;

@Controller
@RequestMapping("/planner")
public class PlannerController {

    @Autowired
    private PlannerService plannerService;

    @RequestMapping(value="/arrive/all")
    public ResponseEntity<Planner> getAllArrivalPlans(
        @RequestParam String orig, @RequestParam String dest
    ) {
        Planner planner = plannerService.getAllArrivalPlans(orig, dest);
        return new ResponseEntity<>(planner, HttpStatus.OK);
    }

}
