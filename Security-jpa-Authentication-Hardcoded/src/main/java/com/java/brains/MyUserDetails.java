package com.java.brains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private String username;
	    
	public MyUserDetails(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List list = new ArrayList<GrantedAuthority>();
		if(username.equals("egurkha"))
		{
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		else if(username.equals("Dhileep"))
		{
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return list;
	}

	@Override
	public String getPassword() {
		return "abc123ABC";
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return true;
	}

}
