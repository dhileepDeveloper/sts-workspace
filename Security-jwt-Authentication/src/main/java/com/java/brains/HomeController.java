package com.java.brains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/hello")
	public String welcome()
	{
		return "Hello welcome to JWT";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest authenticateRequest)
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		UserDetails userDetails  = null;
		if(authentication != null)
		{
			userDetails = userDetailsService.loadUserByUsername(authentication.getName());
			
		}
		else
		{
			throw new BadCredentialsException("Please enter the correct username and password");
		}
		
		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println("jwtToken is " + jwtToken);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken);
		
		return ResponseEntity.ok(authenticationResponse);
		
	}
}
