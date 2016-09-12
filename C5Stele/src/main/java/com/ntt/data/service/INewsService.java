package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.NewsArticle;

public interface INewsService {
	
	List<NewsArticle> getAllNews();

	void saveNews(NewsArticle newsArticle);
	
	void delete(NewsArticle newsArticle);
	
	NewsArticle getNewsById(Long id);
}
