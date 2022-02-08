package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(max=50)
	@NotBlank
	private String foodName;
	
	@NotNull
	private Integer foodCost;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private FoodType foodType;
	
	@Size(max=500)
	@NotBlank
	private String description;
	
	@Size(max=100)
	@NotBlank
	private String foodPic;
	
}
