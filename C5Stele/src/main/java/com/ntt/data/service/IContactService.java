package com.ntt.data.service;

import java.util.List;

import com.ntt.data.model.ContactInformation;

public interface IContactService {
	List<ContactInformation> getAllContacts();
	
	void saveContact(ContactInformation contactInformation);

	ContactInformation getContactById(Long Id);
	
	void delete(ContactInformation contactInformation);
}
