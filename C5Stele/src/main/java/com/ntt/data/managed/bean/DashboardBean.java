package com.ntt.data.managed.bean;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.Candidate;
import com.ntt.data.model.Period;
import com.ntt.data.model.Picture;
import com.ntt.data.model.Team;
import com.ntt.data.model.TeamCandidate;
import com.ntt.data.model.TeamWinnerDTO;
import com.ntt.data.model.TopCandidateDTO;
import com.ntt.data.model.TopTeamDTO;
import com.ntt.data.model.User;
import com.ntt.data.model.WinnerDTO;
import com.ntt.data.service.ICandidateService;
import com.ntt.data.service.IPeriodService;
import com.ntt.data.service.IPictureService;
import com.ntt.data.service.IRoleService;
import com.ntt.data.service.ITeamCandidateService;
import com.ntt.data.service.ITeamService;
import com.ntt.data.service.ITeamVoteService;
import com.ntt.data.service.IUserService;
import com.ntt.data.service.IVoteService;
import org.springframework.security.access.prepost.PreAuthorize;

@ManagedBean
@ViewScoped
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

public class DashboardBean {

	private List<User> users;
	private List<Team> teams;

	private List<Candidate> candidates;
	private List<TeamCandidate> teamCandidates;

	private User selectedUser;
	private Team selectedTeam;

	private Candidate selectedCandidate;
	private TeamCandidate selectedTeamCandidate;

	private User chosenUser;
	private Team chosenTeam;

	private Candidate chosenCandidate;
	private TeamCandidate chosenTeamCandidate;

	private String userReason;
	private String teamReason;

	private String userFilter;
	private String teamFilter;

	private String userVoteFilter;
	private String teamVoteFilter;

	private Period period;

	private Picture picture;

	private List<WinnerDTO> winners;
	private Integer noOfWinners = 5;

	private List<TeamWinnerDTO> teamWinners;
	private Integer noOfTeamWinners = 5;

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(value = "#{pictureService}")
	private IPictureService pictureService;

	@ManagedProperty(value = "#{teamService}")
	private ITeamService teamService;

	@ManagedProperty(value = "#{teamCandService}")
	private ITeamCandidateService teamCandService;

	@ManagedProperty(value = "#{candService}")
	private ICandidateService candService;

	@ManagedProperty(value = "#{voteService}")
	private IVoteService voteService;

	@ManagedProperty(value = "#{roleService}")
	private IRoleService roleService;

	@ManagedProperty(value = "#{teamVoteService}")
	private ITeamVoteService teamVoteService;

	@ManagedProperty(value = "#{periodService}")
	private IPeriodService periodService;

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	private User loggedUser;

	private BigInteger totalVotesUser;
	private BigInteger totalVotesTeam;

	@ManagedProperty(value = "#{pieBean}")
	private PieBean pieBean;

	private boolean showHistory;

	private WinnerDTO currentWinner;

	private TeamWinnerDTO currentTeamWinner;

	private String winnerReasons;

	@PostConstruct
	public void init() {
		if (!session.isLogged()) {
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "index?faces-redirect=true");

		} else {
			this.loggedUser = session.getLoggedUser();
			this.period = periodService.getCurrentByCountry(loggedUser.getAgency().getCountriesId());
			this.users = userService.getUsersByCountry(loggedUser.getAgency().getCountriesId());
			this.teams = teamService.getAllfromCountry(loggedUser.getAgency().getCountriesId());
			if (pictureService.getPictureForUser(loggedUser) != null) {
				this.picture = pictureService.getPictureForUser(loggedUser);
			}
			if (period != null) {
				this.candidates = candService.getAllActiveDistinct(period.getId());
				this.teamCandidates = teamCandService.getAllActiveDistinct(period.getId());
				this.winners = voteService.getLastNWinners(noOfWinners, loggedUser.getAgency().getCountriesId());
				this.teamWinners = teamVoteService.getLastNWinners(noOfTeamWinners,
						loggedUser.getAgency().getCountriesId());

				pieChartTeam();
				pieChartUser();

			}
		}
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public User getChosenUser() {
		return chosenUser;
	}

	public void setChosenUser(User chosenUser) {
		this.chosenUser = chosenUser;
	}

	public void onUserDrop(DragDropEvent ddEvent) {
		User user = ((User) ddEvent.getData());

		if (chosenUser != null) {
			users.add(chosenUser);
		}
		chosenUser = user;
		users.remove(user);
	}

