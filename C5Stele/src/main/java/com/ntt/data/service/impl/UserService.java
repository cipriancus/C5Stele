package com.ntt.data.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IUserDao;
import com.ntt.data.model.User;
import com.ntt.data.service.IUserService;

@Service(value = "userService")
public class UserService implements IUserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDao userDao;

	@Value("${prop.test}")
	private String test;
	
	@Autowired
	private PasswordEncoder encode;

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Long userId) {

		logger.info("get user");

		return userDao.getById(userId);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public boolean validateEmail(String email, boolean active) {
		if (userDao.validateEmail(email, active)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public User getUserByUsername(String username) {
		
		return userDao.getUserByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean emailExists(String email) {
		return userDao.emailExists(email);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.persist(user);

	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> getAllActiveEmployees() {
		return userDao.getAllActiveEmployees();
	}
	
	@Override
	public List<User> getAllEmployees() {
		return userDao.getAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> filterByNameOrAgency(String filter,Long countryID) {
		return userDao.filterByNameOrAgency(filter,countryID);
	}
	
	@Override
	@Transactional
	public User getUserByUID(String UID){
		return userDao.getUserByUID(UID);
	}
	
	@Override
	@Transactional
	public User getUserByForgotUID(String forgot_UID){
		return userDao.getUserByForgotUID(forgot_UID);
	}
	
	@Override
	@Transactional
	public List<String> getTitles() {
		return userDao.getTitles();
	}
	
	@Override
	@Transactional
	public void editInfo(Long id, String fname, String lname, String title, String phone, String email, Long agencyId, Long positionId, Long teamId) {
		User user = userDao.getById(id);
		
		if (user != null) {
			
			user.setFirstName(fname);
			user.setLastName(lname);
			user.setTitle(title);
			user.setPhoneNumber(phone);
			user.setEmail(email);
			user.setAgenciesId(agencyId);
			user.setPositionsId(positionId);
			user.setTeamsId(teamId);
			
			userDao.persist(user);
		}
	}
	
	@Override
	@Transactional
	public void editInfo(Long id,String username, String fname, String lname, String title, String phone, String email, Long agencyId, Long positionId, Long teamId, Long rolesId,boolean active ) {
		User user = userDao.getById(id);
		
		
		
		if (user != null) {
			
			user.setActive(active);
			user.setFirstName(fname);
			user.setLastName(lname);
			user.setTitle(title);
			user.setUsername(username);
			user.setPhoneNumber(phone);
			user.setEmail(email);
			user.setAgenciesId(agencyId);
			user.setPositionsId(positionId);
			user.setTeamsId(teamId);
			user.setRolesId(rolesId);
			user.setActive(active);
			
			userDao.persist(user);
		}
	}
	
	@Override
	@Transactional
	public boolean changePassword(Long id, String newPass,String oldPass) {
		User user = userDao.getById(id);
		
		if (user != null&&encode.matches(oldPass, user.getPassword())) {
			user.setPassword(encode.encode(newPass));
			
			userDao.persist(user);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public List<User> getUsersByCountry(Long countriesId) {
		return userDao.getUsersByCountry( countriesId) ;
	}


	@Override
	@Transactional
	public boolean existsUsername(String username) {
		return userDao.existsUsername(username);
	}
	
	@Override
	@Transactional
	public boolean existsUsername(String username,Long id) {
		return userDao.existsUsername(username,id);
	}


	@Override
	@Transactional
	public boolean emailExists(String email, Long id) {
		return userDao.emailExists(email,id);
	}

}
