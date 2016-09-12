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
@Table(name = "Candidates")
public class Candidate extends BaseAuditEntity {

	@Column(name = "PERIOD_ID")
	private Long periodId;
	
	@Column(name = "PROPOSED_BY_USER_ID")
	private Long proposedByUserId;
	
	@Column(name = "SELECTED_USER_ID")
	private Long selectedUserId;
	
	@Column(name = "ACTIVE")
	private Integer active;
	
	@Column(name = "REASONS")
	private String reasons;
	
	@Column(name = "DATE")
	private Date date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SELECTED_USER_ID", unique= true, nullable=true, insertable=false, updatable=false)
	private User candUser;
	
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

	public Long getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(Long selectedUserId) {
		this.selectedUserId = selectedUserId;
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

	public User getCandUser() {
		return candUser;
	}

	public void setCandUser(User candUser) {
		this.candUser = candUser;
	}

	@Override
	public String toString() {
		return "Candidate [periodId=" + periodId + ", proposedByUserId=" + proposedByUserId + ", selectedUserId="
				+ selectedUserId + ", active=" + active + ", reasons=" + reasons + ", date=" + date + "]";
	}
}
