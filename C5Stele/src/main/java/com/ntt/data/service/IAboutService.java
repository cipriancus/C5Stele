package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.AboutTab;

public interface IAboutService {
	
	List<AboutTab> getAllAbout();
	
	void saveAboutTab(AboutTab aboutTab);

	void deleteAboutTab(AboutTab aboutTab);
	
	AboutTab getAboutTabById(Long id);
}
