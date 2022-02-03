package com.zee.zee5app.dto;

import java.math.BigInteger;
import java.util.Objects;

import javax.naming.InvalidNameException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "serName")}, name = "series")
public class Series implements Comparable<Series>
{
	public Series()
	{
		
	}
//	public Series(Integer Id, Integer Year, String cast, Integer AgeRestriction, String Genre, Integer Length, String Trailer, String Lang, Integer serEpisodes, String Name,  String AudioAvailable,String Synopsis, Integer Seasons, String regid) throws InvalidIdLengthException, InvalidNameException
//	{
//		
//		super();
//		this.setSerId(Id);
//		this.setSerYear(Year);
//		this.setSerSeasons(Seasons);
//		this.setSerEpisodes(serEpisodes);
//		this.setSerName(Name);;
//		this.setSerGenre(Genre);
//		this.setSercast(cast);
//		this.setSerLang(Lang);
//		this.setSerLength(Length);
//		this.setSerTrailer(Trailer);
//		this.setSerAgeRestriction(AgeRestriction);
//		this.setSerAudioAvailable(AudioAvailable);
//		this.setSerSynopsis(Synopsis);
//		this.setRegid(regid);
//		
//			
//	}
	
	@Id
	private Integer serId;
	@NotNull
	private Integer serYear;
	@NotNull
	private Integer serSeasons;
	@Min(value = 1)
	@NotNull
	private Integer serEpisodes;
	@NotBlank	
	private String serName;
	@NotBlank
	private String serGenre;
	@NotBlank
	private String sercast; // separated by comma
	@NotBlank
	private String serLang;
	@NotBlank
	private String serTrailer;
	@Max(value = 70)
	@NotNull
	private Integer serAgeRestriction;
	@NotBlank
	private String regid;
	
	
	@Override
	public int compareTo(Series o) 
	{
		// TODO Auto-generated method stub
		return o.serId.compareTo(this.serId);
	}
	
}
