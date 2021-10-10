package com.example.consumer.services;

import com.example.consumer.data.Payment;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@AllArgsConstructor
@Service
public class KafkaActions {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final KafkaTemplate<String, Payment> template;

    @KafkaListener(groupId = "consumer", topics = "payments")
    public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment) {
        logger.info(message);
        send(message);

        acknowledgment.acknowledge();

    }

    public void send(Payment payment){
        final ListenableFuture<SendResult<String, Payment>> future = template.send(
                new ProducerRecord<>("featuredPayments", "ibank", payment
                ));
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Payment> result) {
                logger.info(result);
            }
        });
    }


    @Bean // Kafka Admin
    public NewTopic messagesTopic() {
        return new NewTopic("featuredPayments", 3, (short)2);
    }
}
