package com.ntt.data.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.ntt.data.dao.ITeamVoteDao;
import com.ntt.data.model.TeamVote;
import com.ntt.data.model.TeamWinnerDTO;
import com.ntt.data.model.TopTeamDTO;

@Repository
public class TeamVoteDao extends GenericDao<TeamVote> implements ITeamVoteDao {

	@Override
	public TeamVote getActiveByUserId(Long userId, Long periodId) {
		TypedQuery<TeamVote> query=
				entityManager.createQuery("from TeamVote where usersId=:userId and active=1 and periodId=:period",TeamVote.class)
				.setParameter("userId", userId)
				.setParameter("period", periodId);
	
		List<TeamVote> resultList=query.getResultList();
		
		if(resultList.isEmpty())
			return null;
		return resultList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeamWinnerDTO> getLastNWinners(Integer n, Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT MAX(vp.nr_votes) max_votes, vp.teams_id, t.team_name, vp.pid, vp.last_voting_day, a.city FROM (SELECT COUNT(v.id) nr_votes, v.teams_id, p.id pid, p.last_voting_day FROM team_votes v JOIN periods p ON v.period_id = p.id WHERE p.countries_id = :country AND p.active = 0 AND v.active = 1 GROUP BY p.id , v.teams_id) vp JOIN teams t ON vp.teams_id = t.id JOIN Projects AS prj ON prj.id = t.projects_id JOIN Agencies AS a ON prj.agencies_id = a.id GROUP BY vp.pid ORDER BY vp.last_voting_day DESC", TeamWinnerDTO.class);
		query.setParameter("country", countriesId);
		query.setMaxResults(n);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TeamWinnerDTO getWinner(Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT MAX(vp.nr_votes) max_votes, vp.teams_id, t.team_name, vp.pid, vp.last_voting_day, a.city FROM (SELECT COUNT(v.id) nr_votes, v.teams_id, p.id pid, p.last_voting_day FROM team_votes v JOIN periods p ON v.period_id = p.id WHERE p.countries_id = :country AND p.active = 1 AND v.active = 1 GROUP BY p.id , v.teams_id) vp JOIN teams t ON vp.teams_id = t.id JOIN Projects AS prj ON prj.id = t.projects_id JOIN Agencies AS a ON prj.agencies_id = a.id GROUP BY vp.pid ORDER BY vp.last_voting_day DESC", TeamWinnerDTO.class);
		query.setParameter("country", countriesId);
		
		List<TeamWinnerDTO> team=query.getResultList();
		
		if(team.isEmpty())
			return null;
			
		return  team.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TopTeamDTO> getCurrentTopN(Integer n, Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) AS nr_votes, v.teams_id, p.id AS pid, t.team_name FROM Team_votes AS v JOIN Periods AS p ON v.period_id = p.id JOIN Teams AS t ON v.teams_id = t.id WHERE p.countries_id = :country AND p.active = 1 AND v.active = 1 GROUP BY v.teams_id ORDER BY nr_votes DESC", TopTeamDTO.class);
		query.setParameter("country", countriesId);
		query.setMaxResults(n);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigInteger getTotalNumberOfVotes(Long countriesId) {
		Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) nr_votes FROM team_votes v WHERE v.active = 1 AND v.period_id IN (SELECT p.id FROM periods p WHERE p.countries_id = :country and p.active = 1)");
		query.setParameter("country", countriesId);
		
		List<BigInteger> resultList=query.getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}
}
