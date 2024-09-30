package com.proyect.CompilAir.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
        private Long bookingId;
        private double amount;
        private String currency;
}
