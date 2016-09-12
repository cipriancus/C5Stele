package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.ITeamCandidateDao;
import com.ntt.data.model.TeamCandidate;

@Repository
public class TeamCandidateDao extends GenericDao<TeamCandidate> implements ITeamCandidateDao {

	@Override
	public List<TeamCandidate> getAllActiveDistinct(Long periodId) {
		TypedQuery<TeamCandidate> query = entityManager.createQuery("from TeamCandidate where active = 1 and periodId=:period group by selectedTeamId", TeamCandidate.class).setParameter("period", periodId);
		
		if(query.getResultList().isEmpty())
			return null;
		
		return query.getResultList();
	}

	@Override
	public TeamCandidate getActiveByUserId(Long userId, Long periodId) {
		TypedQuery<TeamCandidate> query=
				entityManager.createQuery("from TeamCandidate where proposedByUserId=:userId and active=1 and periodId=:period",TeamCandidate.class)
				.setParameter("userId", userId).setParameter("period", periodId);
	
		List<TeamCandidate> resultList=query.getResultList();
		
		if(resultList.isEmpty())
			return null;
		return resultList.get(0);
	}
	
	@Override
	public List<TeamCandidate> filterByNameAgencyProject(String filter, Long periodId,Long countryID) {
		TypedQuery<TeamCandidate> query = entityManager
				.createQuery("select distinct t from TeamCandidate as t INNER JOIN t.candTeam.project as p INNER JOIN p.agency as a where t.candTeam.name LIKE :filter and t.active = 1 and t.periodId=:period and a.countriesId=:country_id", TeamCandidate.class)
				.setParameter("filter", '%' + filter + '%').setParameter("period", periodId).setParameter("country_id", countryID);
		return  query.getResultList();
	}
}
