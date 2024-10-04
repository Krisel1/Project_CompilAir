package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.dto.booking.BookingDTO;
import com.proyect.CompilAir.models.Booking;
<<<<<<< HEAD

=======
import com.proyect.CompilAir.models.Flight;
>>>>>>> e185ac729c7143fd5a2862ced467671ae97faf39
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
    public ArrayList<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        BookingDTO bookingDTO = toDTO(booking);
        return ResponseEntity.ok(bookingDTO);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = BookingMapper.toEntity(bookingDTO);

        Booking savedBooking = bookingService.createBooking(booking);
        BookingDTO savedBookingDTO = toDTO(savedBooking);
        String message = "Booking created successfully";

        Map<String, Object> response = new HashMap<>();
        response.put("booking", savedBookingDTO);
        response.put("message", message);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public void updateBooking(@Valid @RequestBody BookingDTO bookingDTO, @PathVariable("id") Long id) {
        Booking booking = toEntity(bookingDTO);
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
                booking.getFlight().getId(),
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
        booking.setFlight(new Flight());
        return booking;
    }
}
