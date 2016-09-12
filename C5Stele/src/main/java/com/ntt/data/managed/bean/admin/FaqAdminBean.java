package com.ntt.data.managed.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.Faq;
import com.ntt.data.service.IFaqService;

@ManagedBean(name="faqAdminBean")
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class FaqAdminBean {

	@ManagedProperty(value="#{faqService}")
	IFaqService faqService;
	
	@ManagedProperty(value="#{sessionComponent}")
	SessionData sessionData;
	
	public IFaqService getFaqService() {
		return faqService;
	}

	public void setFaqService(IFaqService faqService) {
		this.faqService = faqService;
	}

	public SessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}

	public void postNewFAQ(String question,String answer) {
		if(question!=""&&answer!=""){
		Faq faq=new Faq();
		faq.setAnswer(answer);
		faq.setQuestion(question);
		faqService.saveFaq(faq);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The new FAQ tab has been inserted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
		}else{
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot insert empty data","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			extContext.getFlash().setKeepMessages(true);
		}
	}
	
	public void updateFAQ(Long id, String question, String answer){
		Faq faq=new Faq();
		faq.setAnswer(answer);
		faq.setQuestion(question);
		faq.setId(id);
		faqService.saveFaq(faq);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The FAQ tab has been edited successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
	
	public void deleteFAQ(Long id){
		Faq faq=faqService.getFaqById(id);
		faqService.deleteFaq(faq);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The FAQ tab has been deleted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
}
