package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.data.dao.IRoleDao;
import com.ntt.data.model.Role;
import com.ntt.data.service.IRoleService;

@Service(value="roleService")
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	public boolean isAdmin(Long id) {
		return roleDao.getById(id).getRoleType().equalsIgnoreCase("admin");
	}

	@Override
	@Transactional
	public List<Role> getAll() {
		return roleDao.getAll();
	}
	
	

}
