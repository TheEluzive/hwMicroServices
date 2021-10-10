package com.example.aggregator;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UsersClient;
import com.example.aggregator.dto.Payment;
import com.example.aggregator.dto.ResponseDto;
import com.example.aggregator.dto.UsernameDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class AggregatorApplication {


  public static void main(String[] args) {
    SpringApplication.run(AggregatorApplication.class, args);
  }
}
