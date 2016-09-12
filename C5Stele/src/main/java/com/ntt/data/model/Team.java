package com.ntt.data.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Teams")
public class Team extends BaseEntity  {

	@Column(name = "TEAM_NAME")
	private String name;
	
	@Column(name = "PROJECTS_ID")
	private Long projectsId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECTS_ID", unique= true, nullable=true, insertable=false, updatable=false)
	private Project project;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="TEAMS_ID", insertable=false, updatable=false)
	private List<User> members;
	
	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProjectsId() {
		return projectsId;
	}

	public void setProjectsId(Long projectsId) {
		this.projectsId = projectsId;
	}
	
	@Override
	public String toString() {
		return "Team [name=" + name + ", projectsId=" + projectsId + "]";
	}
}
