package com.alura.microservice.email.consumer;

import com.alura.microservice.email.dto.VoucherDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @KafkaListener(topics = "${info.fornecedor.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(ConsumerRecord<String, VoucherDTO> consumerRecord) {
        System.out.println("_________________________________________");
        System.out.println("Enviando e-mail");
        System.out.println(consumerRecord.value());
    }

}
