package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import models.Route;
import services.RouteService;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController extends MainController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/active")
    public List<Route> getActiveRoutes() {
        return routeService.getActiveRoutes();
    }


}
