package com.careerit.rcs.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.careerit.rcs.domain.Country;
import com.careerit.rcs.dto.CountryRegionDto;
import com.careerit.rcs.dto.CountryStateDto;
import com.careerit.rcs.dto.CountryStateWrapperDto;
import com.careerit.rcs.repo.CountryRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryDataServiceImpl implements CountryDataService {

	@Value("${restcountryapi.access_key}")
	private String accessKey;

	@Autowired
	private RestTemplate restTeamplate;

	@Autowired
	private CountryRepo countryRepo;

	@Override
	public List<CountryRegionDto> getCountryRegionDetails() {

		String api = String.format("http://api.countrylayer.com/v2/all?access_key=%s", accessKey);
		ResponseEntity<List<CountryRegionDto>> response = restTeamplate.exchange(api, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CountryRegionDto>>() {
				});
		List<CountryRegionDto> list = response.getBody();
		log.info("Total country count is :{}", list.size());
		return list;

	}

	@Override
	public CountryStateWrapperDto getCountryStateDetails() {

		ObjectMapper objectMapper = new ObjectMapper();
		CountryStateWrapperDto obj = new CountryStateWrapperDto();
		try {
			obj = objectMapper.readValue(this.getClass().getResourceAsStream("/country_state.json"),
					new TypeReference<CountryStateWrapperDto>() {
					});
			System.out.println(obj.getCountries());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public List<Country> addCountryDetails() {
		List<CountryRegionDto> listCountryRegion = getCountryRegionDetails();
		CountryStateWrapperDto wobj = getCountryStateDetails();

		Map<String, List<String>> map = wobj.getCountries().stream()
				.collect(Collectors.toMap(CountryStateDto::getCountry, CountryStateDto::getStates));

		List<Country> list = new ArrayList<>();

		for (CountryRegionDto cr : listCountryRegion) {

			List<String> states = map.getOrDefault(cr.getName(), new ArrayList<>());
			Country country = Country.builder().name(cr.getName()).capital(cr.getCapital()).region(cr.getRegion())
					.states(states).build();
			list.add(country);
		}
		list=countryRepo.saveAll(list);
		
		return list;
	}

}
