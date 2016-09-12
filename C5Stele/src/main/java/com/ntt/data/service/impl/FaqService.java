package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IFaqDao;
import com.ntt.data.model.Faq;
import com.ntt.data.service.IFaqService;

@Service(value = "faqService")
public class FaqService implements IFaqService {

	@Autowired
	private IFaqDao fDao;

	@Override
	@Transactional(readOnly = true)
	public List<Faq> getAllOrdered() {
		return fDao.getAllOrdered();
	}

	@Override
	@Transactional
	public void saveFaq(Faq faq) {
		fDao.persist(faq);
	}

	@Override
	@Transactional
	public void deleteFaq(Faq faq) {
		fDao.delete(faq);
	}

	@Override
	@Transactional
	public Faq getFaqById(Long id) {
		return fDao.getById(id);
	}

	
}
