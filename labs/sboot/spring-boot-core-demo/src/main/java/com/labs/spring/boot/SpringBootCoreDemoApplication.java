package com.labs.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCoreDemoApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext ctx;

	public static void main(String[] args) {
		System.out.println("Before Launching...");

		SpringApplication.run(SpringBootCoreDemoApplication.class, args);

		System.out.println("After Launching...");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(ctx);
		System.out.println(ctx.getBeanDefinitionCount());

		for(String beanName : ctx.getBeanDefinitionNames()){
			System.out.println(beanName);
		}
	}
}
