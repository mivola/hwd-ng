/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * smartclient.com/license.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package com.voigt.hwd.client.navigation;

import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class NavigationTree extends TreeGrid {

	private final String idSuffix = "";

	private final ExplorerTreeNode[] navigationData = NavigationData.getData(idSuffix);

	public NavigationTree() {
		setWidth100();
		setHeight100();
		// setShowHeader(false);
		setCustomIconProperty("icon");
		setAnimateFolderTime(100);
		setAnimateFolders(true);
		setAnimateFolderSpeed(1000);
		setNodeIcon("silk/application_view_list.png");
		setShowConnectors(true);
		setShowSortArrow(SortArrow.CORNER);
		setShowAllRecords(true);
		setLoadDataOnDemand(false);
		setCanSort(false);
		// setShowFilterEditor(true);

		// also works
		// setDataSource(new HwdDS("side"));
		// setAutoFetchData(true);

		TreeGridField field = new TreeGridField();
		field.setCanFilter(true);
		field.setName("name");
		field.setTitle("<b>HWD NG</b>");
		setFields(field);

		Tree tree = new Tree();
		tree.setModelType(TreeModelType.PARENT);
		tree.setNameProperty("name");
		tree.setOpenProperty("isOpen");
		tree.setIdField("nodeID");
		tree.setParentIdField("parentNodeID");
		tree.setRootValue("root" + idSuffix);
		tree.setShowRoot(true);

		tree.setData(navigationData);

		setData(tree);
	}

	public ExplorerTreeNode[] getShowcaseData() {
		return navigationData;
	}
}
