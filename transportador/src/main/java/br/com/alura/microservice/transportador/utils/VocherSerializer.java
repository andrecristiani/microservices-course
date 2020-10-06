package br.com.alura.microservice.transportador.utils;

import br.com.alura.microservice.transportador.dto.VoucherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class VocherSerializer implements Serializer<VoucherDTO> {

    @Override
    public byte[] serialize(String topic, VoucherDTO voucher) {
        byte[] serializedValue =  new byte[0];
        ObjectMapper objectMapper = new ObjectMapper();
        if(voucher != null) {
            try {
                serializedValue = objectMapper.writeValueAsString(voucher).getBytes();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return serializedValue;
    }
}
