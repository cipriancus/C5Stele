package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IAgencyDao;
import com.ntt.data.model.Agency;

@Repository
public class AgencyDao extends GenericDao<Agency> implements IAgencyDao {

	@Override
	public List<String> getCities() {
		TypedQuery<String> query = entityManager.createQuery("select City from Agency", String.class);
		
		
		return  query.getResultList();
	}
}
