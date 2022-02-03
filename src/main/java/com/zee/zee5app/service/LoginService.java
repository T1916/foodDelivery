package com.zee.zee5app.service;

import java.lang.StackWalker.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.exception.IdNotFoundException;

public interface LoginService 
{
	public String addCredentials(Login login) throws SQLException;
	public Optional<Login> getLoginById(String id);
	public Optional<List<Login>> getAllLogins();
	public String deleteCredentials(String id) throws IdNotFoundException;
	public String changePassword(String userName,String password);
	public String changeRole(String username, ROLE role) throws SQLException;
}
