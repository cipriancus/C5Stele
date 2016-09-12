package com.ntt.data.managed.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.model.Country;
import com.ntt.data.service.ICountryService;

@ManagedBean
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class ChangePeriodAdminBean {

	@ManagedProperty(value = "#{countryService}")
	private ICountryService countryService;
	
	private List<SelectItem> allCountry;
	

	
	public ICountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}

	public List<SelectItem> getAllCountry() {
		allCountry= new ArrayList<SelectItem>();
		for(Country iterator: countryService.getAll())
			allCountry.add(new SelectItem(iterator.getId().toString(),iterator.getName()));
		
		return allCountry;
			
	}

	public void setAllCountry(List<SelectItem> allCountry) {
		this.allCountry = allCountry;
	}
	
	
	
}
