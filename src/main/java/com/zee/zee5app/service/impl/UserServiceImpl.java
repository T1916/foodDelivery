package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.UserRepository;
//import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

//@Component
@Service

public class UserServiceImpl implements UserService 
{
	// bring the userRepository object
	// using getInstance method
	// are we expecting that repo object we should get it from spring?
	
	@Autowired
	private UserRepository userRepository;
	// private static UserServiceImpl service = null;

	@Override
	public String addUser(Register register) 
	{
		// TODO Auto-generated method stub
		// return userRepository.addUser(register);
		Register register2 = userRepository.save(register);
		if(register2!=null)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}
	@Override
	public String updateUser(String id, Register register) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidNameException,
			InvalidEmailException, InvalidPasswordException, InvalidIdLengthException 
	{
		return userRepository.findById(id);
	}
	@Override
	public Register[] getAllUsers()
			throws InvalidNameException, InvalidEmailException, InvalidIdLengthException, InvalidPasswordException 
	{
		// TODO Auto-generated method stub
		List<Register> list = userRepository.findAll();
		Register[] array = new Register[list.size()];
		
		return list.toArray(array);
	}
	@Override
	public String deleteUserById(String id) throws IdNotFoundException, SQLException 
	{
		// TODO Auto-generated method stub
		// can we cross check it with findbyId() ==> record exists?
		
		try {
			Optional<Register> optional = this.getUserById(id);
			if(optional.isEmpty())
			{
				throw new IdNotFoundException("record not found :(");
			}
			else
				userRepository.deleteById(id);
			
			return "success";
		} catch (InvalidNameException | IdNotFoundException | InvalidEmailException | InvalidPasswordException
				| InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}

	}
	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidNameException, InvalidEmailException,
			InvalidIdLengthException, InvalidPasswordException, SQLException 
	{
		return Optional.ofNullable(userRepository.findAll());
	}
	@Override
	public Optional<Register> getUserByName(String name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
