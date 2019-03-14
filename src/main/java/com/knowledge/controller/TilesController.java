package com.knowledge.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.knowledge.dao.TilesRepository;
import com.knowledge.dto.TileSettings;
import com.knowledge.web.View;

@RestController
@EnableAutoConfiguration
public class TilesController {
	@Inject
	private TilesRepository repository;
	
	@JsonView(View.Tile.class)
	@GetMapping("/api/getTiles")
	public List<TileSettings> getAllUsers(){
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@JsonView(View.Tile.class)
	@PostMapping("/api/saveTiles")
	public TileSettings getTiles(@RequestBody TileSettings tileSettings) {
		return repository.save(tileSettings);
	}
}
