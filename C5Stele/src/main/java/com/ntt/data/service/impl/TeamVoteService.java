package com.ntt.data.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.ITeamVoteDao;
import com.ntt.data.model.TeamVote;
import com.ntt.data.model.TeamWinnerDTO;
import com.ntt.data.model.TopTeamDTO;
import com.ntt.data.service.ITeamVoteService;

@Service(value = "teamVoteService")
public class TeamVoteService implements ITeamVoteService {

	@Autowired
	private ITeamVoteDao teamVoteDao;

	@Override
	@Transactional
	public void saveTeamVote(Long periodId, Long userId, Long teamCandId) {

		TeamVote teamVote = teamVoteDao.getActiveByUserId(userId, periodId);

		if (teamVote != null) {
			teamVote.setActive(0);
			teamVoteDao.persist(teamVote);
		}

		TeamVote newTeamVote = new TeamVote();
		newTeamVote.setTeamsId(teamCandId);
		newTeamVote.setUsersId(userId);
		newTeamVote.setPeriodId(periodId);
		newTeamVote.setActive(1);

		teamVoteDao.persist(newTeamVote);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeamWinnerDTO> getLastNWinners(Integer n, Long countriesId) {
		return teamVoteDao.getLastNWinners(n, countriesId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TopTeamDTO> getCurrentTopN(Integer n, Long countriesId) {
		return teamVoteDao.getCurrentTopN(n, countriesId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public BigInteger getTotalNumberOfVotes(Long countriesId) {
		return teamVoteDao.getTotalNumberOfVotes(countriesId);
	}
	
	@Transactional
	@Override
	public TeamWinnerDTO getWinner(Long countriesId) {
		return teamVoteDao.getWinner(countriesId);
	}


}