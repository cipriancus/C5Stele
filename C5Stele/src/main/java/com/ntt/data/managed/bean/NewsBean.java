package com.ntt.data.managed.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.ntt.data.model.NewsArticle;
import com.ntt.data.service.INewsService;

@ManagedBean(name="newsBean")
public class NewsBean {

	private List<NewsArticle> data;
	
	@ManagedProperty(value="#{newsService}")
	INewsService newsService;

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	public List<NewsArticle> getData() {
		data=newsService.getAllNews();
		return data;
	}

	public void setData(List<NewsArticle> data) {
		this.data = data;
	}
}
