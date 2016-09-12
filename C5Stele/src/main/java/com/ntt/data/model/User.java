package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends BaseAuditEntity {

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ROLES_ID")
	private Long rolesId;

	@Column(name = "AGENCIES_ID")
	private Long agenciesId;

	@Column(name = "POSITIONS_ID")
	private Long positionsId;

	@Column(name = "TEAMS_ID")
	private Long teamsId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AGENCIES_ID", unique = true, nullable = true, insertable = false, updatable = false)
	private Agency agency;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITIONS_ID", unique = true, nullable = true, insertable = false, updatable = false)
	private Position position;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy="user")
	private Picture picture;

	@Column(name = "ACTIVE")
	private boolean active;
	
	@Column(name="UID")
	private String uid;
	
	@Column(name="FORGOT_UID")
	private String forgot_uid;
	
	public String getForgot_uid() {
		return forgot_uid;
	}

	public void setForgot_uid(String forgot_uid) {
		this.forgot_uid = forgot_uid;
	}

	public Long getTeamsId() {
		return teamsId;
	}

	public void setTeamsId(Long teamsId) {
		this.teamsId = teamsId;
	}

	public Long getRolesId() {
		return rolesId;
	}

	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}

	public Long getAgenciesId() {
		return agenciesId;
	}

	public void setAgenciesId(Long agenciesId) {
		this.agenciesId = agenciesId;
	}

	public Long getPositionsId() {
		return positionsId;
	}

	public void setPositionsId(Long positionsId) {
		this.positionsId = positionsId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", username=" + username + ", password=" + password + ", rolesId="
				+ rolesId + ", agenciesId=" + agenciesId + ", positionsId=" + positionsId + ", teamsId=" + teamsId
				+ ", agency=" + agency + ", position=" + position + ", active=" + active + ", uid=" + uid + "]";
	}	
}
