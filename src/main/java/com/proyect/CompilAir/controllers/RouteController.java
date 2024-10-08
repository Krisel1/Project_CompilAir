package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.services.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {
    private final RouteService routeService;
    public RouteController(RouteService routeService){
        this.routeService = routeService;
    }
    @GetMapping
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") Long id){
        Optional<Route> route = routeService.getRouteById(id);
        return route.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            }

    @PostMapping
    public Route createRoute(@RequestBody Route newRoute){
        return routeService.createRoute(newRoute);
    }

    @PutMapping(path = "/{id}")
    public void updateRoute(@RequestBody Route route, @PathVariable Long id){
         routeService.updateRoute(route, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteRoute(@PathVariable Long id){
        routeService.deleteRoute(id);
        return "The route has been deleted";
    }
}
