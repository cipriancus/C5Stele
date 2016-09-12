package com.ntt.data.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Team_Candidates")
public class TeamCandidate extends BaseAuditEntity {

	@Column(name = "PERIOD_ID")
	private Long periodId;
	
	@Column(name = "TEAMS_PROPOSED_BY_USER_ID")
	private Long proposedByUserId;
	
	@Column(name = "SELECTED_TEAMS_ID")
	private Long selectedTeamId;
	
	@Column(name = "ACTIVE")
	private Integer active;
	
	@Column(name = "REASONS")
	private String reasons;
	
	@Column(name = "DATE")
	private Date date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SELECTED_TEAMS_ID", unique= true, nullable=true, insertable=false, updatable=false)
	private Team candTeam;
	
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

	public Long getProposedByUserId() {
		return proposedByUserId;
	}

	public void setProposedByUserId(Long proposedByUserId) {
		this.proposedByUserId = proposedByUserId;
	}

	public Long getSelectedTeamId() {
		return selectedTeamId;
	}

	public void setSelectedTeamId(Long selectedTeamId) {
		this.selectedTeamId = selectedTeamId;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Team getCandTeam() {
		return candTeam;
	}

	public void setCandTeam(Team candTeam) {
		this.candTeam = candTeam;
	}

	@Override
	public String toString() {
		return "TeamCandidate [periodId=" + periodId + ", proposedByUserId=" + proposedByUserId + ", selectedTeamId="
				+ selectedTeamId + ", active=" + active + ", reasons=" + reasons + ", date=" + date + "]";
	}
}
