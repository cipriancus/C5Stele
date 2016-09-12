package com.ntt.data.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IVoteDao;
import com.ntt.data.model.TopCandidateDTO;
import com.ntt.data.model.Vote;
import com.ntt.data.model.WinnerDTO;
import com.ntt.data.service.IVoteService;

@Service(value = "voteService")
public class VoteService implements IVoteService {

	@Autowired
	private IVoteDao voteDao;

	@Override
	@Transactional
	public void saveVote(Long periodId, Long userId, Long candId) {
		
		Vote vote = voteDao.getActiveByUserId(userId, periodId);

		if (vote != null) {

			vote.setActive(0);
			voteDao.persist(vote);
		}

		Vote newVote = new Vote();
		newVote.setCandUsersId(candId);
		newVote.setUsersId(userId);
		newVote.setPeriodId(periodId);
		newVote.setActive(1);

		voteDao.persist(newVote);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<WinnerDTO> getLastNWinners(Integer n, Long countriesId) {
		return voteDao.getLastNWinners(n, countriesId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TopCandidateDTO> getCurrentTopN(Integer n, Long countriesId) {
		return voteDao.getCurrentTopN(n, countriesId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public BigInteger getTotalNumberOfVotes(Long countriesId) {
		return voteDao.getTotalNumberOfVotes(countriesId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public WinnerDTO getCurrentWinner(Long countriesId) {
		return voteDao.getCurrentWinner(countriesId);
	}

}
