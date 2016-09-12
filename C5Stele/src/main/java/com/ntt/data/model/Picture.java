package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pictures")
public class Picture extends BaseAuditEntity {
	@Column(name = "TYPE_OF_IMAGE")
	private String typeOfImage;
	@Column(name="IMAGE")
	private String base64Image;
	@Column(name="ID_OF_USER")
	private Long id_of_user;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OF_USER", unique = true, nullable = true, insertable = false, updatable = false)
	private User user;
	
	public String getTypeOfImage() {
		return typeOfImage;
	}
	public void setTypeOfImage(String typeOfImage) {
		this.typeOfImage = typeOfImage;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public Long getId_of_user() {
		return id_of_user;
	}
	public void setId_of_user(Long id_of_user) {
		this.id_of_user = id_of_user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
