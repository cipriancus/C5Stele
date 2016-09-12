package com.ntt.data.service;

import java.util.List;
import com.ntt.data.model.Project;

public interface IProjectService {

	public List<Project> getAll();

	Project getProjectById(Long id);
	
	void save(Project project);
	
	void delete(Project project);
	
	public List<Project> getByCountry(Long countryID);

}
