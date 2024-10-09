package com.proyect.CompilAir.services;

import com.proyect.CompilAir.excepcions.ResourceNotFoundException;
import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.repositories.IBookingRepository;
import com.proyect.CompilAir.repositories.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class BookingService {

    private IBookingRepository iBookingRepository;
    private FlightService flightService;
@Autowired
    public BookingService(IBookingRepository ibookingRepository, FlightService flightService) {
        this.iBookingRepository = ibookingRepository;
        this.flightService = flightService;
    }


    public ArrayList<Booking> getAllBookings(){
        return(ArrayList<Booking>) iBookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return iBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    public Booking createBooking(Booking newBooking){
        if(newBooking.getFlight() == null) {
            throw new IllegalArgumentException("Flight cannot be null.");
        }

        Flight flight = flightService.getFlightById(newBooking.getFlight().getId());
        int requestedSeats = newBooking.getNumberOfPlaces();

        if(flight.availableSeats() >= requestedSeats) {
      flight.setReservedSeats(flight.getReservedSeats() + requestedSeats);

      flightService.updateFlight(flight.getId(), flight);
      return iBookingRepository.save(newBooking);
        } else {
            throw new IllegalArgumentException("Not enough seats available for this booking.");
        }
    }

    public void updateBooking(Booking booking){
        iBookingRepository.save(booking);
    }

    public String deleteBooking(Long id){
        try{
            iBookingRepository.deleteById(id);
            return "Delete Booking successful";
        }catch (Exception error){
            return "Booking not Found";
        }
    }
    public BookingService() {
    }

}
