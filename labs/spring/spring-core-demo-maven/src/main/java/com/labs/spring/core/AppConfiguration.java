package com.labs.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan
@Configuration
public class AppConfiguration {

    @Bean
    public Car swift(){
        Car swift = new Car();
        swift.setEngine(diesel());
        return swift;
    }

    @Bean
    public Engine petrol(){
        return new Engine();
    }

    @Bean
    public Engine diesel(){
        Engine engine = new Engine();
        engine.setType("Diesel");
        return engine;
    }

    @Bean
    public Engine electric(){
        return new ElectricEngine();
    }
}
