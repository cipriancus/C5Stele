package com.ntt.data.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Team_Votes")
public class TeamVote extends BaseAuditEntity {

	@Column(name = "PERIOD_ID")
	private Long periodId;
	
	@Column(name = "USERS_ID")
	private Long usersId;
	
	@Column(name = "TEAMS_ID")
	private Long teamsId;
	
	@Column(name = "ACTIVE")
	private Integer active;
	
	@Column(name = "DATE")
	private Date date;
	
	@PrePersist
	protected void onCreate() {
		this.date = Date.valueOf(LocalDate.now());
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public Long getTeamsId() {
		return teamsId;
	}

	public void setTeamsId(Long teamsId) {
		this.teamsId = teamsId;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TeamVote [periodId=" + periodId + ", usersId=" + usersId + ", teamsId=" + teamsId
				+ ", active=" + active + ", date=" + date + "]";
	}
}
