package com.ntt.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamWinnerDTO implements Serializable{
	
	private static final long serialVersionUID = -4524080327515945204L;

	@Column(name = "MAX_VOTES")
	private String noOfVotes;
	
	@Column(name = "TEAMS_ID")
	private Long teamId;
	
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	@Column(name = "CITY")
	private String city;
	
	@Id
	@Column(name = "PID")
	private Long periodId;
	
	@Column(name = "LAST_VOTING_DAY")
	private Date lastVotingDay;

	public String getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(String noOfVotes) {
		this.noOfVotes = noOfVotes;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Date getLastVotingDay() {
		return lastVotingDay;
	}

	public void setLastVotingDay(Date lastVotingDay) {
		this.lastVotingDay = lastVotingDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "TeamWinnerDTO [noOfVotes=" + noOfVotes + ", teamId=" + teamId + ", teamName=" + teamName + ", city="
				+ city + ", periodId=" + periodId + ", lastVotingDay=" + lastVotingDay + "]";
	}
	
}
