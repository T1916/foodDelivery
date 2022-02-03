package com.zee.zee5app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.NameNotFoundException;

public interface MovieService2 
{
	public String addMovie(Movie movie);
	public Movie updateMovie(Integer id, Integer year, String length);
	public Optional<Movie> getMovieById(Integer id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException;
	public Optional<Movie> getMovieByName(String name) throws NameNotFoundException;
	public Optional<List<Movie>> getMovies() throws InvalidNameException, InvalidIdLengthException;
	public String deleteMovieById(Integer id) throws IdNotFoundException;

}
