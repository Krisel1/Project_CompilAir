package com.proyect.CompilAir.repositories;

import com.proyect.CompilAir.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface IBookingRepository extends CrudRepository<Booking, Long> {
}
