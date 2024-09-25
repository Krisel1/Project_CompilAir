package com.proyect.CompilAir.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;



import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void test_Get_All_Flights() throws Exception {
        when(flightService.getAllFlight()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(flightService, times(1)).getAllFlight();
    }

    @Test
    void test_Get_Flight_By_Id() throws Exception {
        Flight flight = new Flight(1, "FL123", true,
                LocalDateTime.of(2024, 9, 25, 10, 0),
                LocalDateTime.of(2024, 9, 25, 12, 0),
                150);

        when(flightService.getFlightById(1L)).thenReturn(flight);

        mockMvc.perform(get("/api/flights/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.flightName").value("FL123"))
                .andExpect(jsonPath("$.flightStatus").value(true))
                .andExpect(jsonPath("$.totalSeats").value(150));

        verify(flightService, times(1)).getFlightById(1L);
    }

    @Test
    void test_Create_Flight() throws Exception {
        Flight flight = new Flight(1, "FL123", true,
                LocalDateTime.of(2024, 9, 25, 10, 0),
                LocalDateTime.of(2024, 9, 25, 12, 0),
                150);


        when(flightService.createFlight(any(Flight.class))).thenReturn(flight);


        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightName\":\"FL123\",\"flightStatus\":true,\"departureDate\":\"2024-09-25T10:00:00\",\"returnDate\":\"2024-09-25T12:00:00\",\"totalSeats\":150}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.flightName").value("FL123"));

        verify(flightService, times(1)).createFlight(any(Flight.class));
    }

    @Test
    public void test_Update_Flight() throws Exception {
        Long id = 1L;
        Flight flight = new Flight(1, "FL456", false,
                LocalDateTime.of(2024, 9, 25, 14, 0),
                LocalDateTime.of(2024, 9, 25, 16, 0),
                200);


        when(flightService.updateFlight(eq(id), any(Flight.class))).thenReturn(flight);

        mockMvc.perform(put("/api/flights/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightName\":\"FL456\",\"flightStatus\":false,\"departureDate\":\"2024-09-25T14:00:00\",\"returnDate\":\"2024-09-25T16:00:00\",\"totalSeats\":200}"))
                .andExpect(status().isOk());


        verify(flightService, times(1)).updateFlight(eq(id), any(Flight.class));
    }

    @Test
    void delete_Flight_By_Id() throws Exception {
        long flightId = 1L;

        when(flightService.deleteFlight(flightId)).thenReturn(true);

        mockMvc.perform(delete("/api/flights/{id}", flightId))
                .andExpect(status().isOk());

        verify(flightService, times(1)).deleteFlight(flightId);
    }

    @Test
    void delete_Flight_By_Id_Not_Found() throws Exception {
        long flightId = 1L;

        when(flightService.deleteFlight(flightId)).thenReturn(false);

        mockMvc.perform(delete("/api/flights/{id}", flightId))
                .andExpect(status().isOk());

        verify(flightService, times(1)).deleteFlight(flightId);
    }
}