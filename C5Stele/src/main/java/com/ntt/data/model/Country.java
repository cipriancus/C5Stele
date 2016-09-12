package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Countries")
public class Country extends BaseEntity {
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LANGUAGES_ID")
	private Long languagesId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLanguagesId() {
		return languagesId;
	}

	public void setLanguagesId(Long languagesId) {
		this.languagesId = languagesId;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", languagesId=" + languagesId + "]";
	}
}
