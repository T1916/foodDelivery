package com.learning.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import com.learning.dto.Food;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface FoodService 
{
	public Food addFood(Food food) throws AlreadyExistsException;
	public Food getFoodById(Integer id) throws IdNotFoundException;
	public Food updateFood(Integer id, String name, Food food) throws IdNotFoundException, NameNotFoundException;
	public Optional<List<Food>> getAllFoodItems();
	public Optional<List<Food>> getFoodByFoodType(String foodType) throws NameNotFoundException;
	public String deleteFoodById(Integer id) throws IdNotFoundException;

}
