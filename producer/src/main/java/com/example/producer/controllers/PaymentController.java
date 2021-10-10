package com.example.producer.controllers;

import com.example.producer.data.Payment;
import com.example.producer.services.KafkaActions;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@Validated
public class PaymentController {
    private final KafkaActions kafkaActions;


    @PostMapping("/api/payments")
    public void registerPayment(@RequestBody @Valid Payment payment) {
        kafkaActions.send(payment);
    }

}
