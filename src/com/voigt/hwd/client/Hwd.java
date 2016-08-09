package com.voigt.hwd.client;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.HeaderControls;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateInputFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;
import com.voigt.hwd.client.i18n.HwdMessages;
import com.voigt.hwd.client.i18n.HwdMessagesFactory;
import com.voigt.hwd.client.navigation.CommandTreeNode;
import com.voigt.hwd.client.navigation.ExplorerTreeNode;
import com.voigt.hwd.client.navigation.NavigationSectionStack;
import com.voigt.hwd.client.navigation.NavigationTree;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.StringReverserService;
import com.voigt.hwd.client.service.StringReverserServiceAsync;
import com.voigt.hwd.client.service.TeamService;
import com.voigt.hwd.client.service.TeamServiceAsync;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.service.UserServiceAsync;
import com.voigt.hwd.client.ui.LoginDeckPanel;
import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Role;
import com.voigt.hwd.domain.Season;
import com.voigt.hwd.domain.SimplePojo;
import com.voigt.hwd.domain.Team;
import com.voigt.hwd.domain.User;
import com.voigt.hwd.domain.exception.BaseException;
import com.voigt.hwd.domain.exception.IdentificationException;

public class Hwd implements EntryPoint, HistoryListener {

	private static final String NORMAL_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
	private static final String SHORT_DATE_FORMAT = "dd.MM.yyyy";
	private TabSet mainTabSet;
	private NavigationTree navigationTree;

	private StringReverserServiceAsync reverserService = null;

