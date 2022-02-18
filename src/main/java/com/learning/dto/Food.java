package com.learning.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
@Entity
//@AllArgsConstructor
@Table(name = "foodType")
public class Food 
{
	public Food()
	{
		
	}

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(max=50)
	@NotBlank
	private String foodName;
	
	
	@Min(5)
	@Max(200)
	@NotNull
	private Integer foodCost;
	
	@Size(max=500)
	@NotBlank
	private String description;
	
	@Size(max=1000)
	@NotBlank
	private String foodPic;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable
	private Set<TypeFood> foodTypes = new HashSet<>();
	
	public Food(String foodName, Integer foodCost, String description, String foodPic)
	{
		this.foodName = foodName;
		this.foodCost = foodCost;
		this.description = description;
		this.foodPic = foodPic;
	}
}
