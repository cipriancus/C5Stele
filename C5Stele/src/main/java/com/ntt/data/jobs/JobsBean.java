package com.ntt.data.jobs;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntt.data.components.messages.MessageProducer;
import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.model.Country;
import com.ntt.data.model.Period;
import com.ntt.data.service.ICountryService;
import com.ntt.data.service.IPeriodService;
import com.ntt.data.service.ITeamVoteService;
import com.ntt.data.service.IVoteService;

@Component(value = "jobsBean")
public class JobsBean {

	private static Logger logger = LoggerFactory.getLogger(JobsBean.class);

	@Autowired
	IPeriodService periodService;

	@Autowired
	ICountryService countryService;

	@Autowired
	IVoteService voteService;
	
	@Autowired
	ITeamVoteService teamVoteService;

	@Autowired
	private MessageProducer mailProducer;

	/**
	 * Porneste la inceputul lunii: pe 01
	 * 
	 * trimitem mai in fiecare inceput de luna cu propunerile invalidare
	 * perioade precente si creare de noua perioada
	 * 
	 * @throws IOException
	 * 
	 */
	public void sendProposeMails() throws IOException {
		logger.info("Triggered the start process");

		EmailContent content = new EmailContent();

		content.setTo(getProperty("c5stele.emails"));// entire NTT
		content.setFrom("no-reply@nttdata.com");
		content.setSubject("Propunerile pentru Colegul de 5 Stele au inceput !");
		content.setModel(new HashMap<String, Object>());
		content.setTemplate("velocity/startProposeTemplate.vm");
		mailProducer.sendMessage(content);

		Period newPeriod = new Period();
		newPeriod.setActive(1);

		newPeriod.setLastRecommendationDay(
				Date.valueOf(LocalDate.now().plusDays(Long.parseLong(getProperty("c5stele.lastRecommendationDay")))));
		newPeriod.setLastVotingDay(
				Date.valueOf(LocalDate.now().plusDays(Long.parseLong(getProperty("c5stele.lastVotingDay")))));

		periodService.createNewPeriodAllCountries(newPeriod);// cream noi
																// perioade
																// pentru toate
																// tarile,invalidare
																// automata in
																// service
	}

	/**
	 * Porneste in fiecare zi la 3 dimineata si verifica
	 * 
	 * trimite mail ca a inceput perioada de voting verificand tarile
	 */
	public void sendVotingMail() {
		for (Country iterator : countryService.getAll()) {
			if (periodService.getCurrentByCountry(iterator.getId()).getLastRecommendationDay().toLocalDate()
					.getDayOfMonth() + 1 == LocalDate.now().getDayOfMonth()) {
				EmailContent content = new EmailContent();

				content.setTo(getProperty("c5stele.emails"));// entire NTT
				content.setFrom("no-reply@nttdata.com");
				content.setSubject("Votarea pentru Colegul de 5 Stele au inceput !");
				content.setModel(new HashMap<String, Object>());
				content.setTemplate("velocity/startVoteTemplate.vm");
				mailProducer.sendMessage(content);
			}
		}

	}

	/**
	 * Porneste in fiecare zi la 3 dimineata
	 * 
	 * Reaminteste utilizatorilor in ultimele 3 zile de voting sau recomandari
	 * sa voteze sau sa trimita recomandari
	 */
	public void remindUsers() {
		// iau din BD perioadele pentru toate tarile
		int zile = Integer.parseInt(getProperty("c5stele.daysBefore"));// luat
																		// din
																		// proprietati

		EmailContent content = new EmailContent();

		content.setTo(getProperty("c5stele.emails"));// entire NTT
		content.setFrom("no-reply@nttdata.com");

		for (Country iterator : countryService.getAll()) {
			// compare recomandarile si voturile si dau mail
			for (int numarZile = zile; numarZile >= 1; numarZile--) {
				if (periodService.getCurrentByCountry(iterator.getId()).getLastRecommendationDay().toLocalDate()
						.getDayOfMonth() - numarZile == LocalDate.now().getDayOfMonth()) {

					content.setSubject("Reamintire propuneri");

					Map<String, Object> model = new HashMap<String, Object>();
					model.put("days", numarZile);
					content.setModel(model);
					content.setTemplate("velocity/rememberRecommendation.vm");
					mailProducer.sendMessage(content);
					break;
				}
			}

			for (int numarZile = zile; numarZile >= 1; numarZile--) {
				if (periodService.getCurrentByCountry(iterator.getId()).getLastVotingDay().toLocalDate().getDayOfMonth()
						- numarZile == LocalDate.now().getDayOfMonth()) {
					content.setSubject("Reamintire votare");
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("days", numarZile);
					content.setModel(model);
					content.setTemplate("velocity/rememberVoting.vm");
					mailProducer.sendMessage(content);
					break;
				}
			}
		}
	}

	/**
	 * Send an email at the end of the voting period with the winners
	 */
	public void sendWinner() {
			for (Country iterator : countryService.getAll()) {//
				if (periodService.getCurrentByCountry(iterator.getId()).getLastVotingDay().toLocalDate().plusDays(1L)==LocalDate.now()) {
				EmailContent content = new EmailContent();

				content.setTo(getProperty("c5stele.emails"));// entire NTT
				content.setFrom("no-reply@nttdata.com");
				content.setSubject("Votarea pentru Colegul de 5 Stele au inceput !");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("$C5STELE_USER", voteService.getLastNWinners(1, iterator.getId()).get(0).getFirstName() + " "
						+ voteService.getLastNWinners(1, iterator.getId()).get(0).getLastName());
				model.put("$C5STELE_TEAM", teamVoteService.getLastNWinners(1, iterator.getId()).get(0).getTeamName());

				content.setModel(model);
				content.setTemplate("velocity/announceWinner.vm");
				mailProducer.sendMessage(content);
			}
		}
	}

	private String getProperty(String property) {
		Properties prop = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("c5stele.lastRecommendationDay");
	}
}
