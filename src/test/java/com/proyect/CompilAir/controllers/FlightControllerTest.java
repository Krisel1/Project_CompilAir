package com.proyect.CompilAir.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Flight flight;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        flight = new Flight(1, "FL123", true, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), 150);
    }

    @Test
    public void CreateFlight() throws Exception {
        when(flightService.createFlight(any(Flight.class))).thenReturn(flight);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(flight)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightName").value("FL123"));
    }

    @Test
    public void UpdateFlight() throws Exception {
        when(flightService.updateFlight(eq(1L), any(Flight.class))).thenReturn(flight);

        mockMvc.perform(put("/api/flights/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(flight)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightName").value("FL123"));
    }

    @Test
    public void GetAllFlight() throws Exception {
        List<Flight> flightList = Arrays.asList(flight);
        when(flightService.getAllFlight()).thenReturn(flightList);

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].flightName").value("FL123"));
    }

    @Test
    public void GetFlightById() throws Exception {
        when(flightService.getFlightById(1L)).thenReturn(flight);

        mockMvc.perform(get("/api/flights/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightName").value("FL123"));
    }

    @Test
    public void DeleteFlightById_Success() throws Exception {
        when(flightService.deleteFlight(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/flights/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Project with id1was delete"));
    }

    @Test
    public void DeleteFlightById_NotFound() throws Exception {
        when(flightService.deleteFlight(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/flights/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Project with id1not found"));
    }

}