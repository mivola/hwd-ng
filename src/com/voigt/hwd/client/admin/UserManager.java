package com.voigt.hwd.client.admin;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.admin.ds.UserDataSourceManager;

public class UserManager extends AbstractBasePanel {

    public static class Factory implements PanelFactory {
	private String id;

	public Canvas create() {
	    UserManager panel = new UserManager();
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

	CompoundEditor compoundEditor = new CompoundEditor(new UserDataSourceManager(), true, true);

	layout.addMember(compoundEditor);
	return layout;
    }

}
