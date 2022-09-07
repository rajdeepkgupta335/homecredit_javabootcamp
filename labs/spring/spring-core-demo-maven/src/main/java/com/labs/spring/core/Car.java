package com.labs.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("swift")
@Lazy
@Scope("singleton")
public class Car {

    @Value("BMW")
    private  String maker;

    @Value("350D")
    private  String model;

    @Value("Navy Blue")
    private  String color;

//    @Autowired
//    @Qualifier("fuel")
    private  Engine engine;


    public Car(){
        System.out.println("Car Default Constructor Called...");

    }

    public Car(String maker, String model, String color) {
        this.maker = maker;
        this.model = model;
        this.color = color;
    }

    public Car(String maker, String model, String color, Engine engine) {
        this.maker = maker;
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}