package com.voigt.hwd.client.ui;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.types.TimeDisplayFormat;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.Bet.BetJoker;
import com.voigt.hwd.domain.Match;

public class MatchFixture extends Composite {

	private static final int TEAM_FIELD_LENGTH = 50;
	private static final int TEAM_FIELD_WIDTH = 120;

	private final DateItem matchDay = new DateItem();
	private final TimeItem matchTime = new TimeItem("");

	private final TextItem homeTeam = new TextItem("Teams");
	private final TextItem awayTeam = new TextItem("-");

	private final TextItem resultItem = new TextItem("Ergebnis");

	private final Label dirtyLabel = new Label("*");

	private final IntegerItem bet1Item = new IntegerItem();
	private final TextItem bet2Item = new TextItem("-");
	private final CheckboxItem joker1Item = new CheckboxItem("Joker");

	// private final List<ChangeListener> jokerChangeListeners=new
	// ArrayList<ChangeListener>();
	ChangeListenerCollection jokerChangeListenerCollection = new ChangeListenerCollection();

	private Match match = null;
	private Bet bet = null;

	public MatchFixture() {

		final HLayout hLayout = new HLayout();

		matchDay.setUseTextField(true);
		matchDay.setTitle("Anstoss");
		matchDay.setWidth(90);
		matchDay.setShowIcons(false);
		matchDay.setDisabled(true);
		// matchDay.setDisplayFormat(DateDisplayFormat.valueOf("DD.MM.YYYY"));
		// matchDay.setInputFormat("DD.MM.YYYY");

		matchTime.setShowHint(false);
		matchTime.setWidth(40);
		matchTime.setTimeFormatter24Hour(TimeDisplayFormat.TOSHORTPADDED24HOURTIME);
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

		joker1Item.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				if (bet != null) {
					if ((Boolean) joker1Item.getValue()) {
						bet.setJoker(BetJoker.STANDARD);
					} else {
						bet.setJoker(BetJoker.NONE);
					}
				}
				jokerChangeListenerCollection.fireChange(hLayout);
			}

		});

		DynamicForm form = new DynamicForm();
		form.setNumCols(28);
		form.setTitleSuffix("");
		// form.setBorder("1px dotted blue");

		form.setFields(matchDay, matchTime, homeTeam, awayTeam, resultItem, bet1Item, bet2Item, joker1Item);

		// form.setDisabled(true);

		// dirtyLabel.setAutoWidth();
		// dirtyLabel.setContents("*");

		hLayout.addMember(form);
		hLayout.addMember(dirtyLabel);

		hLayout.setHeight(25);
		// hLayout.setBorder("1px red solid");

		initWidget(hLayout);

		// setShowBets(false);
		setShowResults(false);
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

	public void setShowResults(boolean showResults) {
		resultItem.setVisible(showResults);
		// resultItem.redraw();
		if (showResults) {
			resultItem.show();
		} else {
			resultItem.hide();
		}
	}

	public void setShowBets(boolean showBets) {
		bet1Item.setVisible(showBets);
		bet2Item.setVisible(showBets);
		joker1Item.setVisible(showBets);

		if (showBets) {
			bet1Item.show();
			bet2Item.show();
			joker1Item.show();
		} else {
			bet1Item.hide();
			bet2Item.hide();
			joker1Item.hide();
		}
	}

	public void setDirtyLabel(String text) {
		dirtyLabel.setContents(text);
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
			// TODO: set date and time properly!
			// - date format:
			// -- http://forums.smartclient.com/showthread.php?t=3535
			// -- com.smartgwt.client.util.DateUtil
			// - timezone:
			// --
			// http://forums.smartclient.com/showthread.php?t=5064&highlight=timezone
			// --
			// http://forums.smartclient.com/showthread.php?t=1382&highlight=timezone

			Date date = new Date(kickOffDate.getTime());

			// matchDay.setValue(kickOffDate.getDate() + "." +
			// kickOffDate.getMonth() + "." + kickOffDate.getYear());
			// matchTime.setValue(kickOffDate.getHours() + ":" +
			// kickOffDate.getMinutes());
			matchDay.setValue(date);
			matchTime.setValue(date);
		}

		if (match.isDirty()) {
			setDirtyLabel("*");
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

	public Match getMatch() {
		return match;
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

	private void setBet1(int bet1) {
		bet1Item.setValue(bet1);
	}

	private void setBet2(int bet2) {
		bet2Item.setValue(bet2);
	}

	public Bet getBet() {
		return bet;
	}

	public void addJokerChangeListener(ChangeListener listener) {
		this.jokerChangeListenerCollection.add(listener);
	}

	public void removeJokerChangeListener(ChangeListener listener) {
		this.jokerChangeListenerCollection.remove(listener);
	}

	public void deleteJoker() {
		if (bet != null) {
			bet.setJoker(BetJoker.NONE);
		}
		joker1Item.setValue(false);
	}

	public BetJoker getJoker() {
		return ((Boolean) joker1Item.getValue() ? BetJoker.STANDARD : BetJoker.NONE);
	}

}
