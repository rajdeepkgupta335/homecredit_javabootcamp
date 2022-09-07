package com.labs.spring.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("electric")
//@Primary
public class ElectricEngine extends Engine {

    @Value("Electricity")
    String type;

    @Value("1500")
    int capacity;

    @Value("2")
    int cylinder;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCylinder() {
        return cylinder;
    }

    @Override
    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }
}
