package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.BaseEntity;

public interface IGenericDao <T extends BaseEntity> {
	
	T getById(Long id);
	
	T getByCode(String code);
	
	List<T> getAll();
	
	void delete(T value);
	
	void persist(T entity);

}
