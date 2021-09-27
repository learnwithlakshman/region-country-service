package com.careerit.rcs.repo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.dto.RegionNamesDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public RegionNamesDto findRegionNames() {

		AggregationOperation group = Aggregation.group("null").addToSet("region").as("regions");
		AggregationOperation project = Aggregation.project().andExclude("_id").andInclude("regions");

		Aggregation aggregation = Aggregation.newAggregation(group, project);
		AggregationResults<RegionNamesDto> res = mongoTemplate.aggregate(aggregation, "country", RegionNamesDto.class);
		RegionNamesDto regionDto = res.getUniqueMappedResult();
		log.info("Region count found in db :{}",regionDto.getRegions().size());
		return regionDto;
	}

	@Override
	public List<Country> findCountryNameByRegion(String region) {
		log.debug("Looking for country information of the region {}",region);
		List<Country> list = countryRepo.findByRegion(region);
		log.info("Total {} countries found for the region {}",list.size(),region );
		return list;
	}

	@Override
	public List<String> findCountryNames() {
		AggregationOperation group = Aggregation.group("name").addToSet("name").as("countryName");
		AggregationOperation project = Aggregation.project().andExclude("_id").andInclude("countryName");

		Aggregation aggregation = Aggregation.newAggregation(group, project);
		AggregationResults<String> res = mongoTemplate.aggregate(aggregation, "country", String.class);
		List<String> list = res.getMappedResults();
		log.info("Country names count found in db :{}",list.size());
		return list;
	}

	@Override
	public List<String> findStateNames(String name) {
		log.debug("Looking for state information of the country {}",name);
		Country country = countryRepo.findFirstByName(name);
		if (country != null) {
			List<String> list = country.getStates();
			log.info("The country with name {} is found and has total states {}",name,list.size());
			return country.getStates();
		}
		log.info("The country with name {} is not found",name);
		return Collections.emptyList();
	}
}
