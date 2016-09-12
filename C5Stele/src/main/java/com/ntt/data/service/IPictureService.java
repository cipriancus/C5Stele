package com.ntt.data.service;

import com.ntt.data.model.Picture;
import com.ntt.data.model.User;

public interface IPictureService {
	void savePicture(Picture picture);

	Picture getPictureForUser(User user);

	void deletePicture(User user);
}
