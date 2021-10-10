package com.example.data.services;

import com.example.data.dto.Payment;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class KafkaActions {
    private final Log logger = LogFactory.getLog(this.getClass());
    private DataService dataService;


    @KafkaListener(groupId = "consumer", topics = "featuredPayments")
    public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment) {
        logger.info(message);
        dataService.addPayment(message);
        acknowledgment.acknowledge();
    }
}
