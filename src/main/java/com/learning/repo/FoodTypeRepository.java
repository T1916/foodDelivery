package com.learning.repo;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.dto.FoodType;
import com.learning.dto.TypeFood;

public interface FoodTypeRepository extends JpaRepository<TypeFood, Integer> 
{
	Optional<TypeFood> findByFoodType(FoodType foodType);

}
