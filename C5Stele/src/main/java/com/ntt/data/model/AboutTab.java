package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="about")
public class AboutTab extends BaseAuditEntity{
	
	@Column(name="NAME")
	String name;
	
	@Column(name="TEXT")
	String text;
	
	public AboutTab() {
		// TODO Auto-generated constructor stub
	}
	
	public AboutTab(String name, String text){
		this.name=name;
		this.text=text;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
