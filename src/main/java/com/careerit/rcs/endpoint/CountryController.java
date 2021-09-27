package com.careerit.rcs.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.dto.RegionNamesDto;
import com.careerit.rcs.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/regionnames")
	public RegionNamesDto getRegionNames() {
		return countryService.getRegionNames();
	}

	@GetMapping("/countryname")
	public List<String> getCountryNames() {
		return countryService.getCountryNames();
	}

	@GetMapping("/region/{regionname}")
	public List<Country> getCountryRegion(@PathVariable("regionname") String name) {
		return countryService.getCountryNameByRegion(name);
	}

	@GetMapping("/state/{countryname}")
	public List<String> getStateNames(@PathVariable("countryname") String name) {
		return countryService.getStateNames(name);
	}

}
