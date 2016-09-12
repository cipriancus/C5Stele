package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.ICandidateDao;
import com.ntt.data.model.Candidate;
import com.ntt.data.service.ICandidateService;

@Service(value = "candService")
public class CandidateService implements ICandidateService {

	@Autowired
	private ICandidateDao candDao;
	
	@Override
	@Transactional
	public List<Candidate> getAllActiveDistinct(Long periodId) {
		return candDao.getAllActiveDistinct(periodId);
	}
	
	@Override
	@Transactional
	public void saveCandidate(Long periodId, Long userId, Long candId, String reasons) {
		
		Candidate candidate = candDao.getActiveByUserId(userId, periodId);
		
		if (candidate != null) {
			
			candidate.setActive(0);
			
			candDao.persist(candidate);
		}
		
		Candidate cand = new Candidate();
		cand.setSelectedUserId(candId);
		cand.setProposedByUserId(userId);
		cand.setReasons(reasons);
		cand.setPeriodId(periodId);
		cand.setActive(1);
		
		
		candDao.persist(cand);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Candidate> filterByNameOrAgency(String filter, Long periodId,Long countryID) {
		return candDao.filterByNameOrAgency(filter, periodId,countryID);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getAllReasonsForUser(Long userId, Long periodId) {
		return candDao.getAllReasonsForUser(userId, periodId);
	}
}
