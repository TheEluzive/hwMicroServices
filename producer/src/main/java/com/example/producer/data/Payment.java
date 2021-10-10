package com.example.producer.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import validators.CardNumber;
import validators.PaymentValue;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment implements Payload {

    private long id;
    @CardNumber
    private long senderId;
    @PaymentValue
    private long amount;
    @Size(min = 1, max = 20)
    private String comment;
}
