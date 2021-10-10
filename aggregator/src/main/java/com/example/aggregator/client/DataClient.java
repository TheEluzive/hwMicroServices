package com.example.aggregator.client;

import com.example.aggregator.dto.Payment;
import com.example.aggregator.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "data")
public interface DataClient {
    @GetMapping
    ResponseDto getValue();

    @GetMapping("/payments")
    List<Payment> getPayments();
}
