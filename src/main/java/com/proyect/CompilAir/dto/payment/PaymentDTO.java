package com.proyect.CompilAir.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
        private Long bookingId;
        private double amount;
        private String currency;
        private String paymentStatus;
        private LocalDateTime paymentDate;
        private String cardHolderName;
        private String cardLastFourDigits;
        private String cardType;
}




