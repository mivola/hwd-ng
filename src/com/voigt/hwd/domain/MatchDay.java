package com.voigt.hwd.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * describes one match day in a season; contains all matches for that match day
 * for all leagues
 * 
 * @author mvoigt
 * 
 */
@Entity
public class MatchDay implements IDomainObject {

	private static final long serialVersionUID = 6412240540465136886L;

	@Id
	@GeneratedValue
	private int id;

	/* the real Spieltag, e.g. "<11>. Spieltag" */
	private int matchDayId;

	/*
	 * the OpenLiga-ID for that matchDay (ie "groupOrderID"); (should be equal
	 * to matchDayId ?)
	 */
	private int oLId;

	/* are all matches in this match day played and the results entered? */
	private boolean finished;

	/* the season to which this match day belongs */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Season season;

	/* a list of matches (from all leagues) that are played during this matchday */
	@OneToMany(mappedBy = "matchDay")
	private List<Match> matches;

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

	public int getMatchDayId() {
		return matchDayId;
	}

	public void setMatchDayId(int matchDayId) {
		this.matchDayId = matchDayId;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "id: " + id + "; oLId: " + oLId + "; matchDayId: " + matchDayId + "; finished: " + finished;
	}

}
