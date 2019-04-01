package com.knowledge.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.knowledge.dto.UserDetail;

public interface UserRepository extends CrudRepository<UserDetail, Long> {

	@Query("select c from UserDetail c where c.userName=?1 AND c.password =?2")
	UserDetail findByUserNameAndPassword(String userName, String password);


}
