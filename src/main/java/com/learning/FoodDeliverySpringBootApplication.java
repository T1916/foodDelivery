package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FoodDeliverySpringBootApplication 
{
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(FoodDeliverySpringBootApplication.class, args);
	}

}
