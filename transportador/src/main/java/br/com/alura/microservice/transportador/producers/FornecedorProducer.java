package br.com.alura.microservice.transportador.producers;

import br.com.alura.microservice.transportador.dto.VoucherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Component
public class FornecedorProducer {

    @Value("${info.fornecedor.topic}")
    private String infoFornecedorTopic;

    private final KafkaTemplate kafkaTemplate;

    public FornecedorProducer(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final @RequestBody VoucherDTO voucher) {
        final String messageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(infoFornecedorTopic, messageKey, voucher);
    }
}
