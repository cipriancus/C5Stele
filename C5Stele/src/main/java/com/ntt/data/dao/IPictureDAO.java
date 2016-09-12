package com.ntt.data.dao;

import org.springframework.stereotype.Repository;

import com.ntt.data.model.Picture;
import com.ntt.data.model.User;

@Repository
public interface IPictureDAO extends IGenericDao<Picture>
{
	Picture getPictureForUser(User user);
}
