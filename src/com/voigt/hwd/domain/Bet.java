package com.voigt.hwd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bet implements IDomainObject {

	private static final long serialVersionUID = 3782216829799873967L;

	public static final int NO_BET = -1;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Match match;

	private int bet1;

	private int bet2;

	private BetJoker joker;

	public enum BetJoker {
		NONE, STANDARD
	}

	public Bet() {
		bet1 = NO_BET;
		bet2 = NO_BET;
		joker = BetJoker.NONE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public int getBet1() {
		return bet1;
	}

	public void setBet1(int bet1) {
		this.bet1 = bet1;
	}

	public int getBet2() {
		return bet2;
	}

	public void setBet2(int bet2) {
		this.bet2 = bet2;
	}

	public BetJoker getJoker() {
		return joker;
	}

	public void setJoker(BetJoker joker) {
		this.joker = joker;
	}

}
