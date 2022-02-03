package com.zee.zee5app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.exception.NameNotFoundException;

public interface UserService 
{
	public String addUser(Register register);
	public String updateUser(String id, Register register);
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidNameException, InvalidEmailException, InvalidPasswordException, InvalidIdLengthException;
	public Register[] getAllUsers() throws InvalidNameException, InvalidEmailException, InvalidIdLengthException, InvalidPasswordException;
	public String deleteUserById(String id) throws IdNotFoundException, SQLException;
	public Optional<List<Register>> getAllUserDetails() throws InvalidNameException, InvalidEmailException, InvalidIdLengthException, InvalidPasswordException, SQLException;
	public Optional<Register> getUserByName(String name) throws NameNotFoundException;

}
