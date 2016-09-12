package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Positions")
public class Position extends BaseEntity {
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DEPARTMENTS_ID")
	private Long departmentsId;

	@Column(name = "CODE_MESSAGE_ID")
	private String codeMessageId;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDepartmentsId() {
		return departmentsId;
	}

	public void setDepartmentsId(Long departmentsId) {
		this.departmentsId = departmentsId;
	}

	public String getCodeMessageId() {
		return codeMessageId;
	}

	public void setCodeMessageId(String codeMessageId) {
		this.codeMessageId = codeMessageId;
	}

	@Override
	public String toString() {
		return "Position [code=" + code + ", description=" + description + ", departmentsId=" + departmentsId
				+ ", codeMessageId=" + codeMessageId + "]";
	}
	
}
