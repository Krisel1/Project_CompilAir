package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.services.BookingService;
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

    @GetMapping(path = "")
    public ArrayList<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping(path = "/{id}")
    public Booking getBookingById(@PathVariable("id") Long id){
        return bookingService.getBookingById(id);
    }

    @PostMapping(path ="")
    public Booking createBooking(@RequestBody Booking newBooking){
        return bookingService.createBooking(newBooking);
    }

    @PutMapping(path ="/{id}")
    public void updateBooking(@RequestBody Booking booking, @PathVariable("id") Long id) {
        booking.setId(id);
        bookingService.updateBooking(booking);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteBooking(@PathVariable("id")Long id){
        return bookingService.deleteBooking(id);
    }
}
