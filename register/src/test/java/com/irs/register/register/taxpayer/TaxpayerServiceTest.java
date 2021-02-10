package com.irs.register.register.taxpayer;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.register.register.application.controller.taxpayer.TaxpayerDTO;
import com.irs.register.register.application.controller.taxpayer.TaxpayerService;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

public class TaxpayerServiceTest {

    private TaxpayerService taxpayerService;

    private MockProducer<String, TaxPayer> mockProducer;

    @Test
    void sendMessage(){

        final MockProducer<String, TaxPayer> mockProducer = new MockProducer(true, new StringSerializer(), new KafkaAvroSerializer());

        taxpayerService = new TaxpayerService(mockProducer);

        final TaxpayerDTO taxpayerDTO = new TaxpayerDTO();
        taxpayerDTO.setDocument("12345678901");
        taxpayerDTO.setEmail("fake@email.com");
        taxpayerDTO.setName("John Doe");

        taxpayerService.send(taxpayerDTO);

    }

}
