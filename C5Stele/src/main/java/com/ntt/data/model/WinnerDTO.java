package com.ntt.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class WinnerDTO implements Serializable{
	
	private static final long serialVersionUID = -527868730095944093L;
	
	@Column(name = "MAX_VOTES")
	private String noOfVotes;
	
	@Column(name = "CAND_USERS_ID")
	private Long userId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Id
	@Column(name = "PID")
	private Long periodId;
	
	@Column(name = "LAST_VOTING_DAY")
	private Date lastVotingDay;
	
	@Column(name = "IMAGE")
	private String base64Image;
	
	@Column(name = "TYPE_OF_IMAGE")
	private String typeOfImage;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CAND_USERS_ID", unique = true, nullable = true, insertable = false, updatable = false)
	private User candUser;
	
	public String getNoOfVotes() {
		return noOfVotes;
	}
	
	public void setNoOfVotes(String noOfVotes) {
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
	
	public Date getLastVotingDay() {
		return lastVotingDay;
	}
	
	public void setLastVotingDay(Date lastVotingDay) {
		this.lastVotingDay = lastVotingDay;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getTypeOfImage() {
		return typeOfImage;
	}

	public void setTypeOfImage(String typeOfImage) {
		this.typeOfImage = typeOfImage;
	}

	public User getCandUser() {
		return candUser;
	}

	public void setCandUser(User candUser) {
		this.candUser = candUser;
	}

	@Override
	public String toString() {
		return "WinnerDTO [noOfVotes=" + noOfVotes + ", userId=" + userId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", periodId=" + periodId + ", lastVotingDay=" + lastVotingDay + ", base64Image="
				+ base64Image + ", typeOfImage=" + typeOfImage + "]";
	}
}
