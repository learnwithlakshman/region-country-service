package com.careerit.rcs.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {
	

		@Id
		private String id;
	    private String name;
	    private String capital;
	    private String region;
	    private List<String> states;
	
}
