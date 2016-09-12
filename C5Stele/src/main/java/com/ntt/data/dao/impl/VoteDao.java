package com.ntt.data.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IVoteDao;
import com.ntt.data.model.TopCandidateDTO;
import com.ntt.data.model.Vote;
import com.ntt.data.model.WinnerDTO;

@Repository
public class VoteDao extends GenericDao<Vote> implements IVoteDao {
	@Override
	public Vote getActiveByUserId(Long userId, Long periodId) {
		TypedQuery<Vote> query=
				entityManager.createQuery("from Vote where usersId=:userId and active=1 and periodId=:period",Vote.class)
				.setParameter("userId", userId)
				.setParameter("period", periodId);
	
		List<Vote> resultList=query.getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WinnerDTO> getLastNWinners(Integer n, Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT MAX(vp.nr_votes) max_votes, vp.cand_users_id, u.first_name, u.last_name, vp.pid, vp.last_voting_day, p.image, p.type_of_image FROM (SELECT COUNT(v.id) nr_votes, v.cand_users_id, p.id pid, p.last_voting_day FROM votes v JOIN periods p ON v.period_id = p.id WHERE p.countries_id = :country AND p.active = 0 AND v.active = 1 GROUP BY p.id , v.cand_users_id) vp JOIN users u ON vp.cand_users_id = u.id LEFT OUTER JOIN pictures p ON vp.cand_users_id = p.id_of_user GROUP BY vp.pid ORDER BY vp.last_voting_day DESC", WinnerDTO.class);
		query.setParameter("country", countriesId);
		query.setMaxResults(n);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TopCandidateDTO> getCurrentTopN(Integer n, Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) as nr_votes, v.cand_users_id, p.id as pid, u.first_name, u.last_name FROM Votes as v JOIN Periods as p ON v.period_id = p.id JOIN Users as u ON v.cand_users_id = u.id WHERE p.countries_id = :country AND p.active = 1 AND v.active = 1 GROUP BY v.cand_users_id ORDER BY nr_votes DESC", TopCandidateDTO.class);
		query.setParameter("country", countriesId);
		query.setMaxResults(n);
		
		if(query.getResultList().isEmpty())
			return null;
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigInteger getTotalNumberOfVotes(Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) nr_votes FROM votes v WHERE v.active = 1 AND v.period_id IN (SELECT p.id FROM periods p WHERE p.countries_id = :country and p.active = 1)");
		query.setParameter("country", countriesId);
		
		List<BigInteger> resultList=query.getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public WinnerDTO getCurrentWinner(Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT COUNT(vp.id) max_votes, vp.cand_users_id, u.first_name, u.last_name, vp.pid, vp.last_voting_day, p.image, p.type_of_image FROM (SELECT v.id, v.cand_users_id, p.id pid, p.last_voting_day FROM votes v JOIN periods p ON v.period_id = p.id WHERE p.countries_id = :country AND p.active = 1 AND p.last_voting_day < current_date() AND v.active = 1 GROUP BY p.id , v.cand_users_id) vp JOIN users u ON vp.cand_users_id = u.id LEFT OUTER JOIN pictures p ON vp.cand_users_id = p.id_of_user GROUP BY vp.pid ORDER BY vp.last_voting_day DESC", WinnerDTO.class);
		query.setParameter("country", countriesId);
		
		List<WinnerDTO> resultList=query.getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}
	
}
