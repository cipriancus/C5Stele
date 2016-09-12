package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.Team;

public interface ITeamDao extends IGenericDao<Team>{

	List<Team> filterByNameAgencyProject(String filter,Long countryID);

	Team getTeamByName(String name);

	List<Team> getTeamsForProjectID(Long id);

	List<Team> getAllfromCountry(Long countrieId);
}
