package com.ntt.data.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Periods")
public class Period extends BaseEntity {
	
	@Column(name = "LAST_RECOMMENDATION_DAY")
	private Date lastRecommendationDay;
	
	@Column(name = "LAST_VOTING_DAY")
	private Date lastVotingDay;
	
	@Column(name = "ACTIVE")
	private Integer active;
	
	@Column(name = "COUNTRIES_ID")
	private Long countriesId;



	public Date getLastRecommendationDay() {
		return lastRecommendationDay;
	}

	public void setLastRecommendationDay(Date lastRecommendationDay) {
		this.lastRecommendationDay = lastRecommendationDay;
	}

	public Date getLastVotingDay() {
		return lastVotingDay;
	}

	public void setLastVotingDay(Date lastVotingDay) {
		this.lastVotingDay = lastVotingDay;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Long getCountriesId() {
		return countriesId;
	}

	public void setCountriesId(Long countriesId) {
		this.countriesId = countriesId;
	}

	@Override
	public String toString() {
		return "Period [lastRecommendationDay=" + lastRecommendationDay + ", lastVotingDay=" + lastVotingDay
				+ ", active=" + active + ", countriesId=" + countriesId + "]";
	}
}
