package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class NewsArticle extends BaseAuditEntity{

	@Column(name="TITLE")
	private String title;
	
	@Column(name="MESSAGE")
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
