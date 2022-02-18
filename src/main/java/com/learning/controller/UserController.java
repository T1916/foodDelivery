package com.learning.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.repo.RoleRepository;
import com.learning.repo.UserRepository;
import com.learning.security.jwt.JwtUtils;
import com.learning.service.LoginService;
import com.learning.service.UserService;
import com.learning.dto.EROLE;
import com.learning.dto.Role;
import com.learning.dto.Register;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.MessageResponse;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.security.services.UserDetailsImpl;

@RestController // version 4 @ResponseBody and @Controller
// REST API : RESPONSE wherever we have to share the response that method
// must be marked with @ResponseBody
// 1000methods ----> @Responsebody ------> 1000times
//
@RequestMapping("/api/auth")
public class UserController 
{
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
	{
		Authentication authentication = authenticationManager
								.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail()
										, loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
		
		
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
								userDetailsImpl.getId(), 
								userDetailsImpl.getUsername(), 
								userDetailsImpl.getEmail(), 
								roles));
		
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest)
	{
		if(userRepository.existsByEmail(signupRequest.getEmail()))
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already taken!"));
		}
		
		// user's account 
		
		Register user = new Register(signupRequest.getEmail(), 
				signupRequest.getName(), 
				passwordEncoder.encode(signupRequest.getPassword()),
				signupRequest.getAddress());
		
		// getting the role details
		
		Set<String> strRoles = signupRequest.getRole();
		
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null)
		{
			Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(
							()->new RuntimeException("Error: role not found")
							);
			roles.add(userRole);
			
		}
		
		else
		{
			strRoles.forEach(e->{
				switch (e) 
				{
				case "admin":
					Role roleAdmin = roleRepository.findByRoleName(EROLE.ROLE_ADMIN)
								.orElseThrow(
										()->new RuntimeException("Error: role not found")
										);
					roles.add(roleAdmin);
					break;					

				default:
					Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
								.orElseThrow(
										()->new RuntimeException("Error: role not found")
										);
					roles.add(userRole);
					break;
					
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return ResponseEntity.status(201).body(new MessageResponse("user created successfully"));
	}	

//	@GetMapping("/authenticate/{id}")
//	public ResponseEntity<?> getCredentialsById(@Valid @PathVariable("id") Long id ) throws IdNotFoundException
//	{
//		Register result = userService.getUserById(id);
//		System.out.println(result);
//		return ResponseEntity.status(201).body(result);
//	}
	
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
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws IdNotFoundException
	{
			Register register = userService.getUserById(id);
			return ResponseEntity.ok(register);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws IdNotFoundException 
	{
		String result = userService.deleteUserById(id);
		return ResponseEntity.ok(result);
	}
	
//	@PutMapping("/users/{id}")
//	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody Register register) throws IdNotFoundException, InvalidEmailException
//	{
//		Register result = userService.updateUser(id, register.getEmail(), register);
//		return ResponseEntity.ok(result);
//	}
//	
	
	
	
}
