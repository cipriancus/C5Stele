package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.ITeamCandidateDao;
import com.ntt.data.model.TeamCandidate;
import com.ntt.data.service.ITeamCandidateService;

@Service(value = "teamCandService")
public class TeamCandidateService implements ITeamCandidateService {

	
	@Autowired
	private ITeamCandidateDao teamCandDao;
	
	@Override
	@Transactional (readOnly = true)
	public List<TeamCandidate> getAllActiveDistinct(Long periodId) {
		return teamCandDao.getAllActiveDistinct(periodId);
	}
	
	@Override
	@Transactional
	public void saveTeamCandidate(Long periodId, Long userId, Long candId, String reasons) {
		
		TeamCandidate teamCandidate=teamCandDao.getActiveByUserId(userId, periodId);
		
		if(teamCandidate!=null){
			
			teamCandidate.setActive(0);
			teamCandDao.persist(teamCandidate);
		}
		
		TeamCandidate cand = new TeamCandidate();
		cand.setSelectedTeamId(candId);
		cand.setProposedByUserId(userId);
		cand.setReasons(reasons);
		cand.setPeriodId(periodId);
		cand.setActive(1);
		
		teamCandDao.persist(cand);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TeamCandidate> filterByNameAgencyProject(String filter, Long periodId,Long countryId) {
		return teamCandDao.filterByNameAgencyProject(filter, periodId,countryId);
	}
	
}