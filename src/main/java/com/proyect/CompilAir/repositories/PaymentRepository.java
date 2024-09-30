package com.proyect.CompilAir.repositories;

import com.proyect.CompilAir.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
