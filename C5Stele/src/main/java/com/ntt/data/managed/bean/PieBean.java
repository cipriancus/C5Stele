package com.ntt.data.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "pieBean")
@ViewScoped
public class PieBean implements Serializable {

	private static final long serialVersionUID = 7782267785663845894L;
	private PieChartModel pieModelUser;
	private PieChartModel pieModelTeam;

	@PostConstruct
	public void init() {
		pieModelUser = new PieChartModel();
		pieModelUser.setLegendPosition("w");
		pieModelUser.setShowDataLabels(true);
		pieModelUser.setSeriesColors("ccffff, 00e6e6, 0066ff, 00ccff");

		pieModelTeam = new PieChartModel();
		pieModelTeam.setLegendPosition("w");
		pieModelTeam.setShowDataLabels(true);
		pieModelTeam.setSeriesColors("00ccff, 0066ff, 00e6e6, ccffff");
	}

	public PieChartModel getPieModelUser() {
		return pieModelUser;
	}

	public void setUser(String name, Number votes) {
		this.pieModelUser.set(name, votes);
	}

	public void setTitleUser(String title) {
		this.pieModelUser.setTitle(title);
	}

	public PieChartModel getPieModelTeam() {
		return pieModelTeam;
	}

	public void setTeam(String name, Number votes) {
		this.pieModelTeam.set(name, votes);
	}

	public void setTitleTeam(String title) {
		this.pieModelTeam.setTitle(title);
	}

	public void resetUser() {
		pieModelUser = new PieChartModel();
		pieModelUser.setLegendPosition("w");
		pieModelUser.setShowDataLabels(true);
		pieModelUser.setSeriesColors("ccffff, 00e6e6, 0066ff, 00ccff");
	}
	
	public void resetTeam() {
		pieModelTeam = new PieChartModel();
		pieModelTeam.setLegendPosition("w");
		pieModelTeam.setShowDataLabels(true);
		pieModelTeam.setSeriesColors("00ccff, 0066ff, 00e6e6, ccffff");	}
}
