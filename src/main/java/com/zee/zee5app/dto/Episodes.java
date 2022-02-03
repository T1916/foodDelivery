package com.zee.zee5app.dto;

import javax.naming.InvalidNameException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name= "episodes", uniqueConstraints = {@UniqueConstraint(columnNames = "episodename")})
public class Episodes 
{
	public Episodes() 
	{
		// TODO Auto-generated constructor stub
	}
	
//	public  Episodes(Integer epiId, Integer seriesId, String episodename, Integer epilength, String location, String trailer, String regId) throws InvalidIdLengthException, InvalidNameException 
//	{
//		super();
//		this.setEpiId(epiId);
//		this.setSeriesId(seriesId);
//		this.setEpisodename(episodename);
//		this.setEpilength(epilength);
//		this.setLocation(location);
//		this.setTrailer(trailer);
//		this.setRegId(regId);
//	}
	
	@Id
	private Integer epiId;
	@NotNull
	private Integer seriesId;
	@Size(max = 50)
	@NotBlank
	private String episodename;
	@NotNull
	private Integer epilength;
	@NotBlank
	private String location;
	@NotBlank
	private String trailer;
	@NotBlank
	private String regId; 
	
}
