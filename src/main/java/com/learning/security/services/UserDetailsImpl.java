package com.learning.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.dto.Register;
import com.learning.security.services.UserDetailsImpl;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails 
{

	private Long id;
	
	private String name;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities; // for roles
	
	private UserDetailsImpl(Long id, String name, String email, String password,
		      Collection<? extends GrantedAuthority> authorities) 
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Register user) 
	{
		// it should build the userdetailsimpl object
		List<GrantedAuthority> authorities = user.getRoles()
							.stream()// it will transform your set to stream 
							.map(role->new SimpleGrantedAuthority(role.getRoleName().toString()))
							.collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getId(), 
								   user.getName(), 
								   user.getEmail(), 
								   user.getPassword(), 
								   authorities);
		
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object o) 
	{
		// TODO Auto-generated method stub
		 if (this == o)
		      return true;
		    if (o == null || getClass() != o.getClass())
		      return false;
		    UserDetailsImpl user = (UserDetailsImpl) o;
		    return Objects.equals(id, user.id);
	}

}
