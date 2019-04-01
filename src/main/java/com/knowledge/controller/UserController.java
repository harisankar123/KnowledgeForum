package com.knowledge.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knowledge.dao.UserRepository;
import com.knowledge.dto.UserDetail;

@RestController
@EnableAutoConfiguration
public class UserController {
	@Inject
	private UserRepository userrepo;
	
	@GetMapping("/api/getUsers")
	public List<UserDetail> getAllUsers(){
		return StreamSupport.stream(userrepo.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/api/saveUser")
	public UserDetail saveUser(@RequestBody UserDetail userdetail) {
		return userrepo.save(userdetail);
	}
	
	@PostMapping("/api/deleteUser/{userId}")
	public boolean deleteUser(@PathVariable("userId") final long userId) {
		try {
		 userrepo.deleteById(userId);
		 return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	@PostMapping("/api/login")
	public boolean loginUser(UserDetail user) {
		return Objects.nonNull(userrepo.findByUserNameAndPassword(user.getUserName(), user.getPassword()))?
				true:false;
	}
}
