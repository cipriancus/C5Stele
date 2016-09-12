package com.ntt.data.dao;

import java.math.BigInteger;
import java.util.List;

import com.ntt.data.model.TeamVote;
import com.ntt.data.model.TeamWinnerDTO;
import com.ntt.data.model.TopTeamDTO;

public interface ITeamVoteDao extends IGenericDao<TeamVote> {

	TeamVote getActiveByUserId(Long userId, Long periodId);

	List<TeamWinnerDTO> getLastNWinners(Integer n, Long countriesId);

	List<TopTeamDTO> getCurrentTopN(Integer n, Long countriesId);

	BigInteger getTotalNumberOfVotes(Long countriesId);

	TeamWinnerDTO getWinner(Long countriesId);

}
