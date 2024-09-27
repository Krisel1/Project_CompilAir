package com.proyect.CompilAir.services;

import com.proyect.CompilAir.excepcions.ResourceNotFoundException;
import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.repositories.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class BookingService {

    @Autowired
    private final IBookingRepository iBookingRepository;

    public BookingService(IBookingRepository ibookingRepository) {
        this.iBookingRepository = ibookingRepository;
    }

    public ArrayList<Booking> getAllBookings(){
        return(ArrayList<Booking>) iBookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return iBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    public Booking createBooking(Booking newBooking){
        return iBookingRepository.save(newBooking);
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
}
