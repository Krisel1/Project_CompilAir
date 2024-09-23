package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void Test_Get_All_Bookings() throws Exception {

        when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());


        mockMvc.perform(get("/api/v1/booking/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void Test_Get_Booking_By_Id() throws Exception {

        Booking booking = new Booking(1L,"Eva","Porter",650349024,"Female","hello@gmail.com",null,"dni",3454556,"street piruleta",21003,"huelva","Spain",null,null);

        when(bookingService.getBookingById(1L)).thenReturn(booking);


        mockMvc.perform(get("/api/v1/booking/list/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(bookingService, times(1)).getBookingById(1L);
    }


}
