package com.careerit.rcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Region-Country-Service",description = "Api's for region,country and state information"))
public class RegionCountryServiceApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(RegionCountryServiceApplication.class, args);
	}



}
