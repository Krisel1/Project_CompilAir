package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.dto.payment.PaymentDTO;
import com.proyect.CompilAir.dto.payment.PaymentMapper;
import com.proyect.CompilAir.models.Payment;
import com.proyect.CompilAir.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api/payments")
    public class PaymentController {

        @Autowired
        private PaymentService paymentService;


    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.createPayment(
                    paymentDTO.getBookingId(),
                    paymentDTO.getAmount(),
                    paymentDTO.getCurrency(),
                    paymentDTO.getCardHolderName(),
                    paymentDTO.getCardLastFourDigits(),
                    paymentDTO.getCardType()
            );

            PaymentDTO responseDTO = PaymentMapper.toDTO(payment);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


        @GetMapping("/{id}")
        public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
            try {

                Payment payment = paymentService.getPaymentById(id);


                PaymentDTO responseDTO = PaymentMapper.toDTO(payment);

                return ResponseEntity.ok(responseDTO);
            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }

