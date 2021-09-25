package com.careerit.rcs.repo;

import java.util.List;

import com.careerit.rcs.domain.Country;

public interface CountryDao {

		public List<String> findRegionNames();
		public List<Country> findCountryNameByRegion(String region);
		public List<String> findCountryNames();
		public List<String> findStateNames(String country);
}
