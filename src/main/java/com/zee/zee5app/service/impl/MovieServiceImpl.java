package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.MovieRepository;
//import com.zee.zee5app.repository.MovieRepository2;
//import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService2;

@Service
public class MovieServiceImpl implements MovieService2 
{
	@Autowired
	private MovieRepository movieRepo;
	
	
	@Override
	public String addMovie(Movie movie) 
	{
		//return this.movieRepo.addMovie(movie);
		Movie movie2 = movieRepo.save(movie);
		if(movie2!=null)
		{
			return "success";
		}
		else
			return "fail";
	}


	@Override
	public Movie updateMovie(Integer id, Integer year, String length) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Movie> getMovieById(Integer id)
			throws IdNotFoundException, InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return movieRepo.findById(id);
	}


	@Override
	public Optional<Movie> getMovieByName(String name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<List<Movie>> getMovies() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(movieRepo.findAll());
	}


	@Override
	public String deleteMovieById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Movie> optional = this.getMovieById(id);
			if(optional.isEmpty())
			{
				throw new IdNotFoundException("record not found :(");
			}
			else
				movieRepo.deleteById(id);
			
			return "success";
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	

}
