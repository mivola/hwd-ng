package com.voigt.hwd.client.ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.Match;

public class MatchFixtureForm extends DynamicForm {

	private static final int TEAM_FIELD_LENGTH = 50;
	private static final int TEAM_FIELD_WIDTH = 120;

	private final DateItem matchDay = new DateItem();
	private final TimeItem matchTime = new TimeItem("");

	private final TextItem homeTeam = new TextItem("Teams");
	private final TextItem awayTeam = new TextItem("awayTeam", "-");

	private boolean showResults = true;
	private final TextItem resultItem = new TextItem("Ergebnis");

	private final Label dirtyLabel = new Label("*");

	private final boolean showBets = true;
	private final IntegerItem bet1Item = new IntegerItem();
	private final TextItem bet2Item = new TextItem("bet2Item", "-");
	private final CheckboxItem joker1Item = new CheckboxItem("Joker");

	private Match match = null;
	private Bet bet = null;

	public MatchFixtureForm(Match match, Bet bet) {

		matchDay.setUseTextField(true);
		matchDay.setTitle("Anstoss");
		matchDay.setWidth(90);
		matchDay.setShowIcons(false);
		matchDay.setDisabled(true);

		matchTime.setShowHint(false);
		matchTime.setWidth(40);
		// matchTime.setTimeFormatter(TimeFormatter.TOSHORTPADDED24HOURTIME);
		matchTime.setDisabled(true);

		homeTeam.setLength(TEAM_FIELD_LENGTH);
		homeTeam.setWidth(TEAM_FIELD_WIDTH);
		homeTeam.setDisabled(true);

		awayTeam.setLength(TEAM_FIELD_LENGTH);
		awayTeam.setWidth(TEAM_FIELD_WIDTH);
		awayTeam.setDisabled(true);

		resultItem.setLength(7);
		resultItem.setWidth(35);
		resultItem.setDisabled(true);
		// resultItem.setVisible(false);

		// bet1Item.setLength(2);
		bet1Item.setTitle("Tipp");
		bet1Item.setWidth(20);
		bet2Item.setLength(2);
		bet2Item.setWidth(20);

		this.setNumCols(25);
		this.setTitleSuffix("");

		setMatch(match);
		setBet(bet);

		List<FormItem> list = new ArrayList<FormItem>();
		list.add(matchDay);
		list.add(matchTime);
		list.add(homeTeam);
		list.add(awayTeam);
		if (showResults) {
			list.add(resultItem);

			// disable bets since result is already known
			bet1Item.setDisabled(true);
			bet2Item.setDisabled(true);
			joker1Item.setDisabled(true);
		}
		if (showBets) {
			list.add(bet1Item);
			list.add(bet2Item);
			list.add(joker1Item);
		}

		FormItem[] formItems = new FormItem[list.size()];
		int i = 0;
		for (FormItem formItem : list) {
			formItems[i++] = formItem;
		}

		this.setFields(formItems);

	}

	public void setMatch(Match match) {

		this.match = match;

		if (match.getTeam1() != null) {
			setHomeTeamName(match.getTeam1().getName());
		}
		if (match.getTeam2() != null) {
			setAwayTeamName(match.getTeam2().getName());
		}

		Timestamp kickOffDate = match.getKickOffDate();
		if (kickOffDate != null) {

			Date date = new Date(kickOffDate.getTime());

			matchDay.setValue(date);
			matchTime.setValue(date);
		}

		if (match.isDirty()) {
			setDirtyLabel("*");
			this.setBorder("1px dotted red");
		} else {
			setDirtyLabel("");
		}

		if (match.getResult1() != Match.NO_RESULT) {
			setResult(match.getResult1() + " - " + match.getResult2());
			setShowResults(true);
		} else {
			setShowResults(false);
			setResult("");
		}

	}

	public void setBet(Bet bet) {
		this.bet = bet;

		if (bet.getBet1() != Bet.NO_BET) {
			setBet1(bet.getBet1());
		}
		if (bet.getBet2() != Bet.NO_BET) {
			setBet2(bet.getBet2());
		}

		if (bet.getJoker() == Bet.BetJoker.STANDARD) {
			joker1Item.setValue(true);
		} else {
			joker1Item.setValue(false);
		}

		setShowBets(true);
	}

	public void setShowBets(boolean showBets) {
		// bet1Item.setVisible(showBets);
		// bet2Item.setVisible(showBets);
		// joker1Item.setVisible(showBets);
		//
		// if (showBets) {
		// bet1Item.show();
		// bet2Item.show();
		// joker1Item.show();
		// } else {
		// bet1Item.hide();
		// bet2Item.hide();
		// joker1Item.hide();
		// }
	}

	public Match getMatch() {
		return match;
	}

	public void setHomeTeamName(String name) {
		homeTeam.setValue(name);
	}

	public void setAwayTeamName(String name) {
		awayTeam.setValue(name);
	}

	public void setResult(String result) {
		resultItem.setValue(result);
	}

	public void setDirtyLabel(String text) {
		dirtyLabel.setContents(text);
	}

	public void setShowResults(boolean showResults) {
		this.showResults = showResults;
	}

	private void setBet1(int bet1) {
		bet1Item.setValue(bet1);
	}

	private void setBet2(int bet2) {
		bet2Item.setValue(bet2);
	}

}
