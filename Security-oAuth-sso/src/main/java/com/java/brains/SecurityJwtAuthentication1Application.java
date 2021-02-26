package com.java.brains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


@EnableOAuth2Sso
@SpringBootApplication
public class SecurityJwtAuthentication1Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtAuthentication1Application.class, args);
	}

}
