package com.learning.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
import com.learning.dto.FoodType;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>
{
	Boolean existsByFoodName(String foodName);
	
	Optional<Food> findById(Integer id);
	//Boolean existsByFoodType(String foodType);
	
	//Optional<List<Food>> findByFoodType(String foodType);
	// the above retrieves data based on the foodtype

}
