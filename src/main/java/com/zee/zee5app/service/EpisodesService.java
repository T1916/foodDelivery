package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface EpisodesService 
{
	public String addEpisodes (Episodes episodes);
	public Optional<Episodes> getEpisodesById(Integer id) throws InvalidNameException, InvalidIdLengthException, IdNotFoundException;
	public Optional<List<Episodes>> getEpisodes() throws InvalidNameException, InvalidIdLengthException;
	public String deleteEpisodeById(Integer id) throws IdNotFoundException;

}
