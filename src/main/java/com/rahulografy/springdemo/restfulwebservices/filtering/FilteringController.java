package com.rahulografy.springdemo.restfulwebservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/getUserFilteringStatic")
	public PojoFilteringStatic getUserFilteringStatic() {
		return new PojoFilteringStatic("1122", "Rahul S Deshpande", "India");
	}

	@GetMapping("/getUserFilteringDynamic")
	public MappingJacksonValue getUserFilteringDynamic() {
		PojoFilteringDynamic pojoFilteringDynamic = new PojoFilteringDynamic("1122", "Rahul S Deshpande", "India");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");

		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("PojoFilteringDynamic", filter);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(pojoFilteringDynamic);
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}
}