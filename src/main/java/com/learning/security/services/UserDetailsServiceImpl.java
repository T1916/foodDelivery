package com.learning.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.dto.Register;
import com.learning.repo.UserRepository;
import com.learning.security.services.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService 
{
	@Autowired
	UserRepository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Register user = userRepository.findByEmail(email)
				.orElseThrow(()->new UsernameNotFoundException("user not found with the email" + email));
		return UserDetailsImpl.build(user);
	}

}
