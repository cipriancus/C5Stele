package com.ntt.data.managed.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.ContactInformation;
import com.ntt.data.service.IContactService;

@ManagedBean(name="contactAdminBean")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class ContactAdminBean{

	@ManagedProperty(value="#{contactService}")
	IContactService contactService;
	
	@ManagedProperty(value="#{sessionComponent}")
	SessionData sessionData;
	
	public IContactService getContactService() {
		return contactService;
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

	public SessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}

	public void postNewContact(String locationOfOffice,String addressOfOffice,String telephoneOfOffice,String faxOfOffice) {
		if(locationOfOffice!=""&&addressOfOffice!=""&&telephoneOfOffice!=""&&faxOfOffice!=""){
		ContactInformation contactInformation=new ContactInformation(locationOfOffice, addressOfOffice, telephoneOfOffice, faxOfOffice);
		contactService.saveContact(contactInformation);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The new Contact tab has been inserted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
		}else{
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot insert empty data","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			extContext.getFlash().setKeepMessages(true);
		}
	}
	
	public void editContact(Long id,String locationOfOffice,String addressOfOffice,String telephoneOfOffice,String faxOfOffice) {
		ContactInformation contactInformation=new ContactInformation(locationOfOffice, addressOfOffice, telephoneOfOffice, faxOfOffice);
		contactInformation.setId(id);
		contactService.saveContact(contactInformation);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The Contact tab has been edited successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
	
	public void deleteContact(Long id) {
		ContactInformation contactInformation=contactService.getContactById(id);
		contactService.delete(contactInformation);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The Contact tab has been deleted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
}
