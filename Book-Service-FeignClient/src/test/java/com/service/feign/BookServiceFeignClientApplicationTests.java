package com.service.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceFeignClientApplicationTests {

	@Autowired
	private BookFeignClient feignClient;
	
	@Test
	void getBook()
	{
		System.out.println("Started");
		System.out.println(feignClient.getBook("1"));
	}
}
