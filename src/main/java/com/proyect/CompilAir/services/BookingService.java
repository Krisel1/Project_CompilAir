package com.proyect.CompilAir.services;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.repositories.IBookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookingService {

    private final IBookingRepository ibookingRepository;

    public BookingService(IBookingRepository ibookingRepository) {
        this.ibookingRepository = ibookingRepository;
    }

    public ArrayList<Booking> getAllBookings(){
        return(ArrayList<Booking>) ibookingRepository.findAll();
    }

    public Booking getBookingById(Long id){
        Booking booking;
        booking = ibookingRepository.findById(id).orElseThrow();
        return booking;
    }


    public Booking createBooking(Booking newBooking){
        return ibookingRepository.save(newBooking);
    }

    public void updateBooking(Booking booking){
        ibookingRepository.save(booking);
    }

    public String deleteBooking(Long id){
        try{
            ibookingRepository.deleteById(id);
            return "Delete Booking successful";
        }catch (Exception error){
            return "Booking not Found with" + id;
        }
    }
}
