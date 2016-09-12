package com.ntt.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopCandidateDTO {

	@Column(name = "NR_VOTES")
	private Long noOfVotes;
	
	@Id
	@Column(name = "CAND_USERS_ID")
	private Long userId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PID")
	private Long periodId;

	public Long getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(Long noOfVotes) {
		this.noOfVotes = noOfVotes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	@Override
	public String toString() {
		return "TopCandidateDTO [noOfVotes=" + noOfVotes + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", periodId=" + periodId + "]";
	}
	
}
