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

        Object id;
        payment = new Payment(1L,34,"ok","amparo",null,"ok","ok",null,null);
    }
    
    @Test
    public void createPayment() {
        Payment newPayment = new Payment();
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
    
        Payment createdPayment = paymentService.createPayment(newPayment);
    
        assertNotNull(createdPayment);
        assertEquals("1L", createdPayment.getPaymentName());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void getAllPayments() {
        List<Payment> paymentList = Arrays.asList(payment);
        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(1L, payments.size());
        assertEquals("ok", payments.get(0).getPaymentName());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    public void getPaymentById() {
        when(paymentRepository.findById(eq(1L))).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentService.getPaymentById(1L);

        assertNotNull(foundPayment);
        assertEquals("1L", foundPayment.getPaymentName());
        verify(paymentRepository, times(1)).findById(eq(1L));
    }

    //

    @Test
    public void testDeletePayment_Success() {
        when(paymentRepository.existsById(eq(1L))).thenReturn(true);

        boolean result = paymentService.deletePayment(1L);

        assertTrue(result);
        verify(paymentRepository, times(1)).existsById(eq(1L));
        verify(paymentRepository, times(1)).deleteById(eq(1L));
    }

    @Test
    public void deletePayment_NotFound() {
        when(paymentRepository.existsById(eq(1L))).thenReturn(false);

        boolean result = paymentService.deletePayment(1L);

        assertTrue(!result);
        verify(paymentRepository, times(1)).existsById(eq(1L));
        verify(paymentRepository, never()).deleteById(anyLong());
    }



}


