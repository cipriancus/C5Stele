package com.ntt.data.managed.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.NewsArticle;
import com.ntt.data.service.INewsService;

@ManagedBean(name = "newsAdminBean")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class NewsAdminBean {

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	@ManagedProperty(value = "#{newsService}")
	private INewsService newsService;

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	public void postNews(String title, String content) {
		if(title!=""&&content!=""){
		NewsArticle newsArticle = new NewsArticle();
		newsArticle.setMessage(content);
		newsArticle.setTitle(title);
		newsService.saveNews(newsArticle);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The new News tab has been inserted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
		}else{
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot insert empty data","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			extContext.getFlash().setKeepMessages(true);
		}
	}
	
	public void updateNews(Long id, String title, String content){
		NewsArticle newsArticle = new NewsArticle();
		newsArticle.setMessage(content);
		newsArticle.setTitle(title);
		newsArticle.setId(id);
		newsService.saveNews(newsArticle);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The news tab has been edited successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
	
	public void deleteNews(Long id){
		NewsArticle newsArticle=newsService.getNewsById(id);
		newsService.delete(newsArticle);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The News tab has been deleted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
}
