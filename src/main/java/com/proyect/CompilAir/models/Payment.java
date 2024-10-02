package com.proyect.CompilAir.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false)
    private String cardLastFourDigits;

    @Column(nullable = false)
    private String cardType;

    public Payment(Long id, double amount, String currency, String paymentStatus, LocalDateTime paymentDate, String cardHolderName, String cardLastFourDigits, Booking booking, String cardType) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.cardHolderName = cardHolderName;
        this.cardLastFourDigits = cardLastFourDigits;
        this.booking = booking;
        this.cardType = cardType;
    }

    public Payment() {
    }

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    public Object getPaymentName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentName'");
    }

    public void setPaymentDate(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPaymentDate'");
    }


}