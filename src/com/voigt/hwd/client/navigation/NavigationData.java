package com.voigt.hwd.client.navigation;

import java.util.HashMap;
import java.util.Map;

import com.voigt.hwd.client.admin.CountryAndSupplyManager;
import com.voigt.hwd.client.admin.MatchManager;
import com.voigt.hwd.client.admin.NewUserManager;
import com.voigt.hwd.client.admin.TeamManager;
import com.voigt.hwd.client.admin.UserManager;
import com.voigt.hwd.client.admin.UsersOnlineManager;
import com.voigt.hwd.client.grid.history.HistoryAllTimeTable;
import com.voigt.hwd.client.grid.history.HistoryOverview;
import com.voigt.hwd.client.grid.history.HistoryStandingsChart;
import com.voigt.hwd.client.user.MatchBetsManager;

public class NavigationData {

	public static final String ROOT = "root";

	private static final Map<String, ExplorerTreeNode[]> navigationDataMap = new HashMap<String, ExplorerTreeNode[]>();

	private final String idSuffix;

	public NavigationData(String idSuffix) {
		this.idSuffix = idSuffix;
	}

	private ExplorerTreeNode[] getData() {

		ExplorerTreeNode[] data = navigationDataMap.get(idSuffix);

		if (data == null) {
			data = new ExplorerTreeNode[] {

					new ExplorerTreeNode("HWD History", "history", ROOT, "silk/house.png", null, true, idSuffix),
					new ExplorerTreeNode("Jahres&uuml;berblick", "history-overview", "history",
							"silk/calendar_view_day.png", new HistoryOverview.Factory(), true, idSuffix),
					new ExplorerTreeNode("Ewige Tabelle", "history-alltime-table", "history", "silk/calendar_add.png",
							new HistoryAllTimeTable.Factory(), true, idSuffix),
					new ExplorerTreeNode("9-Jahres-Platzierungen", "history-standings-chart", "history",
							"silk/calendar_add.png", new HistoryStandingsChart.Factory(), true, idSuffix),

					new ExplorerTreeNode("User", "user", ROOT, "silk/house.png", null, true, idSuffix),
					new ExplorerTreeNode("Tipps abgeben", "user-bets", "user", "silk/house.png",
							new MatchBetsManager.Factory(), true, idSuffix),

					new ExplorerTreeNode("Admin", "admin", ROOT, "silk/house.png", null, true, idSuffix),
					new ExplorerTreeNode("Teamverwaltung", "admin-team", "admin", "silk/house.png",
							new TeamManager.Factory(), true, idSuffix),
					new ExplorerTreeNode("Userverwaltung", "admin-user", "admin", "silk/house.png",
							new UserManager.Factory(), true, idSuffix),
					new ExplorerTreeNode("Matches", "admin-matches", "admin", "silk/house.png",
							new MatchManager.Factory(), true, idSuffix),
					new ExplorerTreeNode("Userverwaltung.neu", "admin-user-new", "admin", "silk/house.png",
							new NewUserManager.Factory(), true, idSuffix),
					new ExplorerTreeNode("Wer ist online?", "admin-online-sessions", "admin", "silk/house.png",
							new UsersOnlineManager.Factory(), true, idSuffix),
					new ExplorerTreeNode("example", "admin-example", "admin", "silk/house.png",
							new CountryAndSupplyManager.Factory(), true, idSuffix),

					new ExplorerTreeNode("HWD Other Stuff", "other", ROOT, "silk/house.png", null, true, idSuffix),
					new CommandTreeNode("Developer Console", "debug-category", "other", "silk/bug.png",
							new DebugConsoleCommand(), true, idSuffix), };

			navigationDataMap.put(idSuffix, data);

		}
		return data;
	}

	public static ExplorerTreeNode[] getData(String idSuffix) {
		return new NavigationData(idSuffix).getData();
	}
}
