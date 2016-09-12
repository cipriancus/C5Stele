package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.Candidate;

public interface ICandidateDao extends IGenericDao<Candidate> {

	List<Candidate> getAllActiveDistinct(Long periodId);
	
	List<Candidate> filterByNameOrAgency(String filter, Long periodId,Long countryID);

	Candidate getActiveByUserId(Long userId, Long periodId);

	List<String> getAllReasonsForUser(Long userId, Long periodId);

}
