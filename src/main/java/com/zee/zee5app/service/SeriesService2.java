package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.NameNotFoundException;

public interface SeriesService2 
{
	public String addSeries (Series series);
	public Series updateSeries(Integer id, Integer year, String length);
	public Optional<Series> getSeriesById(Integer id) throws IdNotFoundException, InvalidNameException, InvalidIdLengthException;
	public Optional<List<Series>> getSeries() throws InvalidNameException, InvalidIdLengthException;
	public String deleteSeriesById(Integer id) throws IdNotFoundException;
	public Optional<Series> getSeriesByName(String name) throws NameNotFoundException;

}
