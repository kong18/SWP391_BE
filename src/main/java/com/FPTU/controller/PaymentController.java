package com.FPTU.controller;
import javax.servlet.http.HttpServletRequest;

import com.FPTU.model.PaypalPaymentIntent;
import com.FPTU.model.PaypalPaymentMethod;
import com.FPTU.service.PaypalService;
import com.FPTU.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/payment")
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(HttpServletRequest request, @RequestParam("price") double price) {
        String cancelUrl = "http://127.0.0.1:5173/" +  URL_PAYPAL_CANCEL;
        String successUrl = "http://127.0.0.1:5173/" + URL_PAYPAL_SUCCESS;

        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl
            );

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    // Return success response to the front-end
                    Map<String, String> response = new HashMap<>();
                    response.put("success", Boolean.toString(true));
                    response.put("successUrl", links.getHref());

                    return ResponseEntity.ok().body(response);
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        // Return error response to the front-end
        Map<String, String> response = new HashMap<>();
        response.put("success", Boolean.toString(false));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay() {
        return "cancel";
    }

//    @GetMapping(URL_PAYPAL_SUCCESS)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if (payment.getState().equals("approved")) {
//                return "success";
//            }
//        } catch (PayPalRESTException e) {
//            log.error(e.getMessage());
//        }
//        return "redirect:/";
//    }




}
