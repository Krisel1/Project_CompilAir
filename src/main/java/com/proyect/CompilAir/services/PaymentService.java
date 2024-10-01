package com.proyect.CompilAir.services;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Payment;
import com.proyect.CompilAir.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PaymentService {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentRepository paymentRepository;


    public Payment createPayment(Long bookingId, double amount, String currency, String cardHolderName, String cardLastFourDigits, LocalDateTime paymentDate, String cardType) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);

        if (booking == null) {
            throw new Exception("Booking not found");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setCurrency(currency);
        payment.setPaymentStatus("Pending");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(booking);
        payment.setCardHolderName(cardHolderName);
        payment.setCardLastFourDigits(cardLastFourDigits);
        payment.setCardType(cardType);

        payment.setPaymentStatus("Completed");

        return paymentRepository.save(payment);
    }


    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new Exception("Payment not found"));
    }


    public Payment updatePayment(long l, Payment payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePayment'");
    }
}
