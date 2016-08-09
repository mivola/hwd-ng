package com.voigt.hwd.client.grid.history;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.HeaderControls;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HistoryOverviewWindow extends Window {

	private final HistoryOverviewData data;

	public HistoryOverviewWindow(HistoryOverviewData data) {
		this.data = data;

		createHistoryOverview();
	}

	private void createHistoryOverview() {

		this.setWidth100();
		// descWindow.setMaxHeight(20);
		this.setMargin(10);
		this.setMembersMargin(10);
		this.setTitle(data.getTitle());
		this.setCanDrag(false);
		this.setCanDragReposition(false);
		this.setCanDragResize(false);
		this.setHeaderControls(HeaderControls.HEADER_ICON, HeaderControls.HEADER_LABEL);

		Label descLabel = new Label(data.getDescription());
		descLabel.setWidth100();
		descLabel.setMargin(15);

		final VLayout tabLayout = new VLayout();
		tabLayout.setWidth100();
		tabLayout.addMember(descLabel);

		// add table to display detail data
		addDetailData(tabLayout);

		this.addChild(tabLayout);

	}

	private void addDetailData(Layout layout) {

		// winner
		String winnerKey = "Sieger";
		String winnerValue = data.getWinner();
		Label winnerKeyLabel = createKeyLabel(winnerKey);
		Label winnerValueLabel = createValueLabel(winnerValue);
		addWidgets(layout, winnerKeyLabel, winnerValueLabel);

		// maximum points
		String maxPointsKey = "Maximalpunktzahl";
		String maxPointsValue = data.getMaxPoints();
		Label maxPointsKeyLabel = createKeyLabel(maxPointsKey);
		Label maxPointsValueLabel = createValueLabel(maxPointsValue);
		addWidgets(layout, maxPointsKeyLabel, maxPointsValueLabel);

		// cntParticipants
		String cntParticipantsKey = "Teilnehmer";
		String cntParticipantsValue = "" + data.getCntParticipants();
		Label cntParticipantsKeyLabel = createKeyLabel(cntParticipantsKey);
		Label cntParticipantsValueLabel = createValueLabel(cntParticipantsValue);
		addWidgets(layout, cntParticipantsKeyLabel, cntParticipantsValueLabel);

		// screenshot
		String screenKey = "Endergebnis";
		Label screenKeyLabel = createKeyLabel(screenKey);
		Widget button = createShowScreenshotButton();
		addWidgets(layout, screenKeyLabel, button);

		// link
		String linkKey = "Live-System";
		Label linkKeyLabel = createKeyLabel(linkKey);
		Widget link = createUrlLink();
		addWidgets(layout, linkKeyLabel, link);

	}

	private void addWidgets(Layout layout, Widget key, Widget value) {
		Label spacer = new Label("  ");
		spacer.setWidth(20);

		HLayout hLayout = new HLayout();
		hLayout.setHeight(20);
		hLayout.setAlign(VerticalAlignment.CENTER);
		hLayout.setLeft(15);

		hLayout.addMember(key);
		hLayout.addMember(spacer);
		hLayout.addMember(value);
		layout.addMember(hLayout);
	}

	private Label createKeyLabel(String labelValue) {
		Label label = new Label(labelValue);
		label.setAlign(Alignment.RIGHT);
		return label;
	}

	private Label createValueLabel(String labelValue) {
		Label label = new Label(labelValue);
		label.setAlign(Alignment.LEFT);
		return label;
	}

	private Widget createUrlLink() {

		if (data.getUrl() == null || "".equals(data.getUrl())) {
			Label noLinkLabel = new Label("keine URL vorhanden");
			noLinkLabel.setTitle("nicht vorhanden");
			noLinkLabel.setAutoFit(true);
			return noLinkLabel;
		} else {
			LinkItem link = new LinkItem();
			link.setName("linkToUrl");
			link.setTitle("");
			link.setValue(data.getUrl());
			link.setTarget("_blank");
			link.setPrompt("open in new Window");

			DynamicForm form = new DynamicForm();
			form.setFields(link);
			form.setMargin(0);
			form.setPadding(0);

			return form;
		}

	}

	private IButton createShowScreenshotButton() {

		IButton showScreenshotButton = new IButton("Öffne Screenshot");
		// showScreenshot.setMargin(15);
		// showScreenshotButton.setLeft(15);

		if (data.getImageFilename() == null || data.getImageFilename().equals("")) {

			showScreenshotButton.setTitle("nicht vorhanden");
			showScreenshotButton.setDisabled(true);

		} else {

			final String imgUrl = "hwd/history/" + data.getImageFilename();

			showScreenshotButton.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {

					final Window modalImgWindow = new Window();
					modalImgWindow.setShowModalMask(true);
					modalImgWindow.setTitle("");

					int clientHeight = com.google.gwt.user.client.Window.getClientHeight();
					int clientWidth = com.google.gwt.user.client.Window.getClientWidth();

					if (clientWidth < data.getImageWidth()) {
						modalImgWindow.setWidth("80%");
					} else {
						modalImgWindow.setWidth(data.getImageWidth() + 30);
					}
					if (clientHeight < data.getImageHeight()) {
						modalImgWindow.setHeight("80%");
					} else {
						modalImgWindow.setHeight(data.getImageHeight() + 30);
					}
					modalImgWindow.setShowMinimizeButton(false);
					modalImgWindow.setShowCloseButton(true);
					modalImgWindow.setIsModal(true);
					modalImgWindow.centerInPage();

					modalImgWindow.addCloseClickHandler(new CloseClickHandler() {
						public void onCloseClick(CloseClickEvent event) {
							modalImgWindow.destroy();
						}
					});

					HLayout vLayout = new HLayout(15);
					vLayout.setWidth100();
					vLayout.setHeight100();

					Img img = new Img(imgUrl);
					img.setWidth(data.getImageWidth());
					img.setHeight(data.getImageHeight());
					img.setImageType(ImageStyle.STRETCH);
					img.setOverflow(Overflow.AUTO);

					img.setAlign(Alignment.CENTER);
					vLayout.setAlign(Alignment.CENTER);

					img.addClickHandler(new ClickHandler() {

						public void onClick(ClickEvent event) {
							modalImgWindow.destroy();
						}

					});

					modalImgWindow.addItem(vLayout);
					vLayout.addChild(img);

					modalImgWindow.show();
				}

			});

		}

		showScreenshotButton.setAutoFit(true);
		showScreenshotButton.setAlign(Alignment.CENTER);

		return showScreenshotButton;

	}
}
