package com.java.brains;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<User> user = userRepository.findByUsername(username);
		System.out.println("User is " + user.get());
		if(user.isPresent())
			return new MyUserDetails(user.get());
		else
		{
			System.out.println("No user");
			throw new UsernameNotFoundException("USername does not exist.");
		}
	}
	

	public User createUser(User user)
	{
		System.out.println("User is " + user);
		return userRepository.save(user);
	}

}
