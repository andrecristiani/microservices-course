package com.alura.microservice.email.utils;

import com.alura.microservice.email.dto.VoucherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class VoucherDeserializer implements Deserializer<VoucherDTO> {
    @Override
    public VoucherDTO deserialize(String topic, byte[] data) {
        VoucherDTO voucher = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            voucher = mapper.readValue(data, VoucherDTO.class);
        } catch(IOException e) {
            System.err.println(e);
        }
        return voucher;
    }
}
