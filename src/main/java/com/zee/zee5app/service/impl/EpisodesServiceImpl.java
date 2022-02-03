package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.EpisodesRepository;
//import com.zee.zee5app.repository.SeriesRepository2;
//import com.zee.zee5app.repository.impl.EpisodesRepositoryImpl;
//import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.EpisodesService;
import com.zee.zee5app.service.SeriesService2;


@Service
public class EpisodesServiceImpl implements EpisodesService 
{
	
	@Autowired
	private EpisodesRepository episodesRepo;
	
	@Override
	public String addEpisodes(Episodes episodes) 
	{
		//return this.episodesRepo.addEpisodes(episodes);
		Episodes episode2 = episodesRepo.save(episodes);
		if(episode2!=null)
		{
			return "success";
		}
		else
			return "fail";
	}

	@Override
	public Optional<Episodes> getEpisodesById(Integer id)
			throws InvalidNameException, InvalidIdLengthException, IdNotFoundException {
		// TODO Auto-generated method stub
		return episodesRepo.findById(id);
	}

	@Override
	public Optional<List<Episodes>> getEpisodes() throws InvalidNameException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(episodesRepo.findAll());
	}

	@Override
	public String deleteEpisodeById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Episodes> optional = this.getEpisodesById(id);
			if(optional.isEmpty())
			{
				throw new IdNotFoundException("record not found :(");
			}
			else
				episodesRepo.deleteById(id);
			
			return "success";
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}

	}

	
}
