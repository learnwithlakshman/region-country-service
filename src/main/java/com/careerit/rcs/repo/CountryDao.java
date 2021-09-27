package com.careerit.rcs.repo;

import java.util.List;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.dto.RegionNamesDto;

public interface CountryDao {

		public RegionNamesDto findRegionNames();
		public List<Country> findCountryNameByRegion(String region);
		public List<String> findCountryNames();
		public List<String> findStateNames(String country);
}
