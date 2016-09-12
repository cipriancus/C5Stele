package com.ntt.data.service;

import java.math.BigInteger;
import java.util.List;

import com.ntt.data.model.TopCandidateDTO;
import com.ntt.data.model.WinnerDTO;

public interface IVoteService {

	void saveVote(Long periodId, Long userId, Long candId);

	List<WinnerDTO> getLastNWinners(Integer n, Long countriesId);

	List<TopCandidateDTO> getCurrentTopN(Integer n, Long countriesId);

	BigInteger getTotalNumberOfVotes(Long long1);

	WinnerDTO getCurrentWinner(Long countriesId);
}
