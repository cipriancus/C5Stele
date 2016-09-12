package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.Role;

public interface IRoleService {

	boolean isAdmin(Long id);
	
	public List<Role> getAll();

}
