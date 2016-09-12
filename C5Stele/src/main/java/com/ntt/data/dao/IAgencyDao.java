package com.ntt.data.dao;

import java.util.List;

import com.ntt.data.model.Agency;

public interface IAgencyDao extends IGenericDao<Agency> {

	List<String> getCities();

}
