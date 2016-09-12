package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.ITeamDao;
import com.ntt.data.model.Team;

@Repository
public class TeamDao extends GenericDao<Team> implements ITeamDao {

	@Override
	public List<Team> filterByNameAgencyProject(String filter,Long countryID) {
		int length=filter.length();
		if(length!=0){
		TypedQuery<Team> query = entityManager.createQuery(
				"select t from Team as t  INNER JOIN t.project as p INNER JOIN p.agency as a where a.countriesId=:country_id and t.name LIKE :filter",
				Team.class).setParameter("filter", '%' + filter + '%').setParameter("country_id", countryID);
		return query.getResultList();
		}else return getAllfromCountry(countryID);
	}

	@Override
	public Team getTeamByName(String name) {
		TypedQuery<Team> query = entityManager.createQuery("from Team t where t.name=:name_team", Team.class)
				.setParameter("name_team", name);
		return query.getResultList().get(0);
	}

	@Override
	public List<Team> getTeamsForProjectID(Long id) {
		TypedQuery<Team> query = entityManager.createQuery("From Team t where t.projectsId=:project_id", Team.class)
				.setParameter("project_id", id);
		return query.getResultList();
	}

	@Override
	public List<Team> getAllfromCountry(Long countrieId) {
		TypedQuery<Team> query = entityManager
				.createQuery("select t From Team as t INNER JOIN t.project as p INNER JOIN p.agency as a where a.countriesId=:country_id",
						Team.class)
				.setParameter("country_id", countrieId);
		
		List<Team> resultList = query.getResultList();

		if (resultList.isEmpty()) {
			return null;
		}

		return resultList;
	}

}
