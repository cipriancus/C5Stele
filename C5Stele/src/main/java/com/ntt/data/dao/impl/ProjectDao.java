package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IProjectDao;
import com.ntt.data.model.Project;

@Repository
public class ProjectDao extends GenericDao<Project> implements IProjectDao {

	@Override
	public Project getByTeam(String id) {
		TypedQuery<Project> query= entityManager.createQuery("from Project where projectsId = :id", Project.class).setParameter("id", id);
		
		List<Project> resultList = query.getResultList();
		
		if (resultList.isEmpty()){
			return null;
		}
		
		return resultList.get(0);
	}

	@Override
	public List<Project> getByCountry(Long countryID) {
		TypedQuery<Project> query= entityManager.createQuery("Select p From Project as p INNER JOIN p.agency as a where a.countriesId=:country_id", Project.class).setParameter("country_id", countryID);
		
		List<Project> resultList = query.getResultList();
		
		if (resultList.isEmpty()){
			return null;
		}
		
		return resultList;
	}

}
