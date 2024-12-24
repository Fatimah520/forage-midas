package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Kafka Listener Annotation  to listen to messages, read the name of the configuration
    @KafkaListener(topics = "my-topic", groupId = "midas-core-group")

    // listen method to receive transactions
    public void listen(String message) {
        try{
            Transaction transaction = objectMapper.readValue(message, Transaction.class);
            System.out.println("Received transaction: " + transaction);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
