package com.ntt.data.components.utils;

import java.io.Serializable;
import java.util.Map;

import org.springframework.core.io.Resource;

/**
 * a bean that contains an email content
 * 
 * @author bernard.ciurariu
 *
 */
public class EmailContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6750163920739521626L;

	private String subject;

	private String body;

	private String from;

	private String to;

	private Map<String, Resource> resources;

	private String template;

	private Map<String, Object> model;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Map<String, Resource> getResources() {
		return resources;
	}

	public void setResources(Map<String, Resource> resources) {
		this.resources = resources;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "EmailContent [subject=" + subject + ", body=" + body + ", from=" + from + ", to=" + to + ", resources="
				+ resources + "]";
	}

}
