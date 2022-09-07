package com.labs.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CarApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);

        System.out.println(ctx);


        for(String beanName : ctx.getBeanDefinitionNames()){
            System.out.println(beanName);
        }

//
        Car swift = ctx.getBean("swift",Car.class);
        System.out.println(swift.getMaker()+" : " +swift.getModel()+" : " +swift.getColor()+" : " +swift.getEngine().getType());

//        Car audi = ctx.getBean("audi",Car.class);
//        System.out.println(audi.getMaker()+" : " +audi.getModel()+" : " +audi.getColor()+" : " +audi.getEngine().getType());
    }
}
