package com.voigt.hwd.client.history;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class HwdItemRecord extends ListGridRecord {

	protected final static String USER_ID_FIELD = "userId";
	protected final static String USER_NICKNAME_FIELD = "userNickname";
	public final static String JOINED_FIELD = "joined";
	protected final static String CNT_SEASONS_FIELD = "cntSeasons";
	protected final static String CNT_ALL_TIME_POINTS_FIELD = "cntAllTimePoints";
	protected final static String CNT_FIRST_PLACE_FIELD = "cntFirstPlace";
	protected final static String CNT_SECOND_PLACE_FIELD = "cntSecondPlace";
	protected final static String CNT_THIRD_PLACE_FIELD = "cntThirdPlace";
	protected final static String CNT_LAST_PLACE_FIELD = "cntLastPlace";
	protected final static String TOTAL_POINTS_FIELD = "totalPoints";
	public final static String POINTS_PER_SEASON_FIELD = "pointsPerSeason";
	protected static final String TOTAL_TIPP_POINTS_FIELD = "totalTippPoints";
	public static final String TIPP_POINTS_PER_SEASON_FIELD = "tippPointsPerSeason";

	// used for getter/setter generation
	// private int userId;
	// private String userNickname;
	// private Date joined;
	// private int cntSeasons;
	// private int cntFirstPlace;
	// private int cntSecondPlace;
	// private int cntThirdPlace;
	// private int cntLastPlace;
	// private float totalPoints;
	// private float pointsPerSeason;
	// private int totalTippPoints;
	// private float tippPointsPerSeason;

	public HwdItemRecord(int userId, String userNickname, Date joined, int cntSeasons, int cntFirstPlace,
			int cntSecondPlace, int cntThirdPlace, int cntLastPlace, float totalPoints, int totalTippPoints) {
		setUserId(userId);
		setUserNickname(userNickname);
		setJoined(joined);
		setCntSeasons(cntSeasons);
		setCntAllTimePoints(cntFirstPlace, cntSecondPlace, cntThirdPlace);
		setCntFirstPlace(cntFirstPlace);
		setCntSecondPlace(cntSecondPlace);
		setCntThirdPlace(cntThirdPlace);
		setCntLastPlace(cntLastPlace);
		setTotalPoints(totalPoints);
		setPointsPerSeason((float) totalPoints / (float) cntSeasons);
		setTotalTippPoints(totalTippPoints);
		setTippPointsPerSeason((float) totalTippPoints / (float) cntSeasons);
	}

	public void setCntAllTimePoints(int cntFirstPlace, int cntSecondPlace, int cntThirdPlace) {

		final int firstPlaceFactor = 3;
		final int secondPlaceFactor = 2;
		final int thirdPlaceFactor = 1;

		int sum = firstPlaceFactor * cntFirstPlace + secondPlaceFactor * cntSecondPlace
				+ thirdPlaceFactor * cntThirdPlace;
		setAttribute(CNT_ALL_TIME_POINTS_FIELD, sum);

	}

	public void setUserId(int userId) {
		setAttribute(USER_ID_FIELD, userId);
	}

	public int getUserId() {
		return getAttributeAsInt(USER_ID_FIELD);
	}

	public void setUserNickname(String userNickname) {
		setAttribute(USER_NICKNAME_FIELD, userNickname);
	}

	public String getUserNickname() {
		return getAttribute(USER_NICKNAME_FIELD);
	}

	private void setJoined(Date joined) {
		setAttribute(JOINED_FIELD, joined);
	}

	public String getJoined() {
		return getAttribute(JOINED_FIELD);
	}

	public int getCntSeasons() {
		return getAttributeAsInt(CNT_SEASONS_FIELD);
	}

	public void setCntSeasons(int cntSeasons) {
		setAttribute(CNT_SEASONS_FIELD, cntSeasons);
	}

	public int getCntFirstPlace() {
		return getAttributeAsInt(CNT_FIRST_PLACE_FIELD);
	}

	public void setCntFirstPlace(int cntFirstPlace) {
		setAttribute(CNT_FIRST_PLACE_FIELD, cntFirstPlace);
	}

	public int getCntSecondPlace() {
		return getAttributeAsInt(CNT_SECOND_PLACE_FIELD);
	}

	public void setCntSecondPlace(int cntSecondPlace) {
		setAttribute(CNT_SECOND_PLACE_FIELD, cntSecondPlace);
	}

	public int getCntThirdPlace() {
		return getAttributeAsInt(CNT_THIRD_PLACE_FIELD);
	}

	public void setCntThirdPlace(int cntThirdPlace) {
		setAttribute(CNT_THIRD_PLACE_FIELD, cntThirdPlace);
	}

	public int getCntLastPlace() {
		return getAttributeAsInt(CNT_LAST_PLACE_FIELD);
	}

	public void setCntLastPlace(int cntLastPlace) {
		setAttribute(CNT_LAST_PLACE_FIELD, cntLastPlace);
	}

	public int getTotalPoints() {
		return getAttributeAsInt(TOTAL_POINTS_FIELD);
	}

	public void setTotalPoints(float totalPoints) {
		setAttribute(TOTAL_POINTS_FIELD, totalPoints);
	}

	public float getPointsPerSeason() {
		return getAttributeAsFloat(POINTS_PER_SEASON_FIELD);
	}

	public void setPointsPerSeason(float pointsPerSeason) {
		setAttribute(POINTS_PER_SEASON_FIELD, pointsPerSeason);
	}

	public int getTotalTippPoints() {
		return getAttributeAsInt(TOTAL_TIPP_POINTS_FIELD);
	}

	public void setTotalTippPoints(int totalTippPoints) {
		setAttribute(TOTAL_TIPP_POINTS_FIELD, totalTippPoints);
	}

	public float getTippPointsPerSeason() {
		return getAttributeAsFloat(TIPP_POINTS_PER_SEASON_FIELD);
	}

	public void setTippPointsPerSeason(float tippPointsPerSeason) {
		setAttribute(TIPP_POINTS_PER_SEASON_FIELD, tippPointsPerSeason);
	}

}
