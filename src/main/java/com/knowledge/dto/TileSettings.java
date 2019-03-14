package com.knowledge.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.knowledge.web.View;

@Entity
@Table(name = "Tiles")
public class TileSettings {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonView(View.Tile.class)
	private String title;
	@JsonView(View.Tile.class)
	private Integer displayOrder;
	@JsonView(View.Tile.class)
	private String navigationUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getNavigationUrl() {
		return navigationUrl;
	}

	public void setNavigationUrl(String navigationUrl) {
		this.navigationUrl = navigationUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static TilesBuilder aTileBuilder(){
		return new TilesBuilder();
	}
	
	public static final class TilesBuilder {
		private String title;
		private Integer displayOrder;
		private String navigationUrl;

		public TilesBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public TilesBuilder withDisplayOrder(Integer displayOrder) {
			this.displayOrder = displayOrder;
			return this;
		}

		public TilesBuilder withNavigationUrl(String navigationUrl) {
			this.navigationUrl = navigationUrl;
			return this;
		}


		public TileSettings build() {
			return new TileSettings(title, displayOrder, navigationUrl);
		}
	}
	
	public TileSettings(){}

	public TileSettings(String title, Integer displayOrder, String navigationUrl) {
		super();
		this.title = title;
		this.displayOrder = displayOrder;
		this.navigationUrl = navigationUrl;
	}

}
