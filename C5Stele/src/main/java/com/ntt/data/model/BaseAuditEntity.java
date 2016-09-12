package com.ntt.data.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.ntt.data.dao.util.EntityListener;

@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseAuditEntity extends BaseEntity {
	
	@Column(name = "LAST_MODIFIED_AT")
	private Date lastModifiedAt;
	
	@Column(name = "LAST_MODIFIED_BY")
	private String lastModifiedBy;
	
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
