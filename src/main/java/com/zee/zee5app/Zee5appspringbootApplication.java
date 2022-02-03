package com.zee.zee5app;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.EpisodesService;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.MovieService2;
import com.zee.zee5app.service.SeriesService2;
import com.zee.zee5app.service.SubscriptionService2;
import com.zee.zee5app.service.UserService;

@SpringBootApplication
public class Zee5appspringbootApplication 
{

	public static void main(String[] args) 
	{
		ConfigurableApplicationContext applicationContext=
				SpringApplication.run(Zee5appspringbootApplication.class, args);
		UserService userservice = applicationContext.getBean(UserService.class);
		LoginService loginservice = applicationContext.getBean(LoginService.class);
		MovieService2 movservice = applicationContext.getBean(MovieService2.class);
		SeriesService2 seriesservice = applicationContext.getBean(SeriesService2.class);
		EpisodesService episervice = applicationContext.getBean(EpisodesService.class);
		SubscriptionService2 subservice = applicationContext.getBean(SubscriptionService2.class);
		
		
	// ---------- REGISTER ---------- //	
		//UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		Register register;
		register = new Register("0000036", "vwxyzabcdef", "ghi", "tavishisingh11@gmail.com", "password2222");
		register.setContactNumber("9824293774");
		
		System.out.println("Register Object: " + register);
		//Register result = userRepository.save(register);
		System.out.println(userservice.addUser(register));
		try {
			System.out.println(userservice.getUserById("0000035"));
			System.out.println(userservice.getAllUserDetails());
			System.out.println(userservice.deleteUserById("0000035"));
			System.out.println(userservice.getAllUserDetails());
		} catch (InvalidNameException | IdNotFoundException | InvalidEmailException | InvalidPasswordException
				| InvalidIdLengthException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	// ----------- LOGIN -------------//
		Login login;
		login = new Login("User1", "password1", "user10001", ROLE.ROLE_USER);
		System.out.println("Login Object: " + login);
		Login login2 = new Login("User2", "password2", "user20002", ROLE.ROLE_USER);
		System.out.println("Login Object: " + login2);
		try {
			System.out.println(loginservice.addCredentials(login));
			System.out.println(loginservice.addCredentials(login2));
			System.out.println(loginservice.getLoginById("user20002"));
			System.out.println(loginservice.getAllLogins());
			System.out.println(loginservice.deleteCredentials("user20002"));
			System.out.println(loginservice.getAllLogins());
		} catch (SQLException | IdNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
	// ----------- MOVIE -------------//
		Movie movie;
		movie = new Movie(10001, 2019, "movie1", "drama", "hindi", "122min", "{trailer}", 13, "abc, def, ghi", "0000035");
		System.out.println("Movie Object: " + movie);
		System.out.println(movservice.addMovie(movie));
		movie = new Movie(10002, 2017, "movie2", "comedy", "hindi", "140min", "{trailer}", 13, "abc, def, ghi", "0000036");
		System.out.println("Movie Object: " + movie);
		System.out.println(movservice.addMovie(movie));
		try {
			System.out.println(movservice.getMovieById(10001));
			System.out.println(movservice.getMovies());
			System.out.println(movservice.deleteMovieById(10001));
			System.out.println(movservice.getMovies());
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
	// ----------- SERIES ------------ //
		Series series;
		series = new Series(110010, 2020, 2, 20, "series1", "mystry", "aplha, beta, gamma", "hindi", "{trailer}", 16, "0000036");
		System.out.println("Series Object: " + series);
		System.out.println(seriesservice.addSeries(series));
		series = new Series(110020, 2021, 1, 16, "series2", "rom-com", "aplha, beta, gamma", "hindi", "{trailer}", 13, "0000036");
		System.out.println("Series Object: " + series);
		System.out.println(seriesservice.addSeries(series));
		
		try {
			System.out.println(seriesservice.getSeriesById(110020));
			System.out.println(seriesservice.getSeries());
			System.out.println(seriesservice.deleteSeriesById(110010));
			System.out.println(seriesservice.getSeries());
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	// ------------ EPISODES ------------- //
		Episodes episode;
		episode = new Episodes(10101, 110020, "episode1", 28, "{location}", "{trailer}", "0000036");
		System.out.println("Episode object: " + episode);
		System.out.println(episervice.addEpisodes(episode));
		episode = new Episodes(10102, 110020, "episode2", 32, "{location}", "{trailer}", "0000036");
		System.out.println("Episode object: " + episode);
		System.out.println(episervice.addEpisodes(episode));
		
		try {
			System.out.println(episervice.getEpisodesById(10101));
			System.out.println(episervice.getEpisodes());
			System.out.println(episervice.deleteEpisodeById(10101));
			System.out.println(episervice.getEpisodes());
		} catch (InvalidNameException | InvalidIdLengthException | IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	// ----------- SUBSCRIPTIONS -------------- //	
		Subscriptions sub;
		sub = new Subscriptions(111111, 499, "monthly", "2021-10-15", "active", "India", "netbanking", "yes", "2022-02-15", "0000036");
		System.out.println("Subscriber object: " + series);
		System.out.println(subservice.addSub(sub));
		sub = new Subscriptions(222222, 499, "annually", "2021-01-26", "inactive", "India", "netbanking", "no", "2021-01-26", "0000036");
		System.out.println("Subscriber object: " + series);
		System.out.println(subservice.addSub(sub));
		
		try {
			System.out.println(subservice.getSubById(111111));
			System.out.println(subservice.getSubs());
			System.out.println(subservice.deleteSubById(111111));
			System.out.println(subservice.getSubs());
		} catch (IdNotFoundException | InvalidIdLengthException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println(dataSource!=null);		
		applicationContext.close();
	
	}

}
