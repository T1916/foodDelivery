package com.learning.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.FoodRepository;
import com.learning.service.FoodService;


@Service
public class FoodServiceImpl implements FoodService 
{
	@Autowired
	private FoodRepository foodRepo;

	@Override
	public Food addFood(Food food) throws AlreadyExistsException 
	{
		// TODO Auto-generated method stub
		boolean status = foodRepo.existsByFoodName(food.getFoodName());
		if(status)
		{
			throw new AlreadyExistsException("Record already exist");
		}
		Food food2 = foodRepo.save(food);
		if(food2!=null)
		{
			return food2;
		}
		else
			return null;
	}

	@Override
	public Food getFoodById(Integer id) throws IdNotFoundException 
	{
		// TODO Auto-generated method stub
		Optional<Food> optional = foodRepo.findById(id);
		if(optional.isEmpty())
		{
			throw new IdNotFoundException("Sorry Food Not Found");
		}
		else
		{
			return optional.get();
		}
	}

	@Override
	public Food updateFood(Integer id, String name,  Food food) throws IdNotFoundException, NameNotFoundException 
	{
//		// TODO Auto-generated method stub
//		boolean status = foodRepo.existsByFoodName(name);
//		if(!status)
//		{
//			throw new NameNotFoundException("Sorry, food not found");
//		}
//		else
//		{
//			Food optional = this.getFoodById(id);
//			foodRepo.deleteById(optional.getId());
//			optional = food;
//			foodRepo.save(optional);
//			return food;
//		}
		
		return null;
	}

	@Override
	public Optional<List<Food>> getAllFoodItems() 
	{
		// TODO Auto-generated method stub
		return Optional.ofNullable(foodRepo.findAll());
	}

	@Override
	public Optional<List<Food>> getFoodByFoodType(String foodType) throws NameNotFoundException 
	{
//		// TODO Auto-generated method stub
//		Optional<List<Food>> optional = foodRepo.findByFoodType(foodType);
//		if(optional.isEmpty())
//		{
//			throw new NameNotFoundException("Sorry Food Not Found");
//		}
//		else
//		{
//			return optional;
//		}
		
		return null;
		
	}

	@Override
	public String deleteFoodById(Integer id) throws IdNotFoundException 
	{
		// TODO Auto-generated method stub
		Food optional = this.getFoodById(id);
		if(optional == null)
		{
			throw new IdNotFoundException("Sorry food not found");
		}
		else
			foodRepo.deleteById(id);
		return "Food item Deleted";
	}

}
