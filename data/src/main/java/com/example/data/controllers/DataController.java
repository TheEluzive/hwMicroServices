package com.example.data.controllers;

import com.example.data.dto.Payment;
import com.example.data.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DataController {

    private final DataService dataService;

    @GetMapping("/payments")
    public List<Payment> getPayments() {

        return dataService.getPayments();

    }
}
