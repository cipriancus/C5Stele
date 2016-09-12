package com.ntt.data.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ntt.data.dao.IGenericDao;
import com.ntt.data.model.BaseEntity;

public class GenericDao <T extends BaseEntity> implements IGenericDao<T> {

	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public T getById(Long id) {
		
		String enityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
		Query query = entityManager.createQuery("from " + enityName + " where id=:entityId");
		query.setParameter("entityId", id);
		
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		
		if (list.isEmpty()) {
			return null;
		}
		return (T)list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getByCode(String code) {
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
		Query query = entityManager.createQuery("from " + entityName + " where code=:entityCode");
		query.setParameter("entityCode", code);
		
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		
		if (list.isEmpty()) {
			return null;
		}
		return (T)list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
		Query query = entityManager.createQuery("from " + entityName);
		return (List<T>) query.getResultList();
	}

	@Override
	public void delete(T value) {
		entityManager.remove(entityManager.merge(value));
	}
	
	@Override
	public void persist(T entity) {
		entityManager.merge(entity);
	}
}
