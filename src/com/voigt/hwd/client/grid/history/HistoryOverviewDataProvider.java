package com.voigt.hwd.client.grid.history;

import java.util.ArrayList;
import java.util.List;

import com.voigt.hwd.client.i18n.HwdMessages;
import com.voigt.hwd.client.i18n.HwdMessagesFactory;

public class HistoryOverviewDataProvider {

	private static final int seasonOffset = 1999;
	private static final String hwdBaseURL = "http://hwd.bts-computer.de/";
	private final List<HistoryOverviewData> historyOverviewList = new ArrayList<HistoryOverviewData>();

	private static final HwdMessages messages = HwdMessagesFactory.getInstance();

	public HistoryOverviewDataProvider() {

		String description1999 = "Die HWD-Premiere haben Hüni und Micha zur Rückrunde der Saison 1999/2000 eröffnet. Und das mit so antiken und unpassenden Mitteln wie Mircosoft Word! Später kam dann auch noch Stev dazu. Damals war der Einsatz noch wesentlich geringer: Der Verlierer eines Spieltags zahlt dem/n Gewinner/n ein Cola-Weizen. Jaja, das waren noch Zeiten ... ;-)";
		HistoryOverviewData data1999 = new HistoryOverviewData(1062, 633, 3, "hwd99_00.png", "", description1999,
				messages.userHueni(), "", "Die erste (halbe) Saison");
		historyOverviewList.add(data1999);

		String description2000 = "Die zweite Saison haben wir zu dritt komplett durchgespielt, Und dank Stev \"Excel-Gott\" Thomas sogar mit vorzüglichen Excel-Tabellen :-)";
		HistoryOverviewData data2000 = new HistoryOverviewData(636, 429, 3, "hwd00_01.png", "Hüni (18)",
				description2000, "Stev", "", "Die erste komplette Saison");
		historyOverviewList.add(data2000);

		String description2001 = "In der Saison 2001/2002 haben wir unsere Mitgliederzahl verdoppelt. Gleich zu Beginn sind Nico und Markus eingestiegen, später (außerhalb der Wertung, zum Warmmachen sozusagen ;-) sogar noch Buschi. In dieser Saison wurde zur Spannungssteigerung auch der Extratipp engeführt.";
		HistoryOverviewData data2001 = new HistoryOverviewData(1114, 842, 5, "hwd01_02.png", "Micha, Nico (19)",
				description2001, "Nico", "", "");
		historyOverviewList.add(data2001);

		String description2002 = "Nach dem Warm-Up in der Vorsaison bekam 2002/2003 auch Buschi das harte Tipperleben zu spüren. 2 Spieltage vor Schluss gab es noch ein Kopf-an-Kopfrennen mit ihm, Nico und Micha. Ein Endspurt mit 2 Siegen bescherrte Nico jedoch den verdienten Sieg und damit gelang erstmals in der HWD-Geschichte eine Titelverteidigung.";
		HistoryOverviewData data2002 = new HistoryOverviewData(1027, 876, 6, "hwd02_03.png", "Nico (17)",
				description2002, "Nico", "", "");
		historyOverviewList.add(data2002);

		String description2003 = "";
		HistoryOverviewData data2003 = new HistoryOverviewData(716, 617, 6, "hwd03_04.png", "Stev (17)",
				description2003, "Stev", hwdBaseURL + "hwd03_04", "");
		historyOverviewList.add(data2003);

		String description2004 = "";
		HistoryOverviewData data2004 = new HistoryOverviewData(925, 655, 6, "hwd04_05.png", "Nico (17)",
				description2004, "Micha", hwdBaseURL + "hwd04_05", "");
		historyOverviewList.add(data2004);

		String description2005 = "Einführung Joker";
		HistoryOverviewData data2005 = new HistoryOverviewData(727, 602, 7, "hwd05_06.png", "Micha (19)",
				description2005, "Nico", hwdBaseURL + "hwd05_06", "");
		historyOverviewList.add(data2005);

		String description2006 = "";
		HistoryOverviewData data2006 = new HistoryOverviewData(874, 581, 9, "hwd06_07.png", "Hüni (25)",
				description2006, "Nico", hwdBaseURL + "hwd06_07", "");
		historyOverviewList.add(data2006);

		String description2007 = "";
		HistoryOverviewData data2007 = new HistoryOverviewData(749, 577, 11, "hwd07_08.png",
				"Hüni, Nico, Markus, Janosch (17)", description2007, "Stev", hwdBaseURL + "hwd07_08", "");
		historyOverviewList.add(data2007);

	}

	public HistoryOverviewData getData(int season) {

		int index = season - seasonOffset;
		if (historyOverviewList.size() > index) {
			return historyOverviewList.get(index);
		} else {
			return new HistoryOverviewData(100, 100, 1, "", "", "", "", "", "");
		}

	}

}
