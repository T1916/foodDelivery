package com.learning.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.learning.utils.PasswordUtils;

@Configuration // it is used to mark on config class/classes
// here we will hold all commonly required objects for our application
//@ComponentScan("com.zee.zee5app")
//@PropertySource("classpath:application.properties") // it is responsible to read the property fily

public class Config
{
	@Autowired // will bring the already created objects based on the name(ref name) / types
	Environment environment; // this impl object is prepared by spring
	// do we need to inject that already created object? yes ===> DI ===> IoC
	// config : db related, reading prop file. commonly reqd beans(passwordEncoder)
	
//	@Bean(name = "ds") // for providing the singleton object
//	@Scope("singleton") // ===> if you will call getBean method N no times then you will get N objects.
//	// to get multiple object we should use prototype scope
//	// if we will not specify the bean name then it will take / consider the method name as bean name 
//	public DataSource dataSource() 
//	{
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
//		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
//		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
//		basicDataSource.setDefaultAutoCommit(false);
//		
//		return basicDataSource;
//		
//	}
//	
//	
//	@Bean// are we going to create the object ? yes
//	// this object can we initialize as per the req?
//	// can we customize it as when req? in case of bean
//	public PasswordUtils passowrdUtils()
//	{
//		return new PasswordUtils();
//	}
}
