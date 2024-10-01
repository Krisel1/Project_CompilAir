package com.proyect.CompilAir.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.CompilAir.CompilAirApplication;
import com.proyect.CompilAir.dto.payment.PaymentDTO;
import com.proyect.CompilAir.models.Booking;
import com.proyect.CompilAir.models.Payment;
import com.proyect.CompilAir.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    void testCreatePayment_Success() throws Exception {

        PaymentDTO paymentDTO = new PaymentDTO(
                1L, 200.00, "USD", "Pending", LocalDateTime.now(),
                "John Doe", "1234", "Visa"
        );


        Booking booking = new Booking();
        booking.setId(1L);



        Payment payment = new Payment(1L, 200.00, "USD", "Completed",
                paymentDTO.getPaymentDate(), paymentDTO.getCardHolderName(),
                paymentDTO.getCardLastFourDigits(), booking, paymentDTO.getCardType());


        when(paymentService.createPayment(
                anyLong(), anyDouble(), anyString(), anyString(), anyString(), any(LocalDateTime.class), anyString()
        )).thenReturn(payment);


        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":1,\"amount\":200.00,\"currency\":\"USD\"," +
                                "\"cardHolderName\":\"John Doe\",\"paymentDate\":\"" + paymentDTO.getPaymentDate() +
                                "\",\"cardLastFourDigits\":\"1234\",\"cardType\":\"Visa\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(1L))
                .andExpect(jsonPath("$.amount").value(200.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.paymentStatus").value("Completed"))
                .andExpect(jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(jsonPath("$.cardLastFourDigits").value("1234"))
                .andExpect(jsonPath("$.cardType").value("Visa"));


        verify(paymentService, times(1)).createPayment(
                anyLong(), anyDouble(), anyString(), anyString(), anyString(), any(LocalDateTime.class), anyString()
        );
    }
    @Test
    void testGetPaymentById_Success() throws Exception {

        Booking booking = new Booking();
        booking.setId(1L);


        Payment payment = new Payment(1L, 200.00, "USD", "Completed",
                LocalDateTime.now(), "John Doe", "1234", booking, "Visa");


        when(paymentService.getPaymentById(1L)).thenReturn(payment);


        mockMvc.perform(get("/api/payments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(1L))
                .andExpect(jsonPath("$.amount").value(200.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.paymentStatus").value("Completed"))
                .andExpect(jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(jsonPath("$.cardLastFourDigits").value("1234"))
                .andExpect(jsonPath("$.cardType").value("Visa"));


        verify(paymentService, times(1)).getPaymentById(1L);
    }
    @Test
    void testGetPaymentById_NotFound() throws Exception {

        when(paymentService.getPaymentById(anyLong())).thenThrow(new Exception("Payment not found"));


        mockMvc.perform(get("/api/payments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


        verify(paymentService, times(1)).getPaymentById(1L);
    }
}
