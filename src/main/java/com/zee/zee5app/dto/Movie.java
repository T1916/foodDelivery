package com.zee.zee5app.dto;

import java.util.Objects;

import javax.naming.InvalidNameException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
//@EqualsAndHashCode
@Entity
@AllArgsConstructor
@Table(name = "movies", uniqueConstraints = {@UniqueConstraint(columnNames = "movName")})
public class Movie implements Comparable<Movie>
{
	public Movie()
	{
		
	}
//	public Movie(Integer id, Integer year, String name, String genre, String lang, String length, String trailer, Integer ageRestriction, String audioAvailable, String synopsis, String cast, String regid) throws InvalidIdLengthException, InvalidNameException
//	{
//		super();
//		
//		this.setMovId(id);	
//		this.setMovYear(year);
//		this.setMovName(name);
//		this.setMovGenre(genre);
//		this.setMovLang(lang);
//		this.setMovLength(length);
//		this.setMovTrailer(trailer);
//		this.setMovAgeRestriction(ageRestriction);
//		this.setMovAudioAvailable(audioAvailable);
//		this.setMovSynopsis(synopsis);
//		this.setMovCast(cast);
//		this.setRegid(regid);
//	}
	
	//@Setter(value = AccessLevel.NONE)
	@Id
	private Integer movId;
	@NotNull
	private Integer movYear;
	
	//@Setter(value = AccessLevel.NONE)
	@NotBlank
	private String movName;
	@NotBlank
	private String movGenre;
	@NotBlank
	private String movLang;
	@NotBlank
	private String movLength;
	@NotBlank
	private String movTrailer;
	@Max(value = 70)
	@NotNull
	private Integer movAgeRestriction;
	@NotBlank
	private String movCast;
	@NotBlank
	private String regid;
	
	
	@Override
	public int compareTo(Movie o) 
	{
		// TODO Auto-generated method stub
		return o.movId.compareTo(this.movId);
	}
	
}
