package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IProjectDao;
import com.ntt.data.model.Project;
import com.ntt.data.service.IProjectService;

@Service(value = "projectService")
public class ProjectService implements IProjectService{

	@Autowired
	IProjectDao projectDAO;
	
	@Override
	@Transactional
	public List<Project> getAll(){
		return projectDAO.getAll();
	}
	
	@Override
	@Transactional
	public Project getProjectById(Long id){
		return projectDAO.getById(id);
	}

	@Override
	@Transactional
	public void save(Project project) {
		projectDAO.persist(project);
	}

	@Override
	@Transactional
	public void delete(Project project) {
		projectDAO.delete(project);
	}

	@Override
	@Transactional
	public List<Project> getByCountry(Long countryID) {
		return projectDAO.getByCountry(countryID);
	}
	
}
