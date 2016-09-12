package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.User;

public interface IUserService {

	boolean existsUsername(String username);
	
	User getUserById(Long userId);
	
	User getUserByUsername(String username);

	boolean validateEmail(String email, boolean active);
	
	void saveUser(User user);
	
	User getUserByEmail(String email);

	List<User> getAllActiveEmployees();
	
	List<User> getAllEmployees();

	List<User> filterByNameOrAgency(String filter,Long countryID);

	User getUserByUID(String UID);

	List<String> getTitles();

	void editInfo(Long id, String fname, String lname, String title, String phone, String email, Long agencyId,
			Long positionId, Long teamId);

	boolean changePassword(Long id, String newPass,String oldPass);

	User getUserByForgotUID(String forgot_UID);

	void editInfo(Long id,String username, String fname, String lname, String title, String phone, String email, Long agencyId,
			Long positionId, Long teamId, Long rolesId,boolean active);

	List<User> getUsersByCountry(Long countriesId);

	boolean emailExists(String email);

	boolean existsUsername(String username, Long id);

	boolean emailExists(String email, Long id);
}
