package com.ntt.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IPeriodDao;
import com.ntt.data.model.Period;

@Repository
public class PeriodDao extends GenericDao<Period> implements IPeriodDao {

	@Override
	public Period getCurrentByCountry(Long countryId) {
		TypedQuery<Period> query = entityManager
				.createQuery("from Period where active=:active and countriesId=:country", Period.class)
				.setParameter("active", 1).setParameter("country", countryId);

		List<Period> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return null;
		}

		return resultList.get(0);
	}

	@Override
	public void invalidateAllPeriods() {
		TypedQuery<Period> query = entityManager.createQuery("from Period where active=1", Period.class);

		List<Period> resultList = query.getResultList();

		for (Period iterator : resultList) {
			iterator.setActive(0);
			persist(iterator);
		}
	}

	@Override
	public void invalidatePeriods(Long countryID) {
		TypedQuery<Period> query = entityManager
				.createQuery("from Period where active=1 and countries_id=:country_id", Period.class)
				.setParameter("country_id", countryID);

		if (query.getResultList().isEmpty() != true) {
			Period activePeriod = query.getSingleResult();
			activePeriod.setActive(0);
			persist(activePeriod);
		}
	}
}
