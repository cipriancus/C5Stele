package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IAboutDAO;
import com.ntt.data.model.AboutTab;
import com.ntt.data.service.IAboutService;

@Service(value = "aboutService")
public class AboutService implements IAboutService{

	@Autowired
	IAboutDAO aboutDAO;
		
	@Transactional(readOnly=true)
	@Override
	public List<AboutTab> getAllAbout() {
		return aboutDAO.getAll();
	}

	@Transactional
	@Override
	public void saveAboutTab(AboutTab aboutTab) {
		aboutDAO.persist(aboutTab);
	}

	@Transactional
	@Override
	public void deleteAboutTab(AboutTab aboutTab){
		aboutDAO.delete(aboutTab);
	}

	@Override
	@Transactional
	public AboutTab getAboutTabById(Long id) {
		return aboutDAO.getById(id);
	}
}
