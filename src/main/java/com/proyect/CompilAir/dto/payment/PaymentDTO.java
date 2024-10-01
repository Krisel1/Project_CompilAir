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

        public PaymentDTO(Long bookingId, double amount, String currency, String paymentStatus, LocalDateTime paymentDate, String cardHolderName, String cardLastFourDigits, String cardType) {
                this.bookingId = bookingId;
                this.amount = amount;
                this.currency = currency;
                this.paymentStatus = paymentStatus;
                this.paymentDate = paymentDate;
                this.cardHolderName = cardHolderName;
                this.cardLastFourDigits = cardLastFourDigits;
                this.cardType = cardType;
        }

        public PaymentDTO() {
        }
}





