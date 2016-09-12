package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IContactDAO;
import com.ntt.data.model.ContactInformation;
import com.ntt.data.service.IContactService;

@Service(value="contactService")
public class ContactService implements IContactService{

	@Autowired
	IContactDAO contactDAO;
	
	@Override
	@Transactional
	public List<ContactInformation> getAllContacts() {
		return contactDAO.getAll();
	}

	@Override
	@Transactional
	public void saveContact(ContactInformation contactInformation) {
		contactDAO.persist(contactInformation);
	}

	@Transactional
	@Override
	public ContactInformation getContactById(Long Id) {
		return contactDAO.getById(Id);
	}

	@Transactional
	@Override
	public void delete(ContactInformation contactInformation) {
		contactDAO.delete(contactInformation);
	}
}
