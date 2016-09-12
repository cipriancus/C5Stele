package com.ntt.data.service;

import com.ntt.data.model.Period;

public interface IPeriodService {

	Period getCurrentByCountry(Long country);

	void createNewPeriodAllCountries(Period period);

	void invalidateAllPeriods();

	void invalidatePeriod(Long countryID);

	void createNewPeriod(Period period);

}
