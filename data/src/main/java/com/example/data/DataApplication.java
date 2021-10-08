package com.example.data;

import com.example.data.dto.DataService;
import com.example.data.dto.Payment;
import com.example.data.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
@RestController

public class DataApplication {
  private final Log logger = LogFactory.getLog(this.getClass());
  private DataService dataService;

  @Bean
  public DataService startService(JdbcTemplate jdbcTemplate){
    this.dataService = new DataService(jdbcTemplate);
    return this.dataService;
  };

  @Setter(onMethod_={@Value("${app.id}")})
  private String id;

  @GetMapping
  public ResponseDto endpoint(@RequestHeader Optional<String> authorization) {
    logger.info("request");
//    throw new RuntimeException();
    return new ResponseDto(id);
  }

  public static void main(String[] args) {
    SpringApplication.run(DataApplication.class, args);
  }


  @KafkaListener(groupId = "consumer", topics = "payments")
  public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment) {
    logger.info(message);
    dataService.addPayment(message);


    acknowledgment.acknowledge();

  }




}
