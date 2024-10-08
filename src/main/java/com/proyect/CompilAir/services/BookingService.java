package com.proyect.CompilAir.services;

import com.proyect.CompilAir.excepcions.ResourceNotFoundException;
import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.repositories.IBookingRepository;
import com.proyect.CompilAir.repositories.IFlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class BookingService {

    private final IBookingRepository iBookingRepository;
    private final IFlightRepository iFlightRepository;

    public BookingService(IBookingRepository ibookingRepository, IFlightRepository iFlightRepository) {
        this.iBookingRepository = ibookingRepository;
        this.iFlightRepository = iFlightRepository;
    }

    public ArrayList<Booking> getAllBookings(){
        return(ArrayList<Booking>) iBookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return iBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    @Transactional
    public Booking createBooking(Booking newBooking) {
        if (newBooking.getRoute() == null || newBooking.getRoute().getFlights().isEmpty()) {
            throw new IllegalArgumentException("Route and Flight information must be provided");
        }

        Flight flight = newBooking.getRoute().getFlights().iterator().next(); // Obtener el primer vuelo

        Long flightId = flight.getId();

        flight = iFlightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with ID: " + flightId));

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available for this flight");
        }

        flight.setReservedSeats(flight.getReservedSeats() + 1);
        iFlightRepository.save(flight);

        return iBookingRepository.save(newBooking);
    }

    @Transactional
    public Booking updateBooking(Booking booking) {
        if (!iBookingRepository.existsById(booking.getId())) {
            throw new ResourceNotFoundException("Booking not found with ID: " + booking.getId());
        }
        return iBookingRepository.save(booking);
    }

    public String deleteBooking(Long id){
        try{
            iBookingRepository.deleteById(id);
            return "Delete Booking successful";
        }catch (Exception error){
            return "Booking not Found";
        }
    }
}
