package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.models.User;
import com.proyect.CompilAir.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.proyect.CompilAir.models.ERole.ADMIN;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    void Test_Get_All_Bookings() throws Exception {
        when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void Test_Get_Booking_By_Id() throws Exception {
        User user = new User(1L, ADMIN, "hola", "hola", "hola");

        Route route = new Route("SVQ-HUE", 1L);
        Booking booking = new Booking(1L, "Eva", "Porter", 650349024, "Female", "hello@gmail.com", null, "dni", "3454556", "street piruleta", 21003, "Spain", "seville", user, route);

        when(bookingService.getBookingById(1L)).thenReturn(booking);

        mockMvc.perform(get("/api/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(bookingService, times(1)).getBookingById(1L);
    }

    @Test
    void test_Create_Booking() throws Exception {
        Booking booking = new Booking(1L, "Fran", "Cano", 43754, "male", "hola@hola.es", null, "dni", "3454556", "hola", 3424, "spain", "huelva", null, null);
        booking.setId(1L);

        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Fran\",\"surname\":\"Cano\",\"email\":\"hola@hola.es\",\"city\":\"huelva\",\"country\":\"spain\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(bookingService, times(1)).createBooking(any(Booking.class));
    }

    @Test
    void test_Update_Booking() throws Exception {
        Long id = 1L;
        Booking booking = new Booking(id, "Krisel", "hola", 4968034, "Female", "krisel@gmail.com", null, "Dni", "87435438", "estepona", 41002, "spain", "seville", null, null);
        booking.setId(String.valueOf(1));

        when(bookingService.updateBooking(any(Long.class), any(Booking.class))).thenReturn(booking);

        mockMvc.perform(put("/api/bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Krisel\",\"surname\":\"hola\",\"email\":\"krisel@gmail.com\",\"city\":\"seville\",\"country\":\"spain\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(bookingService, times(1)).updateBooking(eq(id), any(Booking.class));
    }

    @Test
    void delete_Booking_By_Id() throws Exception {
        long bookingId = 1L;

        mockMvc.perform(delete("/api/bookings/{id}", bookingId))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).deleteBooking(bookingId);
    }
}
