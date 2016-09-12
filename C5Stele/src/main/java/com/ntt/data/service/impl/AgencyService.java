package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IAgencyDao;
import com.ntt.data.model.Agency;
import com.ntt.data.service.IAgencyService;

@Service(value = "agencyService")
public class AgencyService implements IAgencyService {

	@Autowired
	private IAgencyDao agencyDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getCities() {
		return agencyDao.getCities();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Agency> getAll() {
		return agencyDao.getAll();
	}

}
