package com.careerit.rcs.dto;

import java.util.List;

import lombok.Data;

@Data
public class CountryStateDto {

		private String country;
		private List<String> states;
}
