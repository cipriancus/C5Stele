package com.ntt.data.service;

import java.math.BigInteger;
import java.util.List;

import com.ntt.data.model.TeamWinnerDTO;
import com.ntt.data.model.TopTeamDTO;

public interface ITeamVoteService {

	void saveTeamVote(Long periodId, Long userId, Long teamCandId);

	List<TeamWinnerDTO> getLastNWinners(Integer n, Long countriesId);

	List<TopTeamDTO> getCurrentTopN(Integer n, Long countriesId);

	BigInteger getTotalNumberOfVotes(Long countriesId);

	TeamWinnerDTO getWinner(Long countriesId);

}
