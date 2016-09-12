package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.TeamCandidate;

public interface ITeamCandidateService {

	void saveTeamCandidate(Long periodId, Long userId, Long candId, String reasons);

	List<TeamCandidate> getAllActiveDistinct(Long periodId);

	List<TeamCandidate> filterByNameAgencyProject(String filter, Long periodId,Long countryID);

}
