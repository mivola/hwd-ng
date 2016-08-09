package com.voigt.hwd.client.admin;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.admin.ds.TeamDataSourceManager;

public class TeamManager extends AbstractBasePanel {

    public static class Factory implements PanelFactory {
	private String id;

	public Canvas create() {
	    TeamManager panel = new TeamManager();
	    id = panel.getID();
	    return panel;
	}

	public String getID() {
	    return id;
	}

	public String getDescription() {
	    return "";
	}
    }

    @Override
    public Canvas getViewPanel() {

	HLayout layout = new HLayout();

	CompoundEditor compoundEditor = new CompoundEditor(new TeamDataSourceManager(), true, false);

	layout.addMember(compoundEditor);
	return layout;
    }

}
