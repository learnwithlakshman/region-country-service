package com.careerit.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.repo.CountryDao;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryDao countryDao;
	@Override
	public List<String> getRegionNames() {
		return countryDao.findRegionNames();
	}

	@Override
	public List<Country> getCountryNameByRegion(String region) {
		return countryDao.findCountryNameByRegion(region);
	}

	@Override
	public List<String> getCountryNames() {
		return countryDao.findCountryNames();
	}

	@Override
	public List<String> getStateNames(String country) {
		return countryDao.findStateNames(country);
	}

}
