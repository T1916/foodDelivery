package com.learning.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.exception.InvalidPasswordException;
import com.learning.repo.LoginRepository;
import com.learning.service.LoginService;
import com.learning.service.UserService;


@Service
public class LoginServiceImpl implements LoginService 
{
	@Autowired
	private LoginRepository loginRepo;
	@Override
	public String addCredentials(Login login) 
	{
		// TODO Auto-generated method stub
		Login login2 = loginRepo.save(login);
		if(login2!=null)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}

	@Override
	public Login updateCredentials(String email, Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCredentialsByEmail(Login login) 
	{
		// TODO Auto-generated method stub
		if(loginRepo.existsByEmail(login.getEmail()))
		{
			return "success";
		}
		else
		{
			return "fail";
		}
		
	}

}
