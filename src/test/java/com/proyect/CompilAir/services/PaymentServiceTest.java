<<<<<<< HEAD
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
            paymentService.createPayment(1L, 100.00, "USD", "John Doe", "1234", LocalDateTime.now(), "Visa");
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
=======
package com.proyect.CompilAir.services;


import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.models.Payment;
import com.proyect.CompilAir.repositories.PaymentRepository;
import com.stripe.param.TopupListParams.Amount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Payment payment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        //Object id;
        payment = new Payment(1L,34,"ok","amparo",null,"ok","ok",null,null);
    }
    
    @Test
    public void createPayment() {
        Payment newPayment = new Payment();
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
    
        Payment createdPayment = paymentService.createPayment(newPayment);
    
        assertNotNull(createdPayment);
        assertEquals("ok", createdPayment.getPaymentName());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void getAllPayments() {
        List<Payment> paymentList = Arrays.asList(payment);
        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(1, payments.size());
        assertEquals("ok", payments.get(0).getPaymentName());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    public void getPaymentById() {
        when(paymentRepository.findById(eq(1L))).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentService.getPaymentById(1L);

        assertNotNull(foundPayment);
        assertEquals("ok", foundPayment.getPaymentName());
        verify(paymentRepository, times(1)).findById(eq(1L));
    }
}


>>>>>>> c11cdd011e6ebdc9e12193a4275d439fccfbad94
