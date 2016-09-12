package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class ContactInformation extends BaseAuditEntity{
	
	@Column(name="location")
	private String site;
	
	@Column(name="address")
	private String address;
	
	@Column(name="telephone")
	private String phone;
	
	@Column(name="fax")
	private String fax;
	
	public ContactInformation() {
	}
	
	public ContactInformation(String site, String address, String phone, String fax) {
		this.site=site;
		this.address=address;
		this.phone=phone;
		this.fax=fax;
	}
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
}
