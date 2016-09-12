package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.ICandidateDao;
import com.ntt.data.model.Candidate;

@Repository
public class CandidateDao extends GenericDao<Candidate> implements ICandidateDao {
	
	@Override
	public Candidate getActiveByUserId(Long userId, Long periodId) {
		TypedQuery<Candidate> query = entityManager
				.createQuery("from Candidate where proposedByUserId=:userId and active=1 and periodId=:period", Candidate.class)
				.setParameter("userId", userId)
				.setParameter("period", periodId);
		
		List<Candidate> resultList = query.getResultList();
		
		if (resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}

	@Override
	public List<Candidate> getAllActiveDistinct(Long periodId) {
		TypedQuery<Candidate> query = entityManager
				.createQuery("from Candidate where active=1 and periodId=:period group by selectedUserId", Candidate.class).setParameter("period", periodId);
		
		List<Candidate> resultList = query.getResultList();
		
		if (resultList.isEmpty()) {
			return null;
		}
		
		return resultList;
	}
	
	@Override
	public List<Candidate> filterByNameOrAgency(String filter, Long periodId,Long countryID) {
		TypedQuery<Candidate> query = entityManager
				.createQuery(
						"select distinct c from Candidate as c INNER JOIN c.candUser.agency as a where a.countriesId=:country_id and ( c.candUser.firstName LIKE :filter OR c.candUser.lastName LIKE :filter ) and c.active = 1 and c.periodId=:period)", Candidate.class)
				.setParameter("filter", '%' + filter + '%').setParameter("period", periodId).setParameter("country_id", countryID);
		return  query.getResultList();
	}
	
	@Override
	public List<String> getAllReasonsForUser(Long userId, Long periodId) {
		TypedQuery<String> query = entityManager.createQuery("select reasons from Candidate as c where c.selectedUserId = :userId and c.periodId = :periodId", String.class)
				.setParameter("userId", userId)
				.setParameter("periodId", periodId);
		return  query.getResultList();
	}
	 
}
