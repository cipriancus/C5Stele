package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.Faq;

public interface IFaqDao extends IGenericDao<Faq> {
	
	List<Faq> getAllOrdered();
	
}
