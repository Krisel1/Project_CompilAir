package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.services.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RouteControllerTest {
    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;
    private MockMvc mockController;

    private Route route1;
    private Route route2;
    private ArrayList<Route> routeList = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockController = MockMvcBuilders.standaloneSetup(routeController).build();

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
    void createRoute() throws Exception {
        when(routeService.createRoute(any(Route.class))).thenReturn(route1);
        String routesJson =
                "{\"id\": 1,\n"
                        + "\"nameRoute\": \"SVQ-PAR\"}";
        mockController
                .perform(post("/api/routes").contentType(MediaType.APPLICATION_JSON).content(routesJson))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .json(
                                        "{\"id\": 1,\n"
                                                + "\"nameRoute\": \"SVQ-PAR\"}"));
    }
    @Test
    void getAllRoutes() throws Exception {
        when(routeService.getAllRoutes()).thenReturn(routeList);

        mockController
                .perform(MockMvcRequestBuilders.get("/api/routes"))
                .andExpect(status().isOk())
                .andExpect(
                 content()
                         .json(
                                 "[{\"id\": 1,\n"
                                         + "\"nameRoute\": \"SVQ-PAR\"\n"
                                         + "    },\n"
                                         + "    {\n"
                                         + " \"id\": 2,\n"
                                         + "\"nameRoute\": \"SVQ-AGP\"\n"
                                         + "    }\n"
                                         + "]"));
    }

    @Test
    void getRoutesById() throws Exception {
        when(routeService.getRoutebyId(anyLong())).thenReturn(Optional.ofNullable(route1));
        mockController
                .perform(MockMvcRequestBuilders.get("/api/routes/1"))
                .andExpect(status().isOk())
                .andExpect(
                 content()
                         .json(
                                 "{\"id\": 1,\n"
                                         + "\"nameRoute\": \"SVQ-PAR\"}"));
    }
    @Test
    void updateRoutes() throws Exception {
        Route updateRoutes = new Route();
        updateRoutes.setId(2L);
        updateRoutes.setNameRoute("SVQ-IBZ");

        String updateRoutesJson =
                "{\"id\": 2,\n"
                        + "\"nameRoute\": \"SVQ-IBZ\"}";
        mockController
                .perform(
                MockMvcRequestBuilders.put("/api/routes/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRoutesJson))
                .andExpect(status().isOk());

        verify(routeService).updateRoute(any(Route.class), any(Long.class));
    }
    @Test
    void deleteRouteById() throws Exception {
        mockController.perform(MockMvcRequestBuilders.delete("/api/routes/2"))
                .andExpect(status().isOk());
    }
}