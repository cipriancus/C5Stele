package com.ntt.data.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ntt.data.dao.IUserDao;
import com.ntt.data.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private IUserDao userDao;
	
	@InjectMocks
	UserService service;
	
	@Test
	public void getUserByIdTest(){
		
		Long userId = 1l;
		User user = new User();
		Mockito.when(userDao.getById(userId)).thenReturn(user);
		
		User userById = service.getUserById(userId);
		
		Assert.assertNotNull(userById);
		
		Mockito.verify(userDao, Mockito.times(1)).getById(userId);
		
		
	}
	
}
