package com.knowledge.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;
	private String userName;
	private String password;
	private String email;
	private String schoolName;
	
	public UserDetail() {}

	public UserDetail(Long userid, String userName, String password, String email, String schoolName) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.schoolName = schoolName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
