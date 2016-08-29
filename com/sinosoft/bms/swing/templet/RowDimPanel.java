/**
 *
 * Created on 2009-5-4
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.entity.BmsTpRowDim;
import com.sinosoft.bms.swing.common.BmsCheckTreeEditor;
import com.sinosoft.bms.swing.common.BmsCheckTreeNode;
import com.sinosoft.bms.swing.common.BmsCheckTreeRenderer;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.ItemVO;

/**
 * @author sunrui
 *
 */
public class RowDimPanel extends JPanel {

	public JScrollPane spTree = new JScrollPane();
	public JTree itemTree = new JTree();
	
	
	public BmsCheckTreeNode rootNode = null;
	/**
	 * 
	 */
	public RowDimPanel() {
		super();
		init();
	}


	public void init() {
		this.setLayout(new BorderLayout());
		this.add(spTree,BorderLayout.CENTER);
		spTree.setViewportView(itemTree);
		
		
	}
	
	public void setItems(BmsItem [] items) throws Exception {
		ItemVO [] itemvos = new ItemVO[items.length];
		for (int i = 0; i < itemvos.length; i++) {
			itemvos[i] = new ItemVO(items[i]);
		}
		rootNode = new BmsCheckTreeNode("Ô¤ËãÏîÄ¿");
		ClientUtils.TreevoToNode(rootNode, itemvos);
		DefaultTreeModel tm = new DefaultTreeModel(rootNode);
		itemTree.setModel(tm);
		itemTree.setEditable(true);
		itemTree.setCellRenderer(new BmsCheckTreeRenderer());
		itemTree.setCellEditor(new BmsCheckTreeEditor());
	}
	
	public void scanNodesForSetData(BmsCheckTreeNode parentNode,HashMap hmRowDim) throws Exception {
		if(parentNode!=rootNode) {
			Object userObject = parentNode.getUserObject();
			if(userObject!=null && userObject instanceof ItemVO) {
				ItemVO itemVO = (ItemVO)userObject;
				if(hmRowDim.containsKey(new Integer(itemVO.getID()))) {
					parentNode.setSelected(true);
				} else {
					parentNode.setSelected(false);
				}
			}
		}
		
		for (int i = 0; i < parentNode.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)parentNode.getChildAt(i);
			scanNodesForSetData(child, hmRowDim);
		}
	}
	
	public void setRowDims(BmsTpRowDim [] rowDims) throws Exception {
		HashMap hmRowDim = new HashMap();
		for (int i = 0; i < rowDims.length; i++) {
			hmRowDim.put(new Integer(rowDims[i].getBmsItem().getItemId()), new Object());
		}
		rootNode.setSelected(false);
		scanNodesForSetData(rootNode, hmRowDim);
		itemTree.treeDidChange();
	}
		
	public BmsTpRowDim [] getRowDims() throws Exception {
		ItemVO [] vos = getSelectedItems();
		BmsTpRowDim [] rowDims = new BmsTpRowDim[vos.length];
		for (int i = 0; i < rowDims.length; i++) {
			rowDims[i] = new BmsTpRowDim();
			rowDims[i].setBmsItem(vos[i].getBmsItem());
		}
		
		return rowDims;
	}
	
	public ItemVO [] getSelectedItems() throws Exception {
		Vector vItem = new Vector();
		scanItems(rootNode, vItem);
		ItemVO [] vos = new ItemVO[vItem.size()];
		vItem.copyInto(vos);
		return vos;
	}
	
	public void scanItems(BmsCheckTreeNode node,Vector vItem) throws Exception {
		if(node.isSelected()) {
			Object userObject = node.getUserObject();
			if(userObject!=null && userObject instanceof ItemVO) {
				vItem.add(userObject);
			}
		}
		
		for (int i = 0; i < node.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)node.getChildAt(i);
			scanItems(child, vItem);
		}
		
	}

}
