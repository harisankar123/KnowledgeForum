package com.knowledge.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name = "SearchResult")
public class SearchResult {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String url;
	private String locationCategory;
	private String searchCategory;
	@Lob
	@Column(length=100000)
	private byte[] searchResult;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocationCategory() {
		return locationCategory;
	}
	public void setLocationCategory(String locationCategory) {
		this.locationCategory = locationCategory;
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	
	public SearchResult() {}
	
	public SearchResult(String url, String locationCategory, String searchCategory, byte[] searchResult) {
		super();
		this.url = url;
		this.locationCategory = locationCategory;
		this.searchCategory = searchCategory;
		this.searchResult = searchResult;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(byte[] searchResult) {
		this.searchResult = searchResult;
	}
}
