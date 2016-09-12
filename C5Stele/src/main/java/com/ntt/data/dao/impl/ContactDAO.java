package com.ntt.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IContactDAO;
import com.ntt.data.model.ContactInformation;
@Repository
public class ContactDAO extends GenericDao<ContactInformation> implements IContactDAO{


}
