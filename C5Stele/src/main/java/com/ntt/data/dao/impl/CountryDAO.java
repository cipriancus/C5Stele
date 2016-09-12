package com.ntt.data.dao.impl;

import javax.persistence.Entity;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.ICountryDAO;
import com.ntt.data.model.Country;

@Repository
public class CountryDAO extends GenericDao<Country> implements ICountryDAO{

}
