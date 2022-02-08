package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(FoodDeliverySpringBootApplication.class, args);

	}

}
