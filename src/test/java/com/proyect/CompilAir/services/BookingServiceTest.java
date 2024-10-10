package com.proyect.CompilAir.services;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.repositories.IBookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

  @Mock private IBookingRepository iBookingRepository;

  @Mock private FlightService flightService;
  @Mock private RouteService routeService;

  @InjectMocks private BookingService bookingService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void test_Get_All_Bookings() {

    List<Booking> mockProjects = new ArrayList<>();
    mockProjects.add(
        new Booking(
            1L,
            "Jacky",
            "Maravi",
            4962034,
            "Female",
            "jacky@gmail.com",
            null,
            "Dni",
            "97435438",
            "cubillas",
            21006,
            "spain",
            "huelva",
            null,
            null,
            3,
            null));
    mockProjects.add(
        new Booking(
            1L,
            "Fran",
            "Cano",
            4968031,
            "male",
            "fran@gmail.com",
            null,
            "Dni",
            "87435438",
            "seca",
            21005,
            "spain",
            "huelva",
            null,
            null,
            3,
            null));
    mockProjects.add(
        new Booking(
            1L,
            "Krisel",
            "Urdaneta",
            4968030,
            "Female",
            "krisel@gmail.com",
            null,
            "Dni",
            "87435408",
            "estepona",
            41002,
            "spain",
            "seville",
            null,
            null,
            3,
            null));
    mockProjects.add(
        new Booking(
            1L,
            "Eva",
            "Martinez",
            4968034,
            "Female",
            "eva@gmail.com",
            null,
            "Dni",
            "87435430",
            "guadalquivir",
            41005,
            "spain",
            "seville",
            null,
            null,
            3,
            null));
    mockProjects.add(
        new Booking(
            1L,
            "Hilmar",
            "Hernandez",
            4968034,
            "Female",
            "hilmar@gmail.com",
            null,
            "Dni",
            "87430438",
            "giralda",
            41007,
            "spain",
            "seville",
            null,
            null,
            3,
            null));
    when(iBookingRepository.findAll()).thenReturn(mockProjects);

    ArrayList<Booking> result = bookingService.getAllBookings();

    assertNotNull(result);
    assertEquals(5, result.size());
    assertEquals("Jacky", result.get(0).getName());
    assertEquals("Fran", result.get(1).getName());
    assertEquals("Krisel", result.get(2).getName());
    assertEquals("Eva", result.get(3).getName());
    assertEquals("Hilmar", result.get(4).getName());

    verify(iBookingRepository, times(1)).findAll();
  }

  @Test
  public void test_Get_Booking_By_Id() {

    Booking mockProject =
        new Booking(
            1L,
            "Alexia",
            "Perez",
            49680345,
            "Female",
            "alexia@gmail.com",
            null,
            "Dni",
            "47430438",
            "brunete",
            43545,
            "spain",
            "cadiz",
            null,
            null,
            3,
            null);
    Long bookingId = 1L;

    when(iBookingRepository.findById(bookingId)).thenReturn(Optional.of(mockProject));

    Booking result = bookingService.getBookingById(bookingId);

    assertNotNull(result);
    assertEquals("Alexia", result.getName());

    verify(iBookingRepository, times(1)).findById(bookingId);
  }

  @Test
  public void test_Create_Booking() {

    Flight mockFlight = mock(Flight.class);

    when(mockFlight.availableSeats()).thenReturn(10L);
    when(mockFlight.getId()).thenReturn(1L);

    Booking newBooking =
        new Booking(
            1L,
            "Eva",
            "Porter",
            650349024,
            "Female",
            "hello@gmail.com",
            null,
            "dni",
            "3454556",
            "street piruleta",
            21003,
            "Spain",
            "seville",
            null,
            new Route(),
            3,
            mockFlight);

    when(flightService.getFlightById(mockFlight.getId())).thenReturn(mockFlight);
    when(routeService.getRouteById(Mockito.anyLong())).thenReturn(Optional.of(new Route()));
    when(iBookingRepository.save(newBooking)).thenReturn(newBooking);

    Booking result = bookingService.createBooking(newBooking);

    assertNotNull(result);
    assertEquals("Eva", result.getName());
    assertEquals(mockFlight, result.getFlight());
    verify(iBookingRepository, times(1)).save(newBooking);
    verify(flightService, times(1)).updateFlight(mockFlight.getId(), mockFlight);
  }

  @Test
  public void testUpdateProject() {

    Booking booking =
        new Booking(
            1L,
            "Eva",
            "Porter",
            650349024,
            "Female",
            "hello@gmail.com",
            null,
            "dni",
            "3454556",
            "street piruleta",
            21003,
            "Spain",
            "seville",
            null,
            null,
            3,
            null);

    bookingService.updateBooking(booking);

    verify(iBookingRepository, times(1)).save(booking);
  }

  @Test
  public void test_Delete_Booking_Success() {

    Long bookingId = 1L;

    String result = bookingService.deleteBooking(bookingId);

    verify(iBookingRepository, times(1)).deleteById(bookingId);

    assertEquals("Delete Booking successful", result);
  }

  @Test
  public void test_Delete_if_Booking_Not_Found() {

    Long bookingId = 1L;
    doThrow(new RuntimeException("Booking not found"))
        .when(iBookingRepository)
        .deleteById(bookingId);

    String result = bookingService.deleteBooking(bookingId);

    verify(iBookingRepository, times(1)).deleteById(bookingId);

    assertEquals("Booking not Found", result);
  }
}
