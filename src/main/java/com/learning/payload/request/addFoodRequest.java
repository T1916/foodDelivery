package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class addFoodRequest 
{
	@NotBlank
	@Size(min = 3, max = 100)
	private String foodName;
	
	@NotNull
	@Min(5)
	@Max(200)
	private Integer foodCost;
	
	private Set<String> foodType;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String description;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String foodPic;
}
