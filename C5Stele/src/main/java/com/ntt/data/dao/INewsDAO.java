package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.NewsArticle;

public interface INewsDAO extends IGenericDao<NewsArticle>{
	
	List<NewsArticle> getAllNews();

}
