package com.learning.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidEmailException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.exception.InvalidPasswordException;
import com.learning.exception.NameNotFoundException;

public interface UserService 
{
	public Register addUser(Register register) throws AlreadyExistsException;
	public Register updateUser(Long id, String email, Register register) throws IdNotFoundException, InvalidEmailException;
	public Register getUserByEmail(String email);
	public Register getUserById(Long id) throws IdNotFoundException;
	public Optional<List<Register>> getAllUsers();
	public String deleteUserById(Long id) throws IdNotFoundException;
}
