package com.proyect.CompilAir.services;

import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.repositories.IRouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RouteServiceTest {
    @Mock private IRouteRepository iRouteRepository;
    @InjectMocks private RouteService routeService;

    private Route route1;
    private Route route2;
    private List<Route> routeList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        route1 = new Route();
        route1.setId(1L);
        route1.setNameRoute("SVQ-PAR");

        route2 = new Route();
        route2.setId(2L);
        route2.setNameRoute("SVQ-AGP");

        routeList.add(route1);
        routeList.add(route2);
        }
        @Test
        void createRoute() {
            when(iRouteRepository.save(ArgumentMatchers.any(Route.class))).thenReturn(route1);

            Route result = routeService.createRoute(route1);
            assertEquals(1L, result.getId());
            assertEquals("SVQ-PAR", result.getNameRoute());
        }

        @Test
        void getAllRoutes() {
            when(iRouteRepository.findAll()).thenReturn(routeList);

            List<Route> result = routeService.getAllRoutes();

            assertEquals(2L, result.size());
            assertEquals(1, result.get(0).getId());
            assertEquals("SVQ-PAR", result.get(0).getNameRoute());
            assertEquals(2, result.get(1).getId());
            assertEquals("SVQ-AGP", result.get(1).getNameRoute());
        }

        @Test
        void getRoutebyId() {
            when(iRouteRepository.findById(anyLong())).thenReturn(Optional.of(route2));

            Optional<Route> result = routeService.getRoutebyId(1L);

            assertEquals(2L, result.get().getId());
            assertEquals("SVQ-AGP", result.get().getNameRoute());
        }
        @Test
        void updateRoute() {
        when(iRouteRepository.save(ArgumentMatchers.any(Route.class))).thenReturn(route1);
        Route update = route1;
        update.setNameRoute("SVQ-IBZ");

        routeService.updateRoute(update, 2);
        assertEquals(2, update.getId());

        verify(iRouteRepository, times(1)).save(update);
        }

        @Test
        void deleteRoute() {
            when(iRouteRepository.findById(1L)).thenReturn(Optional.of(route1));
            routeService.deleteRoute(1);
            verify(iRouteRepository, times (1)).deleteById(1L);
        }
    }



