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

    public Payment(double amount, Long id, String currency, String paymentStatus, LocalDateTime paymentDate, Booking booking) {
        this.amount = amount;
        this.id = id;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.booking = booking;
    }

    public Payment() {
    }

    @OneToOne
    private Booking booking;
}