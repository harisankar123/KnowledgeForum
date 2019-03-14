package com.knowledge.dao;

import org.springframework.data.repository.CrudRepository;

import com.knowledge.dto.TileSettings;

public interface TilesRepository  extends CrudRepository<TileSettings, Long> {
	
}
