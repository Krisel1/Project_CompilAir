package com.proyect.CompilAir.services;

import com.proyect.CompilAir.excepcions.ResourceNotFoundException;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.repositories.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private IRouteRepository iRouteRepository;

    public RouteService(IRouteRepository iRouteRepository){
        this.iRouteRepository = iRouteRepository;
    }

    public Route createRoute(Route newRoute){
        return iRouteRepository.save(newRoute);
    }
    public List<Route> getAllRoutes(){
        return (List<Route>) iRouteRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        Optional<Route> route = iRouteRepository.findById(id);
        if (route.isEmpty()) {
            throw new ResourceNotFoundException("Route not found with id " + id);
        }
        return route;
    }
    public void updateRoute(Route route, long id){
        route.setId(id);
        iRouteRepository.save(route);
    }
    public String deleteRoute(long id){
        iRouteRepository.deleteById(id);
        return "The route has been deleted";
    }
}
