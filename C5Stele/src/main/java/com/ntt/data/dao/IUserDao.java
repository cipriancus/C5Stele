package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.User;

public interface IUserDao extends IGenericDao<User>{

	User getUserByCredentials(String user, String pass);

	boolean validateEmail(String email, boolean active);
	
	User getUserByEmail(String Email);

	List<User> getAllActiveEmployees();
	
	List<User> filterByNameOrAgency(String filter,Long countryID);

	User getUserByUID(String UID);

	List<String> getTitles();

	User getUserByForgotUID(String forgot_UID);

	List<User> getUsersByCountry(Long countriesId);

	boolean emailExists(String email);

	User getUserByUsername(String username);
	
	public boolean existsUsername(String username);

	boolean existsUsername(String username, Long id);

	boolean emailExists(String email, Long id);
}
