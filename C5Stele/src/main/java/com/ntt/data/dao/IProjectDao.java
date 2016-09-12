package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.Project;

public interface IProjectDao extends IGenericDao<Project>{
	Project getByTeam(String id);

	List<Project> getByCountry(Long countryID);
}

