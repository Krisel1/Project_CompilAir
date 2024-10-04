package com.proyect.CompilAir.dto.booking;

import com.proyect.CompilAir.models.Booking;


public class BookingMapper {
    public static BookingDTO toDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getName(),
                booking.getSurname(),
                booking.getEmail(),
                booking.getCity(),
                booking.getCountry()
        );
    }


    public static Booking toEntity(BookingDTO bookingDTO) {
    return new Booking(
            bookingDTO.getId(),
            bookingDTO.getName(),
            bookingDTO.getSurname(),
            bookingDTO.getEmail(),
            bookingDTO.getCity(),
            bookingDTO.getCountry()
    );

    }
}
