package com.ntt.data.dao.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.BaseAuditEntity;
import com.ntt.data.utils.SpringApplicationContext;

public class EntityListener {

	private static Logger logger = LoggerFactory.getLogger(EntityListener.class);
	
	@PrePersist
    @PreUpdate	
	public void beforePersis(BaseAuditEntity entity){
		
		logger.info("Update audit fields");
		
		SessionData bean = SpringApplicationContext.getBean(SessionData.class);
		
		entity.setLastModifiedAt(Date.valueOf(LocalDate.now()));
		if (bean.getLoggedUser() != null) {
			entity.setLastModifiedBy(bean.getLoggedUser().getFirstName());
		}
		
	}

	
}
