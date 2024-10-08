
package com.proyect.CompilAir.services;

import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Payment;
import com.proyect.CompilAir.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Booking mockBooking;
    private Payment mockPayment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockBooking = new Booking();
        mockBooking.setId(1L);

        mockPayment = new Payment();
        mockPayment.setId(1L);
        mockPayment.setAmount(100.00);
        mockPayment.setCurrency("USD");
        mockPayment.setBooking(mockBooking);
        mockPayment.setPaymentStatus("Completed");
    }

    @Test
    public void testCreatePayment_success() throws Exception {
        when(bookingService.getBookingById(1L)).thenReturn(mockBooking);


        mockPayment.setCardHolderName("Pedro Perez");
        mockPayment.setCardLastFourDigits("1234");
        mockPayment.setAmount(100.00);
        mockPayment.setCurrency("USD");
        mockPayment.setBooking(mockBooking);
        mockPayment.setPaymentStatus("Completed");

        when(paymentRepository.save(any(Payment.class))).thenReturn(mockPayment);


        Payment payment = paymentService.createPayment(
                1L, 100.00, "USD", "Pedro Perez", "1234", LocalDateTime.now(), "Visa"
        );


        assertNotNull(payment);
        assertEquals("Completed", payment.getPaymentStatus());
        assertEquals(mockBooking, payment.getBooking());
        assertEquals("Pedro Perez", payment.getCardHolderName());


        verify(bookingService, times(1)).getBookingById(1L);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void testCreatePayment_bookingNotFound() {

        when(bookingService.getBookingById(1L)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {

            paymentService.createPayment(1L, 100.00, "USD", "Pedro Perez", "1234", LocalDateTime.now(), "Visa");

        });

        assertEquals("Booking not found", exception.getMessage());


        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    public void testGetPaymentById_success() throws Exception {

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(mockPayment));


        Payment payment = paymentService.getPaymentById(1L);


        assertNotNull(payment);
        assertEquals(100.00, payment.getAmount());
        assertEquals("USD", payment.getCurrency());


        verify(paymentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPaymentById_notFound() {

        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());


        Exception exception = assertThrows(Exception.class, () -> {
            paymentService.getPaymentById(1L);
        });

        assertEquals("Payment not found", exception.getMessage());


        verify(paymentRepository, times(1)).findById(1L);
    }
}
