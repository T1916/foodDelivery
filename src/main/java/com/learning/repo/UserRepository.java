package com.learning.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Register;

@Repository
public interface UserRepository extends JpaRepository<Register, Integer> 
{
	Boolean existsByEmail(String email);
	// the above is a custom JPA method
}
