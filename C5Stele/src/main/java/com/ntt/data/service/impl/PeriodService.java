package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IPeriodDao;
import com.ntt.data.model.Country;
import com.ntt.data.model.Period;
import com.ntt.data.service.ICountryService;
import com.ntt.data.service.IPeriodService;

@Service(value = "periodService")
public class PeriodService implements IPeriodService {

	@Autowired
	private IPeriodDao periodDao;
	
	@Autowired
	private ICountryService countryService;
	
	@Override
	@Transactional
	public Period getCurrentByCountry(Long country) {
		return periodDao.getCurrentByCountry(country);
	}
	
	@Override
	@Transactional
	public void invalidateAllPeriods(){
		periodDao.invalidateAllPeriods();
	}
	
	@Override
	@Transactional
	public void invalidatePeriod(Long countryID){
		periodDao.invalidatePeriods(countryID);
	}

	@Override
	@Transactional
	public void createNewPeriodAllCountries(Period period){
		invalidateAllPeriods();
		
		List<Country> allCountries=countryService.getAll();
		
		for(Country iterator:allCountries){
			period.setCountriesId(iterator.getId());
			createNewPeriod(period);
		}
	}
	
	@Override
	@Transactional
	public void createNewPeriod(Period period){
		periodDao.persist(period);
	}
}