	public void saveUser() {

		if (chosenUser != null) {
			if (session.getLoggedUser().getId() != chosenUser.getId()) {
				candService.saveCandidate(period.getId(), session.getLoggedUser().getId(), chosenUser.getId(),
						userReason);
				candidates = candService.getAllActiveDistinct(period.getId());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Your user recommendation has been saved", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "You cannot select yourself!", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "You did not select any user!", ""));
		}
	}

	public List<Team> getTeams() {
		return teams;
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public Team getChosenTeam() {
		return chosenTeam;
	}

	public void setChosenTeam(Team chosenTeam) {
		this.chosenTeam = chosenTeam;
	}

	public void onTeamDrop(DragDropEvent ddEvent) {
		Team team = ((Team) ddEvent.getData());

		if (chosenTeam != null) {
			teams.add(chosenTeam);
		}
		chosenTeam = team;
		teams.remove(team);
	}

	public void saveTeam() {
		if (chosenTeam != null) {
			if (session.getLoggedUser().getTeamsId() != chosenTeam.getId()) {
				teamCandService.saveTeamCandidate(period.getId(), session.getLoggedUser().getId(), chosenTeam.getId(),
						teamReason);
				teamCandidates = teamCandService.getAllActiveDistinct(period.getId());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Your team recommendation has been saved", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "You cannot recommend your team!", ""));
			}
		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "You did not select any team!", ""));
		}
	}

	public ITeamCandidateService getTeamCandService() {
		return teamCandService;
	}

	public void setTeamCandService(ITeamCandidateService teamCandService) {
		this.teamCandService = teamCandService;
	}

	public ICandidateService getCandService() {
		return candService;
	}

	public void setCandService(ICandidateService candService) {
		this.candService = candService;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public String getUserReason() {
		return userReason;
	}

	public void setUserReason(String userReason) {
		this.userReason = userReason;
	}

	public String getTeamReason() {
		return teamReason;
	}

	public void setTeamReason(String teamReason) {
		this.teamReason = teamReason;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public List<TeamCandidate> getTeamCandidates() {
		return teamCandidates;
	}

	public void setTeamCandidates(List<TeamCandidate> teamCandidates) {
		this.teamCandidates = teamCandidates;
	}

	public Candidate getSelectedCandidate() {
		return selectedCandidate;
	}

	public void setSelectedCandidate(Candidate selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}

	public TeamCandidate getSelectedTeamCandidate() {
		return selectedTeamCandidate;
	}

	public void setSelectedTeamCandidate(TeamCandidate selectedTeamCandidate) {
		this.selectedTeamCandidate = selectedTeamCandidate;
	}

	public Candidate getChosenCandidate() {
		return chosenCandidate;
	}

	public void setChosenCandidate(Candidate chosenCandidate) {
		this.chosenCandidate = chosenCandidate;
	}

	public TeamCandidate getChosenTeamCandidate() {
		return chosenTeamCandidate;
	}

	public void setChosenTeamCandidate(TeamCandidate chosenTeamCandidate) {
		this.chosenTeamCandidate = chosenTeamCandidate;
	}

	public IVoteService getVoteService() {
		return voteService;
	}

	public void setVoteService(IVoteService voteService) {
		this.voteService = voteService;
	}

	public ITeamVoteService getTeamVoteService() {
		return teamVoteService;
	}

	public void setTeamVoteService(ITeamVoteService teamVoteService) {
		this.teamVoteService = teamVoteService;
	}

	public void onCandDrop(DragDropEvent ddEvent) {
		Candidate candidate = ((Candidate) ddEvent.getData());

		if (chosenCandidate != null) {
			candidates.add(chosenCandidate);
		}
		chosenCandidate = candidate;
		candidates.remove(candidate);
	}

	public void onTeamCandDrop(DragDropEvent ddEvent) {
		TeamCandidate teamCandidate = ((TeamCandidate) ddEvent.getData());

		if (chosenTeamCandidate != null) {
			teamCandidates.add(chosenTeamCandidate);
		}
		chosenTeamCandidate = teamCandidate;
		teamCandidates.remove(teamCandidate);
	}

	public void saveVote() {

		if (chosenCandidate != null) {
			if (session.getLoggedUser().getId() != chosenCandidate.getSelectedUserId()) {
				voteService.saveVote(period.getId(), session.getLoggedUser().getId(),
						chosenCandidate.getSelectedUserId());
				pieChartUser();

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Your user vote has been saved", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "You cannot vote yourself!", ""));
			}
		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "You did not select any user to vote!", ""));
		}
	}

