package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Agencies")
public class Agency extends BaseEntity {
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "COUNTRIES_ID")
	private Long countriesId;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getCountriesId() {
		return countriesId;
	}

	public void setCountriesId(Long countriesId) {
		this.countriesId = countriesId;
	}
	
	@Override
	public String toString() {
		return "Agency [city=" + city + ", countriesId=" + countriesId + "]";
	}
}
