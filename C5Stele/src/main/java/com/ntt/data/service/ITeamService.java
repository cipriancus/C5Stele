package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.Team;

public interface ITeamService {
	List<Team> getAll();

	List<Team> filterByNameAgencyProject(String filter,Long countryId);

	Team getTeamById(Long teamId);

	void save(Team team);

	void delete(Team team);

	Team getTeamByName(String name);

	List<Team> getTeamsForProjectID(Long id);

	List<Team> getAllfromCountry(Long countriesId);
}
