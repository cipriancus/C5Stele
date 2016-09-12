package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.INewsDAO;
import com.ntt.data.model.NewsArticle;

@Repository
public class NewsDAO extends GenericDao<NewsArticle> implements INewsDAO {

	@Override
	public List<NewsArticle> getAllNews() {
		TypedQuery<NewsArticle> createQuery = entityManager.createQuery("from NewsArticle order by LAST_MODIFIED_AT DESC", NewsArticle.class);
		return  createQuery.getResultList();
	}

}
