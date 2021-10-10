package com.example.aggregator.controllers;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UsersClient;
import com.example.aggregator.dto.Payment;
import com.example.aggregator.dto.ResponseDto;
import com.example.aggregator.dto.UsernameDto;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@EnableFeignClients
@RestController
@AllArgsConstructor
@CommonsLog
public class Controller {
    private final DataClient dataClient;
    private final UsersClient usersClient;

    @GetMapping("/value")
    public ResponseDto value(@RequestHeader("Authorization") Optional<String> token) {
        log.info(token);
        return dataClient.getValue();
    }

    @GetMapping("/api/payments")
    public HashMap<String, List<Payment>> payments() {

        final var payments = dataClient.getPayments();
        final var senderIdMap = new HashMap<Long, Long>();
        for (Payment payment : payments
        ) {
            senderIdMap.put(payment.getSenderId(), payment.getSenderId());
        }


        final var users = usersClient.getValue(senderIdMap);
        log.info(users.toString());
        final var usersHashMap = new HashMap<Long, String>();
        for (UsernameDto username : users) {
            usersHashMap.put(
                    username.getId(), username.getUsername()
            );

        }

        final var paymentsWithUsername = new HashMap<String, List<Payment>>();
        var senderId = 0L;
        var senderName = "";
        for (Payment payment : payments
        ) {
            senderId = payment.getSenderId();
            senderName = usersHashMap.get(senderId);


            paymentsWithUsername.computeIfAbsent(
                    senderName, k -> new ArrayList<>()).//check to null
                    add(payment);
        }

        return paymentsWithUsername;

    }
}
