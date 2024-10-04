package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.dto.booking.BookingDTO;
import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ArrayList<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        BookingDTO bookingDTO = convertToDto(booking);
        return ResponseEntity.ok(bookingDTO);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        bookingService.createBooking(booking);
        BookingDTO responseDto = convertToDto(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping(path ="/{id}")
    public void updateBooking(@Valid @RequestBody BookingDTO bookingDTO, @PathVariable("id") Long id) {
        Booking booking = convertToEntity(bookingDTO);
        booking.setId(id);
        bookingService.updateBooking(booking);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id){
        try {
            String response = bookingService.deleteBooking(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }

    private BookingDTO convertToDto(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getName(),
                booking.getSurname(),
                booking.getEmail(),
                booking.getCity(),
                booking.getCountry(),
                booking.getRoute().getId(),
                booking.getUser().getId()
        );
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setName(bookingDTO.getName());
        booking.setSurname(bookingDTO.getSurname());
        booking.setEmail(bookingDTO.getEmail());
        booking.setCity(bookingDTO.getCity());
        booking.setCountry(bookingDTO.getCountry());
        booking.setRoute(new Route());
        return booking;
    }
}
