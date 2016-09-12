package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IPictureDAO;
import com.ntt.data.model.Picture;
import com.ntt.data.model.User;

@Repository 
public class PictureDAO extends GenericDao<Picture> implements IPictureDAO{

	@Override
	public Picture getPictureForUser(User user) {
		TypedQuery<Picture> query=entityManager.createQuery("from Picture where ID_OF_USER=:user_id", Picture.class);
		query.setParameter("user_id", user.getId());
		
		List<Picture> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}

}
