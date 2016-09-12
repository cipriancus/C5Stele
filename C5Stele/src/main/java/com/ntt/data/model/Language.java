package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Languages")
public class Language extends BaseEntity {

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CODE")
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Language [name=" + name + ", code=" + code + "]";
	}
}
