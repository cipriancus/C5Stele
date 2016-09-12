package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.Candidate;

public interface ICandidateService {

	void saveCandidate(Long periodId, Long userId, Long candId, String reasons);

	List<Candidate> getAllActiveDistinct(Long periodId);

	List<Candidate> filterByNameOrAgency(String filter, Long periodId,Long countryID);

	List<String> getAllReasonsForUser(Long userId, Long periodId);

}
