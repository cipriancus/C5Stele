package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.INewsDAO;
import com.ntt.data.model.NewsArticle;
import com.ntt.data.service.INewsService;

@Service(value="newsService")
public class NewsService implements INewsService{

	@Autowired
	INewsDAO newsDAO;
	
	@Override
	@Transactional
	public List<NewsArticle> getAllNews() {
		return newsDAO.getAllNews();
	}

	@Override
	@Transactional
	public void saveNews(NewsArticle newsArticle) {
		newsDAO.persist(newsArticle);
	}

	@Override
	@Transactional
	public void delete(NewsArticle newsArticle) {
		newsDAO.delete(newsArticle);
	}

	@Override
	@Transactional
	public NewsArticle getNewsById(Long id) {
		return newsDAO.getById(id);
	}

}
