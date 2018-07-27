package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import models.Route;
import services.RouteService;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController extends MainController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value="/active")
    public ResponseEntity<List<Route>> getActiveRoutes() {
        List<Route> routes = routeService.getActiveRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @RequestMapping(value="/all")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }


}
