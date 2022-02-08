package com.learning.service.impl;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.exception.InvalidPasswordException;
import com.learning.exception.NameNotFoundException;
import com.learning.repo.LoginRepository;
import com.learning.repo.UserRepository;
import com.learning.service.LoginService;
import com.learning.service.UserService;

//@Component
@Service

public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepo;
	@Autowired 
	private LoginRepository loginRepo;
	@Autowired
	private LoginService loginService;
	

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addUser(Register register) throws AlreadyExistsException 
	{
		// TODO Auto-generated method stub
		boolean status = userRepo.existsByEmail(register.getEmail());
		if(status)
		{
			throw new AlreadyExistsException("Result Already Exists");
		}
		Register register2 = userRepo.save(register);
		if(register2!=null)
		{
			Login login = new Login(register.getEmail(), register.getPassword());
			String result = loginService.addCredentials(login);
			System.out.println(login);
			if(result.equals("success"))
			{
				return register2;
			}
			else
				return null;
			
		}	
		else
		{
			return null;
		}
	}

	@Override
	public Register updateUser(Integer id, String email, Register register) throws IdNotFoundException, InvalidEmailException 
	{
		// TODO Auto-generated method stub
		//Register optional = this.getUserById(id);
		boolean status = userRepo.existsById(id);
		if(!status)
		{
			throw new IdNotFoundException("Sorry user with ID: " + id + " not found");
		}
		else
		{
			Register optional = this.getUserById(id);
			userRepo.deleteById(optional.getId());
			optional = register;
			userRepo.save(optional);
			return register;
		}
	}

	@Override
	public Register getUserByEmail(String email) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Register>> getAllUsers() 
	{
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepo.findAll());
	}

	@Override
	public String deleteUserById(Integer id) throws IdNotFoundException 
	{
		// TODO Auto-generated method stub
		Register optional = this.getUserById(id);
		if(optional == null)
		{
			throw new IdNotFoundException("Sorry user with ID: " + id + " not found");
		}
		else
			userRepo.deleteById(id);
		return "User deleted Successfully";
	}

	@Override
	public Register getUserById(Integer id) throws IdNotFoundException 
	{
		// TODO Auto-generated method stub
		Optional<Register> optional = userRepo.findById(id);
		if(optional.isEmpty())
		{
			throw new IdNotFoundException("Sorry user with ID: " + id + " not found");
		}
		else
		{
			return optional.get();
		}
	}
	
	

}
