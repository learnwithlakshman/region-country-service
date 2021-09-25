package com.careerit.rcs.service;

import java.util.List;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.dto.CountryRegionDto;
import com.careerit.rcs.dto.CountryStateWrapperDto;

public interface CountryDataService {

	public List<CountryRegionDto> getCountryRegionDetails();

	public CountryStateWrapperDto getCountryStateDetails();

	public List<Country> addCountryDetails();
}
