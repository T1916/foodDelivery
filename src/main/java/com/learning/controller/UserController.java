package com.learning.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.service.LoginService;
import com.learning.service.UserService;

@RestController // version 4 @ResponseBody and @Controller
// REST API : RESPONSE wherever we have to share the response that method
// must be marked with @ResponseBody
// 1000methods ----> @Responsebody ------> 1000times
//
@RequestMapping("")
public class UserController 
{
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginService;
	// adding user/register info into the table
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws AlreadyExistsException
	{
		Register result = userService.addUser(register);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> getCredentialsById(@Valid @RequestBody Login login) throws AlreadyExistsException
	{
		String result = loginService.getCredentialsByEmail(login);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() throws AlreadyExistsException
	{
		Optional<List<Register>> optional = userService.getAllUsers();
		
		if(optional.isEmpty())
		{
			Map<String, String> map = new HashMap<>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) throws IdNotFoundException
	{
			Register register = userService.getUserById(id);
			return ResponseEntity.ok(register);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody Register register) throws IdNotFoundException, InvalidEmailException
	{
		Register result = userService.updateUser(id, register.getEmail(), register);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) throws IdNotFoundException 
	{
		String result = userService.deleteUserById(id);
		return ResponseEntity.ok(result);
	}
	
	
}
