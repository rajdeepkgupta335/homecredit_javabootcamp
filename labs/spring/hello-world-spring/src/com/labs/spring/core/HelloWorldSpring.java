package com.labs.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpring {
    public static void main(String[] args) {
//        System.out.println("Hello Spring");
//
//        Greetings greetings = new Greetings();
//
////        greetings.setMessage("Welcome to Spring Training");
//
//        System.out.println(greetings.getMessage());

        //STEP 1: Create IoC Container

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config.xml");
        System.out.println(ctx);

        Greetings greetings = ctx.getBean("greetings", Greetings.class);

        System.out.println(greetings.getMessage());

        Greetings greetings2 = ctx.getBean("greetings2", Greetings.class);

        System.out.println(greetings2.getMessage());

        System.out.println("No of beans: " + ctx.getBeanDefinitionCount());

        for(String beanName : ctx.getBeanDefinitionNames()){
            System.out.println(beanName);
        }

    }

}
