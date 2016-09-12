package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Projects")
public class Project extends BaseEntity {

	@Column(name = "PROJECT_NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "AGENCIES_ID")
	private Long agenciesId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AGENCIES_ID", unique= true, nullable=true, insertable=false, updatable=false)
	private Agency agency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAgenciesId() {
		return agenciesId;
	}

	public void setAgenciesId(Long agenciesId) {
		this.agenciesId = agenciesId;
	}
	
	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", description=" + description + ", agenciesId=" + agenciesId + "]";
	}
}
