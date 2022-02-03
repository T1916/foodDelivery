package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
//import com.zee.zee5app.repository.SeriesRepository2;
//import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService2;


@Service
public class SeriesServiceImpl implements SeriesService2 
{
	
	
	@Autowired
	private SeriesRepository seriesRepo;
	
	

	@Override
	public String addSeries(Series series) 
	{
		//return this.seriesRepo.addSeries(series);
		Series series2 = seriesRepo.save(series);
		if(series2!=null)
		{
			return "success";
		}
		else
			return "fail";
	}


	@Override
	public Optional<Series> getSeriesById(Integer id)
			throws IdNotFoundException, InvalidNameException, InvalidIdLengthException 
	{
		// TODO Auto-generated method stub
		return seriesRepo.findById(id);
	}



	@Override
	public Optional<List<Series>> getSeries() throws InvalidNameException, InvalidIdLengthException 
	{
		// TODO Auto-generated method stub
		return Optional.ofNullable(seriesRepo.findAll());
	}



	@Override
	public String deleteSeriesById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Series> optional = this.getSeriesById(id);
			if(optional.isEmpty())
			{
				throw new IdNotFoundException("record not found :(");
			}
			else
				seriesRepo.deleteById(id);
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public Optional<Series> getSeriesByName(String name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Series updateSeries(Integer id, Integer year, String length) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
