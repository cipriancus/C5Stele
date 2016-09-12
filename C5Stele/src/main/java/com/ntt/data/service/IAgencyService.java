package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.Agency;

public interface IAgencyService {

	List<String> getCities();

	List<Agency> getAll();

}
