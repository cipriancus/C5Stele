package com.ntt.data.managed.bean.admin;

import java.sql.Date;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.model.Period;
import com.ntt.data.service.IPeriodService;

@ManagedBean(name="periodsAdminBean")
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class PeriodsAdminBean {

	private Long selectedCountryId;
	
	private Integer newLastRecommendationDay;
	private Integer newLastVotingDay;
	
	private String lastRecommendationDay;
	private String lastVotingDay;
	
	@ManagedProperty(value = "#{periodService}")
	private IPeriodService periodService;
	
	
	public void submitNewPeriod() {
		Period period=periodService.getCurrentByCountry(selectedCountryId);
		period.setLastRecommendationDay(Date.valueOf(LocalDate.now().withDayOfMonth(newLastRecommendationDay)));
		period.setLastVotingDay(Date.valueOf(LocalDate.now().withDayOfMonth(newLastVotingDay)));

		periodService.createNewPeriod(period);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your edit was successful!",  "");
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
		
	public Integer setNumberOfDays() {

		Integer numberDays = 0;
        switch(LocalDate.now().getMonthValue()){
            case 1:
                numberDays=31;
                break;
            case 2:
                if(LocalDate.now().getYear()%4==0)
                	numberDays=29;
                else
                	numberDays=28;
                break;
            case 3:
            	numberDays=31;
                break;
            case 4:
            	numberDays=30;
                break;
            case 5:
            	numberDays=31;
                break;
            case 6:
            	numberDays=30;
                break;
            case 7:
            	numberDays=31;
                break;
            case 8:
            	numberDays=31;
                break;
            case 9:
            	numberDays=30;
                break;
            case 10:
            	numberDays=31;
                break;
            case 11:
            	numberDays=30;
                break;
            case 12:
            	numberDays=31;
                break;
                
        } 
        return numberDays;
    }

	public IPeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(IPeriodService periodService) {
		this.periodService = periodService;
	}

	public String getLastRecommendationDay(){
		lastRecommendationDay=periodService.getCurrentByCountry(selectedCountryId).getLastRecommendationDay().toString();
		return lastRecommendationDay;
	}
	
	public String getLastVotingDay(){
		lastVotingDay=periodService.getCurrentByCountry(selectedCountryId).getLastVotingDay().toString();
		return lastVotingDay;
	}

	public void setLastRecommendationDay(String lastRecommendationDay) {
		this.lastRecommendationDay = lastRecommendationDay;
	}

	public void setLastVotingDay(String lastVotingDay) {
		this.lastVotingDay = lastVotingDay;
	}

	public PeriodsAdminBean() {
		selectedCountryId=1L;
	}
	
	public Long getSelectedCountryId() {
		return selectedCountryId;
	}

	public void setSelectedCountryId(Long selectedCountryId) {
		this.selectedCountryId = selectedCountryId;
	}

	public Integer getNewLastRecommendationDay() {
		this.newLastRecommendationDay=periodService.getCurrentByCountry(selectedCountryId).getLastRecommendationDay().toLocalDate().getDayOfMonth();
		return newLastRecommendationDay;
	}

	public void setNewLastRecommendationDay(Integer newLastRecommendationDay) {
		this.newLastRecommendationDay = newLastRecommendationDay;
	}

	public Integer getNewLastVotingDay() {
		this.newLastVotingDay=periodService.getCurrentByCountry(selectedCountryId).getLastVotingDay().toLocalDate().getDayOfMonth();
		return newLastVotingDay;
	}

	public void setNewLastVotingDay(Integer newLastVotingDay) {
		this.newLastVotingDay = newLastVotingDay;
	}

}
