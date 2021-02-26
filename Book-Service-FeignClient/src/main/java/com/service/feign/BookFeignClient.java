package com.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BOOK-SERVICE")
public interface BookFeignClient {

	@GetMapping(path="/book/{id}")
	Book getBook(@PathVariable("id") String id);
}
