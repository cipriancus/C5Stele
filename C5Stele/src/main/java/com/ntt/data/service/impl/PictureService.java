package com.ntt.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IPictureDAO;
import com.ntt.data.model.Picture;
import com.ntt.data.model.User;
import com.ntt.data.service.IPictureService;

@Service(value="pictureService")
public class PictureService implements IPictureService{

	@Autowired
	private IPictureDAO pictureDAO;
	
	@Override
	@Transactional
	public void savePicture(Picture picture) {
		pictureDAO.persist(picture);
	}
	
	@Override
	@Transactional
	public void deletePicture(User user) {
		pictureDAO.delete(pictureDAO.getPictureForUser(user));
	}

	@Override
	@Transactional
	public Picture getPictureForUser(User user) {
		return pictureDAO.getPictureForUser(user);
	}

}
