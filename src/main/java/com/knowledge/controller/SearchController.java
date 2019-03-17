package com.knowledge.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.knowledge.dao.SearchResultRepository;
import com.knowledge.dto.SearchCritera;
import com.knowledge.dto.SearchResult;
import com.knowledge.dto.SearchResultUI;
import com.knowledge.dto.TileSettings;
import com.knowledge.service.SearchResultService;
import com.knowledge.web.View;

@RestController
@EnableAutoConfiguration
public class SearchController {
	@Inject
	private SearchResultService searchResultCalulator;
	
	@Inject
	private SearchResultRepository searchResultRepository;
	
	@PostMapping("/api/search")
	public SearchResultUI search(@Valid @RequestBody SearchCritera criteria) {
		final SearchResult searchResult = searchResultCalulator.getSearchResult(criteria);
		searchResultRepository.save(searchResult);
		return new SearchResultUI(searchResult.getUrl(), searchResult.getLocationCategory(),
				searchResult.getSearchCategory(), new String(searchResult.getSearchResult()));
	}
	
	@GetMapping("/api/getReport")
	public List<SearchResultUI> getAllUsers(){
		return StreamSupport.stream(searchResultRepository.findAll().spliterator(), false)
				.map(searchResult -> {
					return new SearchResultUI(searchResult.getUrl(), searchResult.getLocationCategory(),
							searchResult.getSearchCategory(), new String(searchResult.getSearchResult())); 
				})
				.collect(Collectors.toList());
	}
	
}
