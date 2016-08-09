package com.voigt.hwd.client.bet;

import com.google.gwt.user.client.Random;

public class WeightedRandomBetGenerator extends RandomBetGenerator {

	private final int[] weightedGoalValues = { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3,
			3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9, 10 };

	@Override
	protected int getRandomGoal() {
		int weightedRandom = weightedGoalValues[Random.nextInt(weightedGoalValues.length)];
		return weightedRandom;
	}

}
