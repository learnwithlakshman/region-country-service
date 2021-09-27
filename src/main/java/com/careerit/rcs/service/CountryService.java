package com.careerit.rcs.service;

import java.util.List;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.dto.RegionNamesDto;

public interface CountryService {

	public RegionNamesDto getRegionNames();
	public List<Country> getCountryNameByRegion(String region);
	public List<String> getCountryNames();
	public List<String> getStateNames(String country);
}
