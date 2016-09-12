package com.ntt.data.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IUserDao;
import com.ntt.data.model.User;

@Repository
public class UserDao extends GenericDao<User> implements IUserDao {

	@Override
	public User getUserByCredentials(String user, String pass) {
		TypedQuery<User> query = entityManager.createQuery("from User where username=:user and password=:pass", User.class)
				.setParameter("user", user)
				.setParameter("pass", pass);

		List<User> resultList = query.getResultList();

		if (resultList.isEmpty()) {
			return null;
		}

		return resultList.get(0);
	}
	
	

	@Override
	public User getUserByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("from User where username=:user", User.class)
				.setParameter("user", username);

		List<User> resultList = query.getResultList();

		if (resultList.isEmpty()) {
			return null;
		}

		return resultList.get(0);
	}



	@Override
	public boolean validateEmail(String email, boolean active) {
		TypedQuery<Long> query = entityManager
				.createQuery("select count(user.id) from User user where email=:email and active=:isActive", Long.class)
				.setParameter("email", email).setParameter("isActive", active);

		return query.getSingleResult().intValue() == 1 ? true : false;
	}
	
	@Override
	public boolean emailExists(String email) {
		TypedQuery<Long> query = entityManager
				.createQuery("select count(user.id) from User user where email=:email", Long.class)
				.setParameter("email", email);

		return query.getSingleResult().intValue() == 1 ? true : false;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		TypedQuery<User> query = entityManager.createQuery("from User where email=:userEmail", User.class)
				.setParameter("userEmail", userEmail);
		
		if(query.getResultList().isEmpty())
			return null;
		
		return query.getResultList().get(0);
	}

	@Override
	public List<User> getAllActiveEmployees() {
		TypedQuery<User> query = entityManager.createQuery(
				"from User where rolesId not in (select id from Role where roleType = :role) and active = 1",
				User.class).setParameter("role", "admin");
		return query.getResultList();
	}

	@Override
	public List<User> filterByNameOrAgency(String filter,Long countryID) {
		TypedQuery<User> query = entityManager.createQuery(
				"select u from User as u INNER JOIN u.agency as a where a.countriesId=:country_id and u.rolesId not in (select id from Role where roleType = :role) AND (u.firstName LIKE :filter OR u.lastName LIKE :filter OR (select a.city from Agency as a where a.id = u.agenciesId) LIKE :filter)",
				User.class).setParameter("role", "admin").setParameter("filter", '%' + filter + '%').setParameter("country_id", countryID);
		return query.getResultList();
	}

	@Override
	public User getUserByUID(String UID) {
		TypedQuery<User> query = entityManager.createQuery("from User where UID=:user_uid", User.class)
				.setParameter("user_uid", UID);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<String> getTitles() {

		List<String> list = entityManager.createQuery("select distinct u.title from User u", String.class)
				.getResultList();

		if (list.isEmpty())
			return null;
		return list;
	}

	@Override
	public User getUserByForgotUID(String forgot_UID) {
		TypedQuery<User> query = entityManager.createQuery("from User u where u.forgot_uid=:user_forgot_uid", User.class)
				.setParameter("user_forgot_uid", forgot_UID);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<User> getUsersByCountry(Long countriesId) {

		TypedQuery<User> query = entityManager.createQuery("select u From User as u INNER JOIN u.agency as a where a.countriesId=:country_id and u.rolesId!=1", User.class)
				.setParameter("country_id", countriesId);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return null;
		return list;
	}



	@Override
	public boolean existsUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("select u From User as u where u.username=:id_username", User.class)
				.setParameter("id_username", username);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return false;
		
		return true;
	}
	
	@Override
	public boolean existsUsername(String username, Long id) {
		TypedQuery<User> query = entityManager.createQuery("select u From User as u where u.username=:id_username and u.id!=:user_id", User.class)
				.setParameter("id_username", username).setParameter("user_id", id);

		List<User> list = query.getResultList();

		if (list.isEmpty())
			return false;
		
		return true;
	}
	
	@Override
	public boolean emailExists(String email, Long id) {
		TypedQuery<Long> query = entityManager
				.createQuery("select count(user.id) from User user where email=:email and user.id!=:user_id", Long.class)
				.setParameter("email", email).setParameter("user_id", id);

		return query.getSingleResult().intValue() == 1 ? true : false;
	}
}
