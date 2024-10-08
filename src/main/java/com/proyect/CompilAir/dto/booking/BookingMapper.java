package com.proyect.CompilAir.dto.booking;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.models.User;
import org.springframework.stereotype.Component;


@Component
public class BookingMapper {
    public BookingDTO convertToDTO(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }

        User user = booking.getUser();
        Route route = booking.getRoute();

        return new BookingDTO(
                booking.getId(),
                user != null ? user.getId() : null, // Manejo de usuario nulo
                booking.getName(),
                booking.getSurname(),
                booking.getPhone(),
                booking.getEmail(),
                booking.getBirthdayDate(),
                booking.getIdentificationNumber(),
                booking.getGenre(),
                booking.getIdentificationType(),
                booking.getAddress(),
                booking.getZipCode(),
                booking.getCountry(),
                booking.getCity(),
                route != null ? route.getId() : null // Manejo de ruta nula
        );
    }

    public Booking convertToEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            throw new IllegalArgumentException("BookingDTO cannot be null");
        }

        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setName(bookingDTO.getName());
        booking.setSurname(bookingDTO.getSurname());
        booking.setPhone(bookingDTO.getPhone());
        booking.setEmail(bookingDTO.getEmail());
        booking.setBirthdayDate(bookingDTO.getBirthdayDate());
        booking.setIdentificationNumber(bookingDTO.getIdentificationNumber());
        booking.setGenre(bookingDTO.getGenre());
        booking.setIdentificationType(bookingDTO.getIdentificationType());
        booking.setAddress(bookingDTO.getAddress());
        booking.setZipCode(bookingDTO.getZipCode());
        booking.setCountry(bookingDTO.getCountry());
        booking.setCity(bookingDTO.getCity());

        // Crear y asignar ruta
        Route route = new Route();
        route.setId(bookingDTO.getRouteId());
        booking.setRoute(route);

        // Crear y asignar usuario
        User user = new User();
        user.setId(bookingDTO.getUserId());
        booking.setUser(user);

        return booking;
    }
}
