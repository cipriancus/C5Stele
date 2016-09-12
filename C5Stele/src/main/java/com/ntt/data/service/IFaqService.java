package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.Faq;

public interface IFaqService {

	List<Faq> getAllOrdered();

	void saveFaq(Faq faq);
	
	void deleteFaq(Faq faq);
	
	Faq getFaqById(Long id);
}
