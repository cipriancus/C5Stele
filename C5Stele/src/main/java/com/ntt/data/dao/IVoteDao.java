package com.ntt.data.dao;

import java.math.BigInteger;
import java.util.List;

import com.ntt.data.model.TopCandidateDTO;
import com.ntt.data.model.Vote;
import com.ntt.data.model.WinnerDTO;

public interface IVoteDao extends IGenericDao<Vote> {

	Vote getActiveByUserId(Long userId, Long periodId);

	List<WinnerDTO> getLastNWinners(Integer n, Long countriesId);

	List<TopCandidateDTO> getCurrentTopN(Integer n, Long countriesId);

	BigInteger getTotalNumberOfVotes(Long countriesId);

	WinnerDTO getCurrentWinner(Long countriesId);
}
