package com.ntt.data.managed.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ntt.data.model.Faq;
import com.ntt.data.service.IFaqService;

@ManagedBean(name = "faqBean")
@ViewScoped
public class FaqBean {
	
	private List<Faq> data;
	
	@ManagedProperty(value="#{faqService}")
	private IFaqService faqService;

	public IFaqService getFaqService() {
		return faqService;
	}

	public void setFaqService(IFaqService faqService) {
		this.faqService = faqService;
	}

	public void setData(List<Faq> data) {
		this.data = data;
	}
	
	public List<Faq> getData() {
		data = faqService.getAllOrdered();
		return data;
	}
}
