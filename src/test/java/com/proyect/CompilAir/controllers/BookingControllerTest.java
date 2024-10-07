package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.models.User;
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

import static com.proyect.CompilAir.models.ERole.ADMIN;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


        mockMvc.perform(get("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void Test_Get_Booking_By_Id() throws Exception {

        User user = new User(1L, ADMIN, "hola", "hola", "hola");
        user.setId(1L);
        user.setRole(ADMIN);
        user.setPassword("hola");
        user.setEmail("hola");
        user.setUsername("hola");

        Route route= new Route("SVQ-HUE",1L);
        route.setNameRoute("SVQ-HUE");
        route.setId(1L);

        Booking booking = new Booking(1L,"Eva","Porter",650349024,"Female","hello@gmail.com",null,"dni","3454556","street piruleta",21003,"Spain","seville",user,route,3);

        when(bookingService.getBookingById(1L)).thenReturn(booking);


        mockMvc.perform(get("/api/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(bookingService, times(1)).getBookingById(1L);
    }

  @Test
  void test_Create_Booking() throws Exception {
    Booking booking =
        new Booking(
            1L,
            "Fran",
            "Cano",
            43754,
            "male",
            "hola@hola.es",
            null,
            "dni",
            "3454556",
            "hola",
            3424,
            "spain",
            "huelva",
            null,
            null,
                3);
    booking.setId(1L);

    when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

    mockMvc
        .perform(
            post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"id\":1,\"name\":\"Fran\",\"surname\":\"Cano\",\"email\":\"hola@hola.es\",\"city\":\"huelva\",\"country\":\"spain\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1));

    verify(bookingService, times(1)).createBooking(any(Booking.class));
  }

  @Test
  public void test_Update_Booking() {
        Long id = 1L;
        Booking booking = new Booking(1L,"Krisel","hola",4968034,"Female","krisel@gmail.com",null,"Dni","87435438","estepona",41002,"spain","seville",null,null,3);
        booking.setId(id);

        bookingService.updateBooking(booking);

        verify(bookingService, times(1)).updateBooking(booking);
    }

    @Test
    void delete_Booking_By_Id() throws Exception {
        long BookingId = 1L;

        mockMvc.perform(delete("/api/bookings/{id}", BookingId))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).deleteBooking(BookingId);
    }
}
