package com.example.producer.services;

import com.example.producer.data.Payment;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@AllArgsConstructor
@Service
public class KafkaActions {
    private final KafkaTemplate<String, Payment> template;
    private final Log logger = LogFactory.getLog(this.getClass());

    public void send(Payment payment) {
        final ListenableFuture<SendResult<String, Payment>> future = template.send(
                new ProducerRecord<>("payments", "ibank", payment));
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
        return new NewTopic("payments", 3, (short) 2);
    }

}
