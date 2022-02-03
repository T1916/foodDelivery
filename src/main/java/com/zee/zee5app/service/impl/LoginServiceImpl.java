package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.LoginRepository;
//import com.zee.zee5app.repository.impl.LoginRepositoryImpl;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.UserService;


@Service
public class LoginServiceImpl implements LoginService 
{
	
	@Autowired
	private LoginRepository loginRepo;

	@Override
	public String addCredentials(Login login)
	{
		// TODO Auto-generated method stub
		//return loginRepo.addCredentials(login);
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
	public String deleteCredentials(String id) throws IdNotFoundException 
	{
		// TODO Auto-generated method stub
		try {
			Optional<Login> optional = this.getLoginById(id);
			if(optional.isEmpty())
			{
				throw new IdNotFoundException("record not found :(");
			}
			else
				loginRepo.deleteById(id);
			
			return "success";
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}

	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeRole(String username, ROLE role) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Login> getLoginById(String id) 
	{
		// TODO Auto-generated method stub
		return loginRepo.findById(id);
	}

	@Override
	public Optional<List<Login>> getAllLogins() 
	{
		// TODO Auto-generated method stub
		return Optional.ofNullable(loginRepo.findAll());
	}

}
