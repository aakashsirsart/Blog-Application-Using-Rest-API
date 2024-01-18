package com.as.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.as.blog.Model.User;
import com.as.blog.exceptions.ResourceNorFoundException;
import com.as.blog.repository.UserRepo;

@Service
public class CustomUserDetailServices implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNorFoundException("User", "email" + username, 0));
		return user;
	}	

}
