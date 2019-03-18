package com.rahulografy.springdemo.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/getUserFiltered")
	public SomePojo getUserFiltered() {
		return new SomePojo("1122", "Rahul S Deshpande", "India");
	}
}