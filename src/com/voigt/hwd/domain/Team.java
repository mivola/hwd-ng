package com.voigt.hwd.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Team implements IDomainObject {

	private static final long serialVersionUID = -1965120390159842400L;

	@Id
	@GeneratedValue
	private int id;

	/* the OpenLigaDB-ID for this team */
	private int oLId;

	/* the name of the team */
	private String name;

	/* the short name of the team */
	private String abbreviation;

	/* the home matches played by the team (obsolete?) */
	@OneToMany(mappedBy = "team1")
	private List<Match> homeMatches;

	/* the away matches played by the team (obsolete?) */
	@OneToMany(mappedBy = "team2")
	private List<Match> awayMatches;

	/* the leagues in which this team participated */
	@ManyToMany
	private List<League> leagues;

	public static final String LEAGUES = "leagues";
	public static final String OL_ID = "oLId";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOLId() {
		return oLId;
	}

	public void setOLId(int id) {
		oLId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public List<Match> getHomeMatches() {
		return homeMatches;
	}

	public void setHomeMatches(List<Match> homeMatches) {
		this.homeMatches = homeMatches;
	}

	public List<Match> getAwayMatches() {
		return awayMatches;
	}

	public void setAwayMatches(List<Match> awayMatches) {
		this.awayMatches = awayMatches;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	@Override
	public String toString() {
		return "id: " + id + "; oLId: " + oLId + "; name: " + name + "; abbreviation: " + abbreviation;
	}
}
