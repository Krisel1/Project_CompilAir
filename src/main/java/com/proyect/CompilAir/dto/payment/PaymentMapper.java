package com.proyect.CompilAir.dto.payment;

import com.proyect.CompilAir.models.Payment;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setBookingId(payment.getBooking().getId());
        dto.setAmount(payment.getAmount());
        dto.setCurrency(payment.getCurrency());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setCardHolderName(payment.getCardHolderName());
        dto.setCardLastFourDigits(payment.getCardLastFourDigits());
        dto.setCardType(payment.getCardType());
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        Payment payment = new Payment();

        return payment;
    }
}

