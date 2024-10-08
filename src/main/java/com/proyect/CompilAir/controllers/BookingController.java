package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.dto.booking.BookingDTO;
import com.proyect.CompilAir.dto.booking.BookingMapper;
import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.proyect.CompilAir.dto.booking.BookingMapper.toDTO;
import static com.proyect.CompilAir.dto.booking.BookingMapper.toEntity;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @GetMapping
  public ArrayList<Booking> getAllBookings() {
    return bookingService.getAllBookings();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
    Booking booking = bookingService.getBookingById(id);
    BookingDTO bookingDTO = toDTO(booking);
    return ResponseEntity.ok(bookingDTO);
  }

  @PostMapping
  public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
    Booking booking = BookingMapper.toEntity(bookingDTO);

    Booking savedBooking = bookingService.createBooking(booking);
    BookingDTO savedBookingDTO = toDTO(savedBooking);
    String message = "Booking created successfully";

    Map<String, Object> response = new HashMap<>();
    response.put("booking", savedBookingDTO);
    response.put("message", message);

    return new ResponseEntity<Booking>((Booking) response, HttpStatus.CREATED);
  }

  @PutMapping(path = "/{id}")
  public void updateBooking(
      @Valid @RequestBody BookingDTO bookingDTO, @PathVariable("id") Long id) {
    Booking booking = toEntity(bookingDTO);
    booking.setId(id);
    bookingService.updateBooking(booking);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id) {
    try {
      String response = bookingService.deleteBooking(id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
    }
  }
}
