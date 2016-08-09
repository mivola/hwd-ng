package com.voigt.hwd.client.history;

import java.util.Date;

import com.voigt.hwd.client.i18n.HwdMessages;
import com.voigt.hwd.client.i18n.HwdMessagesFactory;

public class HwdItemData {

	private static HwdItemRecord[] records;

	private static final HwdMessages messages = HwdMessagesFactory.getInstance();

	public static HwdItemRecord[] getRecords() {
		if (records == null) {
			records = getNewRecords();
		}
		return records;
	}

	@SuppressWarnings("deprecation")
	public static HwdItemRecord[] getNewRecords() {

		// TODO: 1-3. plätze punkte vergeben und summieren!
		// TODO: faktor für saisons for 3-punkte regel, und ohne joker

		int cntTotalSeasons = 9;

		// 05.02.2000 (R�ckrunde Saison 1999/2000)
		Date hueniDate = new Date(2000 - 1900, 1, 5, 0, 0, 0);

		HwdItemRecord hueni = new HwdItemRecord(1, messages.userHueni(), hueniDate, cntTotalSeasons - 0, 1, 1, 2, 1,
				(8 + 10 + 7 + 10 + 9) + 29.5f + 21 + (49), (97 + 173 + 246 + 232 + 250) + 244 + 210 + (252));
		HwdItemRecord micha = new HwdItemRecord(2, "micha", hueniDate, cntTotalSeasons - 0, 1, 3, 4, 0,
				(7 + 12 + 11 + 9.5f + 12) + 39 + 21.5f + (67), (101 + 177 + 243 + 250 + 273) + 292 + 248 + (282));

		// 11.03.2000 (24. Spieltag Saison 1999/2000)
		Date stevDate = new Date(2000 - 1900, 2, 11);
		HwdItemRecord stev = new HwdItemRecord(3, "stev", stevDate, cntTotalSeasons - 0, 3, 3, 1, 1,
				(3 + 12 + 6.5f + 14.5f + 10.5f) + 45.5f + 35 + (87), (58 + 181 + 214 + 262 + 278) + 280 + 288 + (318));

		// 09.08.2002 (Saison 2002/2003)
		Date nicoDate = new Date(2002 - 1900, 7, 9);
		HwdItemRecord nico = new HwdItemRecord(4, "nico", nicoDate, cntTotalSeasons - 3, 4, 1, 1, 0,
				(13 + 9 + 9.5f) + 60.5f + 35 + (67), (285 + 261 + 293) + 325 + 291 + (295));
		HwdItemRecord markus = new HwdItemRecord(5, "markus", nicoDate, cntTotalSeasons - 3, 0, 0, 0, 4,
				(5 + 3.5f + 4) + 29.5f + 29 + (56), (185 + 191 + 240) + 243 + 258 + (224));
		HwdItemRecord tobi = new HwdItemRecord(6, "tobi", nicoDate, cntTotalSeasons - 3, 0, 1, 0, 1,
				(11 + 7 + 5) + 22.5f + 24 + (66.5f), (244 + 208 + 185) + 235 + 242 + (271));

		// 21.07.2005 (Saison 2005/2006)
		Date marcelDate = new Date(2005 - 1900, 7, 21);
		HwdItemRecord marcel = new HwdItemRecord(7, "marcelly", marcelDate, cntTotalSeasons - 5, 0, 0, 0, 1,
				23 + 16.5f + (48), 236 + 243 + (239));

		// 01.08.2006 (Saison 2006/2007)
		Date patziDate = new Date(2006 - 1900, 8, 1);
		HwdItemRecord patzi = new HwdItemRecord(8, "patzi", patziDate, cntTotalSeasons - 6, 0, 0, 0, 1, 22 + (29),
				257 + (174));
		HwdItemRecord jan = new HwdItemRecord(9, "janosch", patziDate, cntTotalSeasons - 6, 0, 0, 1, 0, 30 + (57),
				272 + (285));

		// 20.07.2007 (Saison 2007/2008)
		Date rossiDate = new Date(2007 - 1900, 7, 20);
		HwdItemRecord rossi = new HwdItemRecord(10, "rossi", rossiDate, cntTotalSeasons - 7, 0, 0, 0, 0, 58.5f, 255);
		HwdItemRecord sven = new HwdItemRecord(11, "sven", rossiDate, cntTotalSeasons - 7, 0, 0, 0, 0, 59, 261);

		return new HwdItemRecord[] { hueni, micha, stev, nico, markus, tobi, marcel, patzi, jan, rossi, sven };

	}
}