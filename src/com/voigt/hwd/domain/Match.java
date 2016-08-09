package com.voigt.hwd.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * describes one match between two teams at one matchday
 * 
 * @author mvoigt
 * 
 */
@Entity
public class Match implements IDomainObject {

	private static final long serialVersionUID = -1037709895793308169L;

	public static final int NO_RESULT = -1;

	@Id
	@GeneratedValue
	private int id;

	/* openLiga-matchID, eg 1013 */
	private int oLId;

	private Timestamp kickOffDate;

	/* the matchday to which this match belongs */
	@ManyToOne
	@JoinColumn(nullable = false)
	private MatchDay matchDay;

	/* the league to which this match belongs */
	@ManyToOne
	@JoinColumn(nullable = false)
	private League league;

	/* home team */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Team team1;

	/* away team */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Team team2;

	/* a list of bets for this match */
	@OneToMany(mappedBy = "match")
	private List<Bet> bets;

	private int result1;

	private int result2;

	@Transient
	private int team1OLId;

	@Transient
	private int team2OLId;

	/*
	 * is there some data added (eg from OpenLigaDB) that is not yet saved in
	 * the internal DB?
	 */
	@Transient
	private boolean dirty;

	public Match() {
		result1 = NO_RESULT;
		result2 = NO_RESULT;
		dirty = false;
	}

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

	public MatchDay getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(MatchDay matchDay) {
		this.matchDay = matchDay;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	public int getResult1() {
		return result1;
	}

	public void setResult1(int result1) {
		this.result1 = result1;
	}

	public int getResult2() {
		return result2;
	}

	public void setResult2(int result2) {
		this.result2 = result2;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public int getTeam1OLId() {
		return team1OLId;
	}

	public void setTeam1OLId(int team1OLId) {
		this.team1OLId = team1OLId;
	}

	public int getTeam2OLId() {
		return team2OLId;
	}

	public void setTeam2OLId(int team2OLId) {
		this.team2OLId = team2OLId;
	}

	public Timestamp getKickOffDate() {
		return kickOffDate;
	}

	public void setKickOffDate(Timestamp kickOffDate) {
		this.kickOffDate = kickOffDate;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	@Override
	public String toString() {
		return "id: " + id + "; oLId: " + oLId + "; kickOffDate: " + kickOffDate + "; result1: " + result1
				+ "; result2: " + result2 + "; team1OLId: " + team1OLId + "; team2OLId: " + team2OLId;
	}

}
