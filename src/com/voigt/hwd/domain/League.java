package com.voigt.hwd.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * defines a league that is valid for one season (eg BL1 2008/2009); contains
 * all matches for that season in this league and a list of teams that play in
 * this league in this season
 * 
 * @author mvoigt
 * 
 */
@Entity
public class League implements IDomainObject {

	private static final long serialVersionUID = 716133650743800785L;

	@Id
	@GeneratedValue
	private int id;

	/* shortcut of this league in the OpenLigaDB, eg "bl1", "bl2" */
	private String oLShortcut;

	/* eg "1. Bundesliga", "2. Bundesliga" */
	private String title;

	/* is it BL1, BL2, ... */
	@Enumerated(value = EnumType.STRING)
	private LeagueType type;

	/* the season in which this league takes place */
	@ManyToOne
	private Season season;

	/* all matches in this league (bl1: 34*9 bl2: 34*3) */
	@OneToMany(mappedBy = "league")
	private List<Match> matches;

	/* all teams that play in this league (18 entries) */
	@ManyToMany(mappedBy = "leagues")
	private List<Team> teams;

	public enum LeagueType {
		FIRST_BL, SECOND_BL
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOLShortcut() {
		return oLShortcut;
	}

	public void setOLShortcut(String shortcut) {
		oLShortcut = shortcut;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LeagueType getType() {
		return type;
	}

	public void setType(LeagueType type) {
		this.type = type;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "id: " + id + "; oLShortcut: " + oLShortcut + "; title: " + title + "; type: " + type;
	}
}
