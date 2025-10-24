package com.example.smarthome.config;

import com.example.smarthome.domain.AirConditioner;
import com.example.smarthome.domain.Fan;
import com.example.smarthome.domain.Light;
import com.example.smarthome.repo.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(DeviceRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Light("Hallway Light"));
                repo.save(new Fan("Bedroom Fan"));
                repo.save(new AirConditioner("Living Room AC"));
            }
        };
    }
}
