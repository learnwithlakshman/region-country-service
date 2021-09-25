package com.careerit.rcs.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.careerit.rcs.domain.Country;

public interface CountryRepo extends MongoRepository<Country,String> {

	List<Country> findByRegion(String region);

	Country findByName(String name);

}
