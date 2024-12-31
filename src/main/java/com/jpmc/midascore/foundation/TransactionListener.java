package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {


    @PostConstruct
    public void init(){
        System.out.println("TransactionListener initialized");
    }
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "midas-transactions", groupId = "midas-core-group")
    public void listen(String message) {
        try{
            Transaction transaction = objectMapper.readValue(message, Transaction.class);
            System.out.println("Received transaction: " + transaction);
            System.out.println("Parsed transaction: " + transaction);
        } catch (Exception e){
            System.err.println("Error processing transaction: " + message);
            e.printStackTrace();
        }
    }


}