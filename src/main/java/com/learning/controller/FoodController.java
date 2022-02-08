package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController 
{
	@Autowired
	FoodService foodService;
	
	
	@PostMapping("")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food) throws AlreadyExistsException
	{
		Food result = foodService.addFood(food);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Integer id) throws IdNotFoundException
	{
		Food result = foodService.getFoodById(id);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateFood(@PathVariable("id") Integer id, @RequestBody Food food) throws NameNotFoundException, IdNotFoundException
	{
		Food result = foodService.updateFood(id, food.getFoodName(), food);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllFood() 
	{
		Optional<List<Food>> optional = foodService.getAllFoodItems();
		
		if(optional.isEmpty())
		{
			Map<String, String> map = new HashMap<>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional);
	}
	
	@GetMapping("/{foodType}")
	public ResponseEntity<?> getFoodByFoodType(@PathVariable("foodType") String foodType) throws NameNotFoundException
	{
		Optional<List<Food>> result = foodService.getFoodByFoodType(foodType);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{foodId}")
	public ResponseEntity<?> deleteFood(@PathVariable("foodId") Integer id) throws IdNotFoundException 
	{
		String result = foodService.deleteFoodById(id);
		return ResponseEntity.ok(result);
	}
}
