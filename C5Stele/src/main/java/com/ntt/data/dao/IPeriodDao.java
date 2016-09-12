package com.ntt.data.dao;

import com.ntt.data.model.Period;

public interface IPeriodDao extends IGenericDao<Period>{

	Period getCurrentByCountry(Long countryId);

	void invalidateAllPeriods();

	void invalidatePeriods(Long countryID);
}
