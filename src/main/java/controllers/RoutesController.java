package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import models.Route;
import org.springframework.web.bind.annotation.RequestParam;
import services.RouteService;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value="/active")
    public ResponseEntity<List<Route>> getActiveRoutes() {
        List<Route> routes = routeService.getActiveRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}")
    public ResponseEntity<Route> getSpecificRoute(@PathVariable(value="id") String id) {
        Route route = routeService.getSpecificRoute(id).get();
        return new ResponseEntity<>(route, HttpStatus.OK);
    }


}
