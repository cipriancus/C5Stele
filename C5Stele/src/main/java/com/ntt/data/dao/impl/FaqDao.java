package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IFaqDao;
import com.ntt.data.model.Faq;

@Repository
public class FaqDao extends GenericDao<Faq> implements IFaqDao {
	
	public List<Faq> getAllOrdered() {
	
		TypedQuery<Faq> query = entityManager.createQuery("from Faq order by order_of_questions", Faq.class);
		
		return (List<Faq>) query.getResultList();
	}

}
