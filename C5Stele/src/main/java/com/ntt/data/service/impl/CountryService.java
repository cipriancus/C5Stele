package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.data.dao.ICountryDAO;
import com.ntt.data.dao.impl.GenericDao;
import com.ntt.data.model.Country;
import com.ntt.data.service.ICountryService;

@Service(value="countryService")
public class CountryService extends GenericDao<Country> implements ICountryService{
	
	@Autowired
	ICountryDAO countryDAO;
	
	public List<Country> getAll(){
		return countryDAO.getAll();
	}
}
