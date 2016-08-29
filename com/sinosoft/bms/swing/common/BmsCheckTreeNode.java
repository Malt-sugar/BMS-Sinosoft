/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;



import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author sunrui
 * 
 */
public class BmsCheckTreeNode extends DefaultMutableTreeNode {

	public final static int SINGLE_SELECTION = 0;

	public final static int DIG_IN_SELECTION = 4;

	protected int selectionMode;

	protected boolean isSelected=false;

	protected boolean expanded;

	public BmsCheckTreeNode() {
		this(null);
	}

	public BmsCheckTreeNode(Object userObject) {
		this(userObject, true, false);
	}

	public BmsCheckTreeNode(Object userObject, boolean allowsChildren,
			boolean isSelected) {
		super(userObject, allowsChildren);
		this.isSelected = isSelected;
		setSelectionMode(DIG_IN_SELECTION);
	}

	public void setSelectionMode(int mode) {
		selectionMode = mode;
	}

	public int getSelectionMode() {
		return selectionMode;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		
		if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
			Enumeration e = children.elements();
			while (e.hasMoreElements()) {
				BmsCheckTreeNode node = (BmsCheckTreeNode) e.nextElement();
				node.setSelected(isSelected);
			}
		}
	}
	
	public void setSelected(boolean isSelected,JTree tree) {
		this.isSelected = isSelected;
		if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
			Enumeration e = children.elements();
			while (e.hasMoreElements()) {
				BmsCheckTreeNode node = (BmsCheckTreeNode) e.nextElement();
				node.setSelected(isSelected,tree);
			}
		}
	}

	public void setExpanded(boolean isExpanded) {
		this.expanded = isExpanded;

//		if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
//			Enumeration e = children.elements();
//			while (e.hasMoreElements()) {
//				BmsCheckTreeNode node = (BmsCheckTreeNode) e.nextElement();
//				node.setExpanded(isExpanded);
//			}
//		}
	}

	public boolean isExpanded() {
		return expanded;
	}

	public boolean isSelected() {
		return isSelected;
	}
}
