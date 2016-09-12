package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.TeamCandidate;

public interface ITeamCandidateDao extends IGenericDao<TeamCandidate>{

	TeamCandidate getActiveByUserId(Long userId, Long periodId);
	
	List<TeamCandidate> getAllActiveDistinct(Long periodId);

	List<TeamCandidate> filterByNameAgencyProject(String filter, Long periodId,Long countryID);

}
