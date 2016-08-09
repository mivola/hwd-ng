package com.voigt.hwd.client.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class NavigationSectionStack extends SectionStack {

	private final String idSuffix = "";

	private final ExplorerTreeNode[] navigationData = NavigationData.getData(idSuffix);

	/* nodeId, section (without the content) */
	private final Map<String, SectionStackSection> sections = new HashMap<String, SectionStackSection>();

	/* parentNodeId, list of nodes */
	private final Map<String, List<ExplorerTreeNode>> sectionTreeNodes = new HashMap<String, List<ExplorerTreeNode>>();

	public NavigationSectionStack(RecordClickHandler recordClickHandler) {

		this.setHeight100();

		this.setVisibilityMode(VisibilityMode.MULTIPLE);
		this.setBorder("2px solid");

		for (ExplorerTreeNode node : navigationData) {

			if (node.getParentNodeID().equals(NavigationData.ROOT)) {
				// create a new section
				SectionStackSection section = new SectionStackSection(node.getName());
				section.setID(node.getNodeID());
				sections.put(node.getNodeID(), section);
			} else {

				// get the map entry for the parent of this node
				String parentNodeID = node.getParentNodeID();

				List<ExplorerTreeNode> sectionNodes = sectionTreeNodes.get(parentNodeID);
				if (sectionNodes == null) {
					sectionNodes = new ArrayList<ExplorerTreeNode>();
				}

				// add the new node to the list
				sectionNodes.add(node);

				sectionTreeNodes.put(parentNodeID, sectionNodes);

			}

		}

		// add all sections to the stack
		Set<String> keySet = sections.keySet();
		for (String key : keySet) {

			SectionStackSection section = sections.get(key);

			// create new grid
			final ListGrid listGrid = new ListGrid();
			listGrid.setCanEdit(false);
			listGrid.setShowHeader(false);
			listGrid.setShowHeaderContextMenu(false);
			listGrid.setShowSortArrow(SortArrow.NONE);
			listGrid.setShowTreeColumnPicker(false);
			listGrid.setShowHeaderMenuButton(false);

			// add one column that refers the name attribute of the
			// ExplorerTreeNode
			List<ExplorerTreeNode> sectionNodes = sectionTreeNodes.get(key);
			listGrid.setFields(new ListGridField("name", "Name"));

			// add the data
			if (sectionNodes != null) {
				ListGridRecord[] arrayOfRecords = sectionNodes.toArray(new ListGridRecord[sectionNodes.size()]);
				listGrid.setData(arrayOfRecords);
			}

			listGrid.addRecordClickHandler(recordClickHandler);

			// add the grid to the section and the section to the stack
			section.setItems(listGrid);
			this.addSection(section);
		}

	}

}
