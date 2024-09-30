package com.proyect.CompilAir.controllers;


import com.proyect.CompilAir.dto.payment.PaymentDTO;
import com.proyect.CompilAir.models.Payment;
import com.proyect.CompilAir.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void test_Create_Payment_Success() throws Exception {

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setBookingId(1L);
        paymentDTO.setAmount(200.00);
        paymentDTO.setCurrency("USD");
        paymentDTO.setPaymentStatus("Completed");
        paymentDTO.setCardHolderName("John Doe");
        paymentDTO.setCardLastFourDigits("1234");
        paymentDTO.setCardType("Visa");
        paymentDTO.setPaymentDate(LocalDateTime.of(2024, 9, 25, 10, 0)); // Agregar paymentDate


        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setCurrency(paymentDTO.getCurrency());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());
        payment.setCardHolderName(paymentDTO.getCardHolderName());
        payment.setCardLastFourDigits(paymentDTO.getCardLastFourDigits());
        payment.setCardType(paymentDTO.getCardType());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        when(paymentService.createPayment(eq(paymentDTO.getBookingId()), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(payment);

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":1,\"amount\":200.00,\"currency\":\"USD\", \"cardHolderName\":\"John Doe\", \"cardLastFourDigits\":\"1234\", \"cardType\":\"Visa\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(200.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.paymentStatus").value("Completed"))
                .andExpect(jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(jsonPath("$.cardLastFourDigits").value("1234"))
                .andExpect(jsonPath("$.cardType").value("Visa"))
                .andExpect(jsonPath("$.paymentDate").value("2024-09-25T10:00:00"));

        verify(paymentService, times(1)).createPayment(eq(paymentDTO.getBookingId()), anyDouble(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void test_Create_Payment_Booking_Not_Found() throws Exception {

        when(paymentService.createPayment(eq(1L), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new Exception("Booking not found"));

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":1,\"amount\":200.00,\"currency\":\"USD\", \"cardHolderName\":\"John Doe\", \"cardLastFourDigits\":\"1234\", \"cardType\":\"Visa\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Booking not found"));

        verify(paymentService, times(1)).createPayment(eq(1L), anyDouble(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void test_Get_Payment_By_Id() throws Exception {

        Payment payment = new Payment();
        payment.setAmount(200.00);
        payment.setCurrency("USD");
        payment.setPaymentStatus("Completed");
        payment.setCardHolderName("John Doe");
        payment.setCardLastFourDigits("1234");
        payment.setCardType("Visa");
        payment.setPaymentDate(LocalDateTime.of(2024, 9, 25, 10, 0)); // Agregar paymentDate

        when(paymentService.getPaymentById(1L)).thenReturn(payment);


        mockMvc.perform(get("/api/payments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(200.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.paymentStatus").value("Completed"))
                .andExpect(jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(jsonPath("$.cardLastFourDigits").value("1234"))
                .andExpect(jsonPath("$.cardType").value("Visa"))
                .andExpect(jsonPath("$.paymentDate").value("2024-09-25T10:00:00"));

        verify(paymentService, times(1)).getPaymentById(1L);
    }

    @Test
    void test_Get_Payment_Not_Found() throws Exception {

        when(paymentService.getPaymentById(1L)).thenThrow(new Exception("Payment not found"));


        mockMvc.perform(get("/api/payments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Payment not found"));

        verify(paymentService, times(1)).getPaymentById(1L);
    }
}
