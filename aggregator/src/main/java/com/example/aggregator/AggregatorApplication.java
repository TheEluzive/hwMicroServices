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
@RestController
@RequiredArgsConstructor
@CommonsLog
public class AggregatorApplication {
  private final DiscoveryClient discoveryClient;
  private final DataClient dataClient;
  private final UsersClient usersClient;

  @GetMapping("/value")
  public ResponseDto value(@RequestHeader("Authorization") Optional<String> token) {
//    final List<ServiceInstance> services = discoveryClient.getInstances("data");
//    logger.info(services.stream().map(o -> o.getUri().toString()).collect(Collectors.joining(", ")));
    log.info(token);
    return dataClient.getValue();
  }

  @GetMapping("/api/payments")
  public HashMap<String, List<Payment>> payments(){
    //log.info("we are in api/payments");
    final var payments = dataClient.getPayments();
    //log.info("we are in data.getPayments");
    final var senderIdMap = new HashMap<Long, Long>();
    for (Payment payment: payments
         ) {
        senderIdMap.put(payment.getSenderId(),payment.getSenderId());
    }


    final var users = usersClient.getValue(senderIdMap);
    //log.info("we are in users.getValue");
    log.info(users.toString());
    final var usersHashMap = new HashMap<Long, String>();
    for (UsernameDto username: users) {
      usersHashMap.put(
              username.getId(), username.getUsername()
      );

    }

    final var paymentsWithUsername = new HashMap<String, List<Payment>>();
    var senderId = 0L;
    var senderName = "";
    for (Payment payment: payments
    ) {
      senderId = payment.getSenderId();
      senderName = usersHashMap.get(senderId);


      paymentsWithUsername.computeIfAbsent(
              senderName, k -> new ArrayList<>()).//check to null
              add(payment);
    }
    log.info("NEXT STEP IS RETURN");
    return paymentsWithUsername;

  }


  public static void main(String[] args) {
    SpringApplication.run(AggregatorApplication.class, args);
  }
}
