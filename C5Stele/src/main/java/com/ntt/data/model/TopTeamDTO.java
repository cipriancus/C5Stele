package com.ntt.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopTeamDTO {

	@Column(name = "NR_VOTES")
	private Long noOfVotes;
	
	@Id
	@Column(name = "TEAMS_ID")
	private Long teamId;
	
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	@Column(name = "PID")
	private Long periodId;

	public Long getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(Long noOfVotes) {
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

	@Override
	public String toString() {
		return "TopTeamDTO [noOfVotes=" + noOfVotes + ", teamId=" + teamId + ", firstName=" + teamName + ", periodId="
				+ periodId + "]";
	}

}
