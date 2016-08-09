package com.voigt.hwd.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * describes one season; contains a list of leagues (usually two: 1.BL, 2.BL)
 * that take place in this season
 * 
 * @author mvoigt
 * 
 */
@Entity
public class Season implements IDomainObject {

	private static final long serialVersionUID = -8090200256472548112L;

	@Id
	@GeneratedValue
	private int id;

	/* when did the season start, eg 2008 for the season "2008/2009" */
	private int year;

	/* eg "Saison 2008/2009" */
	private String title;

	/* is this the currently played season? */
	private boolean active;

	/* openLigaDB-ID, eg 18 for BL1/2008 */
	private int oLId;

	/* usually 2 entries: 1. BL, 2. BL */
	@OneToMany(mappedBy = "season")
	private List<League> leagues;

	/* the matchdays that are played in this season; usually 34 entries */
	@OneToMany(mappedBy = "season")
	private List<MatchDay> matchDays;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getOLId() {
		return oLId;
	}

	public void setOLId(int id) {
		oLId = id;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public List<MatchDay> getMatchDays() {
		return matchDays;
	}

	public void setMatchDays(List<MatchDay> matchDays) {
		this.matchDays = matchDays;
	}

	@Override
	public String toString() {
		return "id: " + id + "; title: " + title + "; active: " + active + "; year: " + year + "; oLId: " + oLId;
	}

}
