package com.voigt.hwd.client.bet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Random;
import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.Match;

public class RandomBetGenerator implements IBetGenerator {

	private static final int MAX_GOALS = 11;

	public Map<Match, Bet> generateBets(List<Match> matches) {

		Map<Match, Bet> generatedBets = new HashMap<Match, Bet>();
		for (Match match : matches) {

			Bet bet = new Bet();

			bet.setBet1(getRandomGoal());
			bet.setBet2(getRandomGoal());

			generatedBets.put(match, bet);
		}
		return generatedBets;
	}

	/**
	 * @return a random int value; might be overridden
	 */
	protected int getRandomGoal() {
		return Random.nextInt(MAX_GOALS);
	}

}
