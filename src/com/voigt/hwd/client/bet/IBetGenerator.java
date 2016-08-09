package com.voigt.hwd.client.bet;

import java.util.List;
import java.util.Map;

import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.Match;

public interface IBetGenerator {

	/**
	 * @return adds a Bet to each Match according to the specific algorithm
	 */
	public Map<Match, Bet> generateBets(List<Match> matches);

}
