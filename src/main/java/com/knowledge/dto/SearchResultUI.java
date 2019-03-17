package com.knowledge.dto;

public class SearchResultUI {
	private String url;
	private String locationCategory;
	private String searchCategory;
	private String searchResult;
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
	public String getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}
	public SearchResultUI(String url, String locationCategory, String searchCategory, String searchResult) {
		super();
		this.url = url;
		this.locationCategory = locationCategory;
		this.searchCategory = searchCategory;
		this.searchResult = searchResult;
	}
}
