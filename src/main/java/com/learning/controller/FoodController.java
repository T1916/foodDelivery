package com.learning.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.learning.dto.TypeFood;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.addFoodRequest;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.FoodRepository;
import com.learning.repo.FoodTypeRepository;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/api/auth/food")
public class FoodController 
{
	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	FoodTypeRepository foodTypeRepository;
	
	@PostMapping("") // working
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addFood(@Valid @RequestBody addFoodRequest addFoodRequest)
	{
//		Food result = foodService.addFood(food);
//		System.out.println(result);
//		return ResponseEntity.status(201).body(result);
		
		if(foodRepository.existsByFoodName(addFoodRequest.getFoodName()))
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Food item already exists"));
		}
		
		Food food = new Food(addFoodRequest.getFoodName(),
				addFoodRequest.getFoodCost(),
				addFoodRequest.getDescription(),
				addFoodRequest.getFoodPic());
		
		Set<String> strTypes = addFoodRequest.getFoodType();
		
		Set<TypeFood> types = new HashSet<>();
		
		strTypes.forEach(e->{
			switch(e)
			{
			case "indian":
				TypeFood typeIndian = foodTypeRepository.findByFoodType(FoodType.Indian)
										.orElseThrow(
										()->new RuntimeException("Error: Food Type not found")
										);
				types.add(typeIndian);
				break;
			
			case "chinese":
				TypeFood typeChinese = foodTypeRepository.findByFoodType(FoodType.Chinese)
										.orElseThrow(
										()->new RuntimeException("Error: Food Type not found")
										);
				types.add(typeChinese);
				break;
			
			case "mexican":
				TypeFood typeMexican = foodTypeRepository.findByFoodType(FoodType.Mexican)
										.orElseThrow(
										()->new RuntimeException("Error: Food Type not found")
										);
				types.add(typeMexican);
				break;
			
			default:
				TypeFood type = foodTypeRepository.findByFoodType(FoodType.Indian)
										.orElseThrow(
										()->new RuntimeException("Error: Food Type not found")
										);
				types.add(type);
				break;
			}
		});
		
		food.setFoodTypes(types);
		foodRepository.save(food);
		
		return ResponseEntity.status(201).body(new MessageResponse("food item added successfully"));
	}
	
	@GetMapping("/{id}") // working by commenting getfoodbyfoodtype
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Integer id) throws IdNotFoundException
	{
		Food result = foodService.getFoodById(id);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateFood(@PathVariable("id") Integer id, @RequestBody Food food) throws NameNotFoundException, IdNotFoundException
	{
		Food result = foodService.updateFood(id, food.getFoodName(), food);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("") // working 
	@PreAuthorize("hasRole('ADMIN')")
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
	
//	@GetMapping("/{foodType}")
//	public ResponseEntity<?> getFoodByFoodType(@PathVariable("foodType") String foodType) throws NameNotFoundException
//	{
//		Optional<List<Food>> result = foodService.getFoodByFoodType(foodType);
//		System.out.println(result);
//		return ResponseEntity.ok(result);
//	}
	
	@DeleteMapping("/{foodId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteFood(@PathVariable("foodId") Integer id) throws IdNotFoundException 
	{
		String result = foodService.deleteFoodById(id);
		return ResponseEntity.ok(result);
	}
}
