package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.ITeamDao;
import com.ntt.data.model.Team;
import com.ntt.data.service.ITeamService;

@Service(value = "teamService")
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDao teamDao;
	
	@Override
	@Transactional(readOnly = true)
	public Team getTeamById(Long teamId) {

		return teamDao.getById(teamId);
	}
	
	@Override
	@Transactional
	public Team getTeamByName(String name){
		return teamDao.getTeamByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Team> getAll() {
		return teamDao.getAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Team> filterByNameAgencyProject(String filter,Long countryID) {
		return teamDao.filterByNameAgencyProject(filter,countryID);
	}
	
	@Override
	@Transactional
	public void save(Team team){
		teamDao.persist(team);
	}
	
	@Override
	@Transactional
	public void delete(Team team){
		teamDao.delete(team);
	}

	@Override
	@Transactional
	public List<Team> getTeamsForProjectID(Long id) {
		return teamDao.getTeamsForProjectID(id);
	}

	@Override
	@Transactional
	public List<Team> getAllfromCountry(Long countriesId) {
		return teamDao.getAllfromCountry( countriesId);
	}
	
}
