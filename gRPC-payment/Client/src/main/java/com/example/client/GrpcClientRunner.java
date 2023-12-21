package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class GrpcClientRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger
            (GrpcClient.class);
    @Autowired
    GrpcClient client;
    @Override
    public void run(String... args) {
        client.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                client.shutdown();
            } catch (InterruptedException e) {
                System.out.println("error: " + e.getMessage());
            }
        }));
    }
}