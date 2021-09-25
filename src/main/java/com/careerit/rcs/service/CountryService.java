package com.careerit.rcs.service;

import java.util.List;

import com.careerit.rcs.domain.Country;

public interface CountryService {

	public List<String> getRegionNames();
	public List<Country> getCountryNameByRegion(String region);
	public List<String> getCountryNames();
	public List<String> getStateNames(String country);
}
