package com.switchfully.parkshark;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.switchfully.parkshark", "com.switchsecure", "com.switchfully.homemadesecurity"})
public class ParksharkApplication{

    public static void main(String[] args) {
        SpringApplication.run(ParksharkApplication.class, args);

    }
}
