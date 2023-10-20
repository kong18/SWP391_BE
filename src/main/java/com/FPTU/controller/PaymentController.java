package com.FPTU.controller;

import com.FPTU.dto.PaymentRequestDTO;
import com.FPTU.dto.PaymentResponseDTO;
import com.FPTU.service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaypalService paypalService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            Payment payment = paypalService.createPayment(paymentRequest);

            PaymentResponseDTO paymentResponse = new PaymentResponseDTO();
            paymentResponse.setPaymentId(payment.getId());
            paymentResponse.setStatus(payment.getState());

            return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
        } catch (PayPalRESTException e) {
            // Handle PayPal payment creation error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/execute")
    public ResponseEntity<String> executePayment(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if ("approved".equalsIgnoreCase(payment.getState())) {
                // Handle successful payment confirmation
                return new ResponseEntity<>("Payment successful", HttpStatus.OK);
            } else {
                // Handle payment not approved
                return new ResponseEntity<>("Payment not approved", HttpStatus.BAD_REQUEST);
            }
        } catch (PayPalRESTException e) {
            // Handle PayPal payment execution error
            return new ResponseEntity<>("Failed to execute payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancel() {
        // Handle canceled payment
        return new ResponseEntity<>("Payment canceled", HttpStatus.OK);
    }
}
