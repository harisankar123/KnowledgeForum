package com.knowledge.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knowledge.calculator.SearchResultCalculator;
import com.knowledge.dto.SearchCritera;

@RestController
@EnableAutoConfiguration
public class SearchController {
	@Inject
	private SearchResultCalculator searchResultCalulator;
	@PostMapping("/api/search")
	public String search(@Valid @RequestBody SearchCritera criteria) {
		return searchResultCalulator.getSearchResult(criteria);
	}
}
