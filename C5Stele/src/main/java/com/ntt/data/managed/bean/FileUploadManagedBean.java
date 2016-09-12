package com.ntt.data.managed.bean;

import java.io.File;
import java.util.Base64;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="fileUploadManagedBean")
@SessionScoped
public class FileUploadManagedBean {
	UploadedFile file;
	
	String typeOfImage;
	
	String imageContentsAsBase64;
	
	public String getTypeOfImage() {
		return typeOfImage;
	}

	public void setTypeOfImage(String typeOfImage) {
		this.typeOfImage = typeOfImage;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void fileUploadListener(FileUploadEvent e){
		this.file = e.getFile();
		this.typeOfImage=file.getContentType();
		this.imageContentsAsBase64 = Base64.getEncoder().encodeToString(file.getContents());
		
		System.out.println(file.getFileName());
	}
	
	public void fileUploadSimple(){
		if(file!=null){
		this.typeOfImage=file.getContentType();
		this.imageContentsAsBase64 = Base64.getEncoder().encodeToString(file.getContents());
		}
	}
	
	public String getImageContentsAsBase64() {
	    return imageContentsAsBase64;
	}

	public void setImageContentsAsBase64(String imageContentsAsBase64) {
		this.imageContentsAsBase64 = imageContentsAsBase64;
	}
}