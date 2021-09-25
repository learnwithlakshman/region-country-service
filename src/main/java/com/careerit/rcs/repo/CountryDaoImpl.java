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

@Repository
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<String> findRegionNames() {

		AggregationOperation group = Aggregation.group("region").addToSet("region").as("regionName");
		AggregationOperation project = Aggregation.project().andExclude("_id").andInclude("regionName");

		Aggregation aggregation = Aggregation.newAggregation(group, project);
		AggregationResults<String> res = mongoTemplate.aggregate(aggregation, "country", String.class);
		List<String> list = res.getMappedResults();
		return list;
	}

	@Override
	public List<Country> findCountryNameByRegion(String region) {
		List<Country> list = countryRepo.findByRegion(region);
		return list;
	}

	@Override
	public List<String> findCountryNames() {
		AggregationOperation group = Aggregation.group("name").addToSet("name").as("countryName");
		AggregationOperation project = Aggregation.project().andExclude("_id").andInclude("countryName");

		Aggregation aggregation = Aggregation.newAggregation(group, project);
		AggregationResults<String> res = mongoTemplate.aggregate(aggregation, "country", String.class);
		List<String> list = res.getMappedResults();
		return list;
	}

	@Override
	public List<String> findStateNames(String name) {
		Country country = countryRepo.findByName(name);
		if (country != null) {
			return country.getStates();
		}
		return Collections.emptyList();
	}
}