	public void onModuleLoad() {

		final String initToken = History.getToken();

		// setup overall layout
		// viewport
		VLayout main = new VLayout() {
			@Override
			protected void onInit() {
				super.onInit();
				if (initToken.length() != 0) {
					onHistoryChanged(initToken);
				}
			}
		};

		// Force proxy generation
		this.forceDomainProxyGeneration();

		// set the date format
		this.setDateFormats();

		// test the RPC Services
		// this.testServices();

		main.setWidth100();
		main.setHeight100();
		main.setLayoutMargin(5);
		main.setStyleName("tabSetContainer");

		HLayout hLayout = new HLayout();
		hLayout.setWidth100();
		hLayout.setHeight100();

		VLayout sideNavLayout = new VLayout();
		sideNavLayout.setHeight100();
		sideNavLayout.setWidth(185);
		sideNavLayout.setShowResizeBar(true);

		LoginDeckPanel loginPanel = new LoginDeckPanel();
		sideNavLayout.addMember(loginPanel);

		navigationTree = new NavigationTree();

		navigationTree.addLeafClickHandler(new LeafClickHandler() {
			public void onLeafClick(LeafClickEvent event) {
				TreeNode node = event.getLeaf();
				showSample(node);
			}
		});

		HwdMessages messages = HwdMessagesFactory.getInstance();

		// TODO: change the value of this key in the properties file
		// during the
		// build process to the compile time
		String builtDate = messages.builtDate();

		Label builtDateLabel = new Label("erstellt: " + builtDate);
		builtDateLabel.setHeight(20);
		builtDateLabel.setWrap(false);

		Label versionLabel = new Label("v" + messages.builtVersion());
		versionLabel.setWidth100();
		versionLabel.setHeight(20);
		versionLabel.setAlign(Alignment.RIGHT);

		Tab treeTab = new Tab();
		treeTab.setPane(navigationTree);
		treeTab.setTitle("1");

		RecordClickHandler recordClickHandler = new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				Record record = event.getRecord();
				showSample(record);
			}
		};

		final NavigationSectionStack accordeonNav = new NavigationSectionStack(recordClickHandler);

		Tab accordeonTab = new Tab();
		accordeonTab.setPane(accordeonNav);
		accordeonTab.setTitle("2");

		// TODO: set Icons into the tabs instead of the title
		TabSet navTabSet = new TabSet();
		navTabSet.setHeight100();
		navTabSet.addTab(treeTab);
		navTabSet.addTab(accordeonTab);

		sideNavLayout.addMember(navTabSet);

		HLayout versionLayout = new HLayout();
		versionLayout.setWidth100();
		versionLayout.addMember(builtDateLabel);
		versionLayout.addMember(versionLabel);

		sideNavLayout.addMember(versionLayout);

		hLayout.addMember(sideNavLayout);

		mainTabSet = new TabSet();

		// default is 22. required to increase to that select tab
		// control
		// displays well
		mainTabSet.setTabBarThickness(24);
		mainTabSet.setWidth100();
		mainTabSet.setHeight100();
		mainTabSet.setCloseTabIcon("[SKIN]/Window/close.png");

		LayoutSpacer layoutSpacer = new LayoutSpacer();
		layoutSpacer.setWidth(5);

		SelectItem selectItem = new SelectItem();
		selectItem.setWidth(130);
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		valueMap.put("Enterprise", "Enterprise");
		valueMap.put("SilverWave", "Silver Wave");
		valueMap.put("BlackOps", "Black Ops");
		valueMap.put("TreeFrog", "Tree Frog");
		valueMap.put("fleet", "Fleet");

		selectItem.setValueMap(valueMap);

		String currentSkin = Cookies.getCookie("skin");
		if (currentSkin == null) {
			currentSkin = "SilverWave";
		}
		selectItem.setDefaultValue(currentSkin);
		selectItem.setShowTitle(false);
		selectItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				Cookies.setCookie("skin", (String) event.getValue());
				com.google.gwt.user.client.Window.Location.reload();
			}
		});

		DynamicForm form = new DynamicForm();
		form.setPadding(0);
		form.setMargin(0);
		form.setCellPadding(1);
		form.setNumCols(1);
		form.setFields(selectItem);

		mainTabSet.setTabBarControls(TabBarControls.TAB_SCROLLER, TabBarControls.TAB_PICKER, layoutSpacer, form);
		// mainTabSet.setTabBarControls(TabBarControls.TAB_SCROLLER,
		// TabBarControls.TAB_PICKER, layoutSpacer, form,
		// imgButton);

		final Menu contextMenu = createContextMenu();
		mainTabSet.addShowContextMenuHandler(new ShowContextMenuHandler() {
			public void onShowContextMenu(ShowContextMenuEvent event) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				if (selectedTab != 0) {
					contextMenu.showContextMenu();
				}
				event.cancel();
			}
		});

		Tab tab = new Tab();
		tab.setTitle("HWD NG&nbsp;&nbsp;");
		tab.setIcon("pieces/16/cube_green.png");

		final String title = "HWD NG - Latest News";
		Window window = new Window();
		window.setTitle(title);
		window.setHeaderControls(HeaderControls.HEADER_ICON, HeaderControls.HEADER_LABEL);
		window.setWidth(500);
		window.setHeight(375);

		Label titleLabel = new Label(messages.welcomeNewsTitle());
		titleLabel.setWidth100();
		titleLabel.setMargin(15);
		titleLabel.setHeight(30);

		Label newsLabel = new Label(messages.welcomeNews());
		newsLabel.setWidth100();
		newsLabel.setMargin(15);

		window.addItem(titleLabel);
		window.addItem(newsLabel);

		// CHRONOSCOPE
		// double GOLDEN_RATIO = 1.618;
		// int chartWidth = 600, chartHeight = (int) (chartWidth /
		// GOLDEN_RATIO);
		// Chronoscope.setFontBookRendering(true);
		// Basic basicDatasetRequest = new DatasetRequest.Basic();
		// basicDatasetRequest.setAxisId("blh");
		// basicDatasetRequest.setDomain(new double[] { 1d, 2d });
		// basicDatasetRequest.setRangeLabel("asjdfkl");
		//
		// // Dataset<MyTuple2D> dataset = new
		// // ChronoscopeDatasetFactory().create(basicDatasetRequest);
		// // Dataset<MyTuple2D>[] datasetArray = new
		// Dataset<MyTuple2D>[2];
		// XYDataSource dataSource =
		// XYDataSource.getInstance("datset.js");
		//
		// // dataset[0] =
		// Chronoscope.createXYDataset(getJson("dataset"));
		//
		// VerticalPanel vp = new VerticalPanel();
		// vp.add(new Label(dataset.getRangeLabel()));
		// ChartPanel chartPanel =
		// Chronoscope.createTimeseriesChart(dataset,
		// chartWidth, chartHeight);
		// vp.add(chartPanel);

		// Float[] data = new Float[] { new Float(0f), new Float(45f),
		// new
		// Float(35f), new Float(75f), new Float(100f) };
		// // Defining Line
		// Line line = new Line(new Data(data));
		// line.setLineStyle(new LineStyle(3, 1, 0));
		// line.setColor(Color.RED);
		// // Defining chart.
		// LineChart chart = new LineChart(new Line[] { line });
		// chart.setSize(400, 300);
		// chart.setTitle("Web Traffic|(in billions of hits)",
		// Color.RED, 14);
		// chart.addXAxisInfo(new AxisInfo(new String[] { "Nov", "Dec",
		// "Jan",
		// "Feb", "Mar" }));
		// chart.addXAxisInfo(new AxisInfo(new String[] { "2007",
		// "2007",
		// "2008", "2008", "2008" }));
		// chart.addYAxisInfo(new AxisInfo(new String[] { "", "25",
		// "50", "75",
		// "100" }));
		// // Defining background and chart fills.
		// chart.setBackgroundFill(new SolidFill(Color.LIGHTGREY));
		// LinearGradientFill fill = new LinearGradientFill(0,
		// Color.LIGHTBLUE,
		// 1);
		// fill.addColorAndOffset(Color.WHITE, 0);
		// chart.setAreaFill(fill);
		// // ChartWidget widget = new ChartWidget( chart );
		// String url = chart.createURLString();

		window.setKeepInParentRect(true);
		window.setTop(30);
		window.setLeft(30);
		window.setCanDragResize(true);

		HLayout mainPanel = new HLayout();
		mainPanel.setHeight100();
		mainPanel.setWidth100();

		mainPanel.addChild(window);

		tab.setPane(mainPanel);

		mainTabSet.addTab(tab);

		Canvas canvas = new Canvas();
		canvas.setBackgroundImage("[SKIN]/shared/background.gif");
		canvas.setWidth100();
		canvas.setHeight100();
		canvas.addChild(mainTabSet);

		hLayout.addMember(canvas);
		main.addMember(hLayout);
		main.draw();

		// Add history listener
		History.addHistoryListener(this);

		RootPanel.get("loadingMsg").getElement().setInnerHTML("");
	}

	private void setDateFormats() {

		DateUtil.setDateInputFormatter(new DateInputFormatter() {
			public Date parse(String dateString) {
				final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(SHORT_DATE_FORMAT);
				Date date = dateFormatter.parse(dateString);
				return date;
			}
		});

		DateUtil.setNormalDateDisplayFormatter(new DateDisplayFormatter() {
			public String format(Date date) {
				if (date == null) {
					return null;
				}
				final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(NORMAL_DATE_FORMAT);
				String format = dateFormatter.format(date);
				return format;
			}
		});

		DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
			public String format(Date date) {
				if (date == null) {
					return null;
				}
				final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(SHORT_DATE_FORMAT);
				String format = dateFormatter.format(date);
				return format;
			}
		});

	}

	/**
	 * needed for hibernate4gwt/gilead dynamic proxy mode
	 */
	private void forceDomainProxyGeneration() {
		GWT.create(BaseException.class);
		GWT.create(IdentificationException.class);
		GWT.create(Bet.class);
		// GWT.create(BetJoker.class);
		GWT.create(League.class);
		// GWT.create(LeagueType.class);
		GWT.create(Match.class);
		GWT.create(MatchDay.class);
		GWT.create(Role.class);
		GWT.create(Season.class);
		GWT.create(SimplePojo.class);
		GWT.create(Team.class);
		GWT.create(User.class);
	}

	/**
	 * test the GWT RPC services
	 */
	private void testServices() {
		// UserServiceAsync userServiceSpring = (UserServiceAsync)
		// GWT.create(UserService.class);
		// ((ServiceDefTarget)
		// userServiceSpring).setServiceEntryPoint(GWT.getModuleBaseURL() +
		// "/UserService.gwt");
		// userServiceSpring.getUserList(new DefaultCallback() {
		//
		// public void onSuccess(Object result) {
		// Log.debug"user service call successful: " + result);
		// List<User> users = (List<User>) result;
		// for (User user : users) {
		// Log.debug("user: " + user.getId());
		// }
		// }
		//
		// });

		League league = new League();
		TeamServiceAsync teamService = TeamService.Util.getInstance();
		teamService.getTeams(league, new DefaultCallback<List<Team>>() {

			public void onSuccess(List<Team> teams) {
				Log.debug("team service call successful: " + teams);
				for (Team team : teams) {
					int id = team.getId();
				}

			}

		});

		UserServiceAsync userService = (UserServiceAsync) GWT.create(UserService.class);
		((ServiceDefTarget) userService).setServiceEntryPoint(GWT.getModuleBaseURL() + "/UserService");
		userService.getUserList(new DefaultCallback() {

			public void onSuccess(Object result) {
				Log.debug("user service call successful: " + result);
				List<User> users = (List<User>) result;
				for (User user : users) {
					Log.debug("user: " + user);
					List<Role> roles = user.getRoles();
					if (roles != null) {
						for (Role role : roles) {
							int id = role.getId();
							String name = role.getName();
							Log.debug("user: " + user.getId() + ": " + id + ", " + name);
						}
					}
				}
			}

		});

		userService.getSimplePojo(new DefaultCallback() {

			public void onSuccess(Object result) {
				Log.debug("simplepojo service call successful: " + result);
				SimplePojo pojo = (SimplePojo) result;
				Log.debug("simplePojo: " + pojo.getId() + ", " + pojo.getLastName());
			}

		});
		userService.getSampleRole(new DefaultCallback() {

			public void onSuccess(Object result) {
				Log.debug("samplerole service call successful: " + result);
				Role role = (Role) result;
				Log.debug("samplerole: " + role.getId() + ", " + role.getName());
			}

		});
		userService.getSampleMatchDay(new DefaultCallback() {

			public void onSuccess(Object result) {
				Log.debug("sample matchday service call successful: " + result);
				MatchDay matchDay = (MatchDay) result;
				Log.debug("sample MatchDay: " + matchDay.getId() + ", " + matchDay.getOLId());
			}

		});

		StringReverserServiceAsync reverserService2 = (StringReverserServiceAsync) GWT
				.create(StringReverserService.class);
		((ServiceDefTarget) reverserService2).setServiceEntryPoint(GWT.getModuleBaseURL() + "/StringReverserService");

		reverserService2.reverseString("blablub", new DefaultCallback() {

			public void onSuccess(Object result) {

				// Get the service call response and display it
				String reverse = (String) result;
				Log.debug("success: " + reverse);
			}
		});
	}

	private static native JavaScriptObject getJson(String varName) /*-{ return $wnd[varName]; }-*/;

	private Menu createContextMenu() {
		Menu menu = new Menu();
		menu.setWidth(140);

		MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
			public boolean execute(Canvas target, Menu menu, MenuItem item) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				return selectedTab != 0;
			}
		};

		MenuItem closeItem = new MenuItem("<u>C</u>lose");
		closeItem.setEnableIfCondition(enableCondition);
		closeItem.setKeyTitle("Alt+C");
		KeyIdentifier closeKey = new KeyIdentifier();
		closeKey.setAltKey(true);
		closeKey.setKeyName("C");
		closeItem.setKeys(closeKey);
		closeItem.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				mainTabSet.removeTab(selectedTab);
				mainTabSet.selectTab(selectedTab - 1);
			}
		});

		MenuItem closeAllButCurrent = new MenuItem("Close All But Current");
		closeAllButCurrent.setEnableIfCondition(enableCondition);
		closeAllButCurrent.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				int selected = mainTabSet.getSelectedTabNumber();
				Tab[] tabs = mainTabSet.getTabs();
				int[] tabsToRemove = new int[tabs.length - 2];
				int cnt = 0;
				for (int i = 1; i < tabs.length; i++) {
					if (i != selected) {
						tabsToRemove[cnt] = i;
						cnt++;
					}
				}
				mainTabSet.removeTabs(tabsToRemove);
			}
		});

		MenuItem closeAll = new MenuItem("Close All");
		closeAll.setEnableIfCondition(enableCondition);
		closeAll.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				Tab[] tabs = mainTabSet.getTabs();
				int[] tabsToRemove = new int[tabs.length - 1];

				for (int i = 1; i < tabs.length; i++) {
					tabsToRemove[i - 1] = i;
				}
				mainTabSet.removeTabs(tabsToRemove);
				mainTabSet.selectTab(0);
			}
		});

		menu.setItems(closeItem, closeAllButCurrent, closeAll);
		return menu;
	}

	private void showSample(Record record) {

		boolean isExplorerTreeNode = record instanceof ExplorerTreeNode;
		if (record instanceof CommandTreeNode) {
			CommandTreeNode commandTreeNode = (CommandTreeNode) record;
			commandTreeNode.getCommand().execute();
		} else if (isExplorerTreeNode) {
			ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode) record;
			PanelFactory factory = explorerTreeNode.getFactory();
			if (factory != null) {
				String panelID = factory.getID();
				Tab tab = null;
				if (panelID != null) {
					String tabID = panelID + "_tab";
					tab = mainTabSet.getTab(tabID);
				}
				if (tab == null) {
					Canvas panel = factory.create();
					tab = new Tab();
					tab.setID(factory.getID() + "_tab");
					String sampleName = explorerTreeNode.getName();

					String icon = explorerTreeNode.getIcon();
					if (icon == null) {
						icon = "silk/plugin.png";
					}
					String imgHTML = Canvas.imgHTML(icon);
					tab.setTitle("<span>" + imgHTML + "&nbsp;" + sampleName + "</span>");
					tab.setPane(panel);
					tab.setCanClose(true);
					mainTabSet.addTab(tab);
					mainTabSet.selectTab(tab);
					if (!SC.isIE()) {
						if (mainTabSet.getNumTabs() == 10) {
							mainTabSet.removeTabs(new int[] { 1 });
						}
					}
					History.newItem(explorerTreeNode.getNodeID(), false);
				} else {
					mainTabSet.selectTab(tab);
				}
			}
		}
	}

	public void onHistoryChanged(String historyToken) {
		if (historyToken == null || historyToken.equals("")) {
			mainTabSet.selectTab(0);
		} else {
			ExplorerTreeNode[] showcaseData = navigationTree.getShowcaseData();
			for (ExplorerTreeNode explorerTreeNode : showcaseData) {
				if (explorerTreeNode.getNodeID().equals(historyToken)) {
					showSample(explorerTreeNode);
					navigationTree.selectRecord(explorerTreeNode);
					Tree tree = navigationTree.getData();
					TreeNode categoryNode = tree.getParent(explorerTreeNode);
					// TODO isRoot not working?
					// while (categoryNode != null &&
					// !tree.isRoot(categoryNode)) {
					while (categoryNode != null && !"/".equals(tree.getName(categoryNode))) {
						tree.openFolder(categoryNode);
						categoryNode = tree.getParent(categoryNode);
					}
				}
			}
		}
	}

	private StringReverserServiceAsync getReverserServiceInstance() {
		if (reverserService == null) {
			// Instantiate the service
			reverserService = (StringReverserServiceAsync) GWT.create(StringReverserService.class);

			// Specify the URL at which the service implementation is running.
			// The target URL must reside on the same domain and port from
			// which the host page was served.
			((ServiceDefTarget) reverserService)
					.setServiceEntryPoint(GWT.getModuleBaseURL() + "/StringReverserService");
		}
		return reverserService;
	}

}
