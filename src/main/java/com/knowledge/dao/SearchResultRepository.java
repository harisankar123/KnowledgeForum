package com.knowledge.dao;

import org.springframework.data.repository.CrudRepository;

import com.knowledge.dto.SearchResult;

public interface SearchResultRepository extends CrudRepository<SearchResult, Long> {
	
}

