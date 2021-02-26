package com.java.brains;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityJpaAuthenticationHardcodedApplicationTests {

	@Autowired
	private MyUserDetailsService userService;
	
	@Test
	void createUserTest() {
		System.out.println("Entered junit test");
		User user = new User();
		user.setUsername("Jack");
		user.setPassword("abc123ABC");
		String[] roles = {"ROLE_USER","ROLE_ADMIN"};
		user.setUserRoles(roles);
		System.out.println(userService.createUser(user));
	}
	
	@Test
	void createUserTest2() {
		System.out.println("Entered junit test");
		User user = new User();
		user.setUsername("Rose");
		user.setPassword("abc123ABC");
		String[] roles = {"ROLE_USER"};
		user.setUserRoles(roles);
		System.out.println(userService.createUser(user));
	}
	

}
