package com.careerit.rcs.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.careerit.rcs.domain.Country;

@SpringBootTest
public class CountryDataServiceTest {
		
		@Autowired
		private CountryDataService cdService;
	
		@Test
		public void countryWithRegionTest() {
			
//			List<CountryRegionDto> list = cdService.getCountryRegionDetails();
//		
//			list.stream().forEach(System.out::println);
//			
//			CountryStateWrapperDto obj = cdService.getCountryStateDetails();
//			
//			obj.getCountries().stream().forEach(System.out::println);
			List<Country> list = cdService.addCountryDetails();
			list.stream().forEach(e->{
				System.out.println(e.getName());
				System.out.println(e.getStates());
			});
		}
}
