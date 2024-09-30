package com.proyect.CompilAir.dto.payment;

import com.proyect.CompilAir.models.Payment;

public class PaymentMapper {

        public static PaymentDTO toDTO(Payment payment) {
            PaymentDTO dto = new PaymentDTO();
            dto.setAmount(payment.getAmount());
            dto.setCurrency(payment.getCurrency());
            dto.setBookingId(payment.getId());
            return dto;
        }
    }

