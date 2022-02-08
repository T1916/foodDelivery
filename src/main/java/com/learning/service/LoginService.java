package com.learning.service;

import java.lang.StackWalker.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.learning.dto.Login;
import com.learning.exception.IdNotFoundException;

public interface LoginService 
{
	public String addCredentials(Login login);
	public Login updateCredentials(String email, Login login);
	public String getCredentialsByEmail(Login login);
	
}