	public void saveTeamVote() {

		if (chosenTeamCandidate != null) {
			if (session.getLoggedUser().getTeamsId() != chosenTeamCandidate.getSelectedTeamId()) {
				teamVoteService.saveTeamVote(period.getId(), session.getLoggedUser().getId(),
						chosenTeamCandidate.getSelectedTeamId());
				pieChartTeam();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Your team vote has been saved", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "You cannot vote your team!", ""));
			}
		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "You did not select any team to vote!", ""));
		}
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public IPeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(IPeriodService periodService) {
		this.periodService = periodService;
	}

	public String getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(String userFilter) {
		this.userFilter = userFilter;
	}

	public String getTeamFilter() {
		return teamFilter;
	}

	public void setTeamFilter(String teamFilter) {
		this.teamFilter = teamFilter;
	}

	public String getUserVoteFilter() {
		return userVoteFilter;
	}

	public void setUserVoteFilter(String userVoteFilter) {
		this.userVoteFilter = userVoteFilter;
	}

	public String getTeamVoteFilter() {
		return teamVoteFilter;
	}

	public void setTeamVoteFilter(String teamVoteFilter) {
		this.teamVoteFilter = teamVoteFilter;
	}

	public void filterUsers() {
		if (userFilter != null) {
			this.users = userService.filterByNameOrAgency(userFilter, loggedUser.getAgency().getCountriesId());
		}
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void filterTeams() {
		if (teamFilter != null) {
			this.teams = teamService.filterByNameAgencyProject(teamFilter, loggedUser.getAgency().getCountriesId());
		}
	}

	public void filterCandidates() {
		if (userVoteFilter != null) {
			this.candidates = candService.filterByNameOrAgency(userVoteFilter, period.getId(),
					loggedUser.getAgency().getCountriesId());
		}
	}

	public void filterTeamCandidates() {
		if (teamVoteFilter != null) {
			this.teamCandidates = teamCandService.filterByNameAgencyProject(teamVoteFilter, period.getId(),
					loggedUser.getAgency().getCountriesId());
		}
	}

	public boolean isRecommendationPeriod() {
		if (period != null) {
			Date now = Date.valueOf(LocalDate.now());
			return (now.compareTo(period.getLastRecommendationDay()) <= 0);
		}
		return false;
	}

	public boolean isVotingPeriod() {
		if (period != null) {
			Date now = Date.valueOf(LocalDate.now());
			return (now.compareTo(period.getLastRecommendationDay()) > 0
					&& now.compareTo(period.getLastVotingDay()) <= 0)
					&& ((teamCandService.getAllActiveDistinct(period.getId()) != null)
							|| (candService.getAllActiveDistinct(period.getId()) != null));
		}
		return false;
	}

	public boolean isWinnerPeriod() {
		if (period != null) {
			return (!isRecommendationPeriod() && !isVotingPeriod());
		}
		return false;
	}

	public boolean existsWinningUser() {
		if (voteService.getCurrentWinner(loggedUser.getAgency().getCountriesId()) != null)
			return true;
		return false;
	}

	public boolean existsWinningTeam() {
		if (teamVoteService.getWinner(loggedUser.getAgency().getCountriesId()) != null)
			return true;
		return false;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public List<WinnerDTO> getWinners() {
		return winners;
	}

	public void setWinners(List<WinnerDTO> winners) {
		this.winners = winners;
	}

	public Integer getNoOfWinners() {
		return noOfWinners;
	}

	public void setNoOfWinners(Integer noOfWinners) {
		this.noOfWinners = noOfWinners;
	}

	public void changeWinners() {
		this.winners = voteService.getLastNWinners(noOfWinners, loggedUser.getAgency().getCountriesId());
	}

	public List<TeamWinnerDTO> getTeamWinners() {
		return teamWinners;
	}

	public void setTeamWinners(List<TeamWinnerDTO> teamWinners) {
		this.teamWinners = teamWinners;
	}

	public Integer getNoOfTeamWinners() {
		return noOfTeamWinners;
	}

	public void setNoOfTeamWinners(Integer noOfTeamWinners) {
		this.noOfTeamWinners = noOfTeamWinners;
	}

	public void changeTeamWinners() {
		this.teamWinners = teamVoteService.getLastNWinners(noOfTeamWinners, loggedUser.getAgency().getCountriesId());
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public IPictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPictureService pictureService) {
		this.pictureService = pictureService;
	}

	public BigInteger getTotalVotesUser() {
		return totalVotesUser;
	}

	public void setTotalVotesUser(BigInteger totalVotesUser) {
		this.totalVotesUser = totalVotesUser;
	}

	public PieBean getPieBean() {
		return pieBean;
	}

	public void setPieBean(PieBean pieBean) {
		this.pieBean = pieBean;
	}

	public BigInteger getTotalVotesTeam() {
		return totalVotesTeam;
	}

	public void setTotalVotesTeam(BigInteger totalVotesTeam) {
		this.totalVotesTeam = totalVotesTeam;
	}

	public boolean isShowHistory() {
		return showHistory;
	}

	public void switchShowHistory() {
		this.showHistory = !this.showHistory;
	}

	public void setShowHistory(boolean showHistory) {
		this.showHistory = showHistory;
	}

	public String historyButton() {
		return this.showHistory ? "Hide" : "Show";
	}

	public WinnerDTO getCurrentWinner() {
		if (isWinnerPeriod()) {
			currentWinner = voteService.getCurrentWinner(loggedUser.getAgency().getCountriesId());
			List<String> reasons = candService.getAllReasonsForUser(currentWinner.getUserId(),
					currentWinner.getPeriodId());
			if (reasons != null) {
				winnerReasons = reasons.get(0);
				reasons.remove(0);
				for (String reas : reasons) {
					if (reas != null && !reas.equals("")) {
						winnerReasons += "; " + reas;
					}
				}
			}
		}

		return currentWinner;
	}

	public void setCurrentWinner(WinnerDTO currentWinner) {
		this.currentWinner = currentWinner;
	}

	public String getWinnerReasons() {
		return winnerReasons;
	}

	public void setWinnerReasons(String winnerReasons) {
		this.winnerReasons = winnerReasons;
	}

	public TeamWinnerDTO getCurrentTeamWinner() {
		if (isWinnerPeriod()) {
			currentTeamWinner = teamVoteService.getWinner(loggedUser.getAgency().getCountriesId());

			/*
			 * List<String> reasons =
			 * candService.getAllReasonsForUser(currentWinner.getUserId(),
			 * currentWinner.getPeriodId()); if (reasons != null) {
			 * winnerReasons = reasons.get(0); reasons.remove(0); for (String
			 * reas : reasons) { if (reas != null && !reas.equals("")) {
			 * winnerReasons += "; " + reas; } } }
			 */
		}

		return currentTeamWinner;
	}

	public void setCurrentTeamWinner(TeamWinnerDTO currentTeamWinner) {
		this.currentTeamWinner = currentTeamWinner;
	}

	public Boolean noWinner() {
		if (loggedUser != null && (isVotingPeriod() == true || isWinnerPeriod() == true)) {
			if (teamVoteService.getWinner(loggedUser.getAgency().getCountriesId()) == null
					&& voteService.getCurrentWinner(loggedUser.getAgency().getCountriesId()) == null
					&& teamCandService.getAllActiveDistinct(period.getId()) == null
					&& candService.getAllActiveDistinct(period.getId()) == null) {
				return true;
			}
		}
		return false;
	}

	public Boolean existsUserCandidates() {
		if (candService.getAllActiveDistinct(period.getId()) != null) {
			return true;
		}
		return false;
	}

	public Boolean existsTeamCandidates() {
		if (teamCandService.getAllActiveDistinct(period.getId()) != null) {
			return true;
		}
		return false;
	}
	
	public Boolean existsUserVotes() {
		if (voteService.getTotalNumberOfVotes(period.getCountriesId()).intValue() != 0) {
			return true;
		}
		return false;
	}

	public Boolean existsTeamVotes() {
		if (teamVoteService.getTotalNumberOfVotes(period.getCountriesId()).intValue() != 0) {
			return true;
		}
		return false;
	}

	private void pieChartUser() {
		pieBean.resetUser();

		List<TopCandidateDTO> topList = voteService.getCurrentTopN(2, loggedUser.getAgency().getCountriesId());
		this.totalVotesUser = voteService.getTotalNumberOfVotes(loggedUser.getAgency().getCountriesId());
		int others = 0;
		if (topList != null) {
			for (TopCandidateDTO cand : topList) {
				if (cand != null) {
					String name = cand.getLastName() + " " + cand.getFirstName();
					Number votes = cand.getNoOfVotes();
					others += votes.intValue();
					pieBean.setUser(name, votes);
				}
			}
		}
		if (totalVotesUser.intValue() != 0) {
			pieBean.setUser("Others", totalVotesUser.intValue() - others);
		}
		pieBean.setTitleUser("Top Candidates");
	}

	private void pieChartTeam() {
		pieBean.resetTeam();

		List<TopTeamDTO> topListTeam = teamVoteService.getCurrentTopN(2, loggedUser.getAgency().getCountriesId());

		this.totalVotesTeam = teamVoteService.getTotalNumberOfVotes(loggedUser.getAgency().getCountriesId());
		int teamOthers = 0;
		for (TopTeamDTO teamCand : topListTeam) {
			if (teamCand != null) {
				String name = teamCand.getTeamName();
				Number votes = teamCand.getNoOfVotes();
				teamOthers += votes.intValue();
				pieBean.setTeam(name, votes);
			}
		}
		if (totalVotesTeam.intValue() != 0) {
			pieBean.setTeam("Others", totalVotesTeam.intValue() - teamOthers);
		}
		pieBean.setTitleTeam("Top Teams");
		showHistory = false;
	}

}
