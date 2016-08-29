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

import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.swing.common.BmsCheckTreeEditor;
import com.sinosoft.bms.swing.common.BmsCheckTreeNode;
import com.sinosoft.bms.swing.common.BmsCheckTreeRenderer;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.DimVO;

/**
 * @author sunrui
 *
 */
public class ParamDimPanel extends JPanel {

	public JScrollPane spTree = new JScrollPane();
	public JTree dimTree = new JTree();
	public BmsCheckTreeNode rootNode = null;
	
	/**
	 * 
	 */
	public ParamDimPanel() {
		super();
		init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		this.add(spTree,BorderLayout.CENTER);
		spTree.setViewportView(dimTree);
	}
	

	
	public void scanNodesForParamDims(BmsCheckTreeNode parentNode,Vector vDim) throws Exception {
		if(rootNode!=parentNode) {
			if(parentNode.isSelected()) {
				Object userObject = parentNode.getUserObject();
				if(userObject!=null && userObject instanceof DimVO) {
					DimVO dimvo = (DimVO) userObject;
					if(dimvo.getType()==DimVO.TYPE_MEMBER) {
						BmsDimMember dimmem = dimvo.getMember();
						BmsTpParamDim paramDim = new BmsTpParamDim();
						paramDim.setBmsDim(dimmem.getBmsDim());
						paramDim.setBmsDimMember(dimmem);
						vDim.add(paramDim);
					}
				}
			}
		}
		
		for (int i = 0; i < parentNode.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)parentNode.getChildAt(i);
			scanNodesForParamDims(child, vDim);
		}
	}
	
	public BmsTpParamDim [] getTpParamDims() throws Exception {
		Vector vDim = new Vector();
		scanNodesForParamDims(rootNode, vDim);
		
		BmsTpParamDim [] vos = new BmsTpParamDim[vDim.size()];
		vDim.copyInto(vos);
		
		return vos;
	}
	
	public void setTpParamDims(BmsTpParamDim [] vos) throws Exception {
		HashMap hmDim = new HashMap();
		HashMap hmDimMember = new HashMap();
		for (int i = 0; i < vos.length; i++) {
			hmDim.put(new Integer(vos[i].getBmsDim().getDimId()), new Object());
			hmDimMember.put(new Integer(vos[i].getBmsDimMember().getDimMemId()), new Object());
		}
		rootNode.setSelected(false);
		scanNodesForSetData(rootNode, hmDim,hmDimMember);
		dimTree.treeDidChange();
	}
	
	public void scanNodesForSetData(BmsCheckTreeNode parentNode,HashMap hmDim,HashMap hmDimMember) throws Exception {
		if(parentNode!=rootNode) {
			Object userObject = parentNode.getUserObject();
			if(userObject!=null && userObject instanceof DimVO) {
				DimVO dimvo = (DimVO)userObject;
				if(dimvo.getType()==DimVO.TYPE_DIM) {
					if(hmDim.containsKey(new Integer(dimvo.getDim().getDimId()))) {
						parentNode.setSelected(true);
					} else {
						parentNode.setSelected(false);
					}
				} else {
					if(hmDimMember.containsKey(new Integer(dimvo.getMember().getDimMemId()))) {
						parentNode.setSelected(true);
					} else {
						parentNode.setSelected(false);
					}
				}
			}
		}
		for (int i = 0; i < parentNode.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)parentNode.getChildAt(i);
			scanNodesForSetData(child, hmDim,hmDimMember);
		}
	}
	
	public void setDims(BmsDim [] dims,BmsDimMember [] members) throws Exception {
		Vector vDimVO = new Vector();
		for (int i = 0; i < dims.length; i++) {
			DimVO vo = new DimVO(dims[i]);
			vDimVO.add(vo);
		}
		for (int i = 0; i < members.length; i++) {
			DimVO vo = new DimVO(members[i]);
			vDimVO.add(vo);
		}
		
		DimVO [] vos = new DimVO[vDimVO.size()];
		vDimVO.copyInto(vos);
		
		rootNode = new BmsCheckTreeNode("Ô¤ËãÎ¬¶È");
		ClientUtils.TreevoToNode(rootNode, vos);
		DefaultTreeModel tm = new DefaultTreeModel(rootNode);
		dimTree.setModel(tm);
		dimTree.setEditable(true);
		dimTree.setCellRenderer(new BmsCheckTreeRenderer());
		dimTree.setCellEditor(new BmsCheckTreeEditor());
		
	}
	
	public DimVO [] getSelectedDims() throws Exception {
		Vector vDim = new Vector();
		scanDims(rootNode, vDim);
		DimVO [] dims = new DimVO[vDim.size()];
		vDim.copyInto(dims);
		return dims;
	}
	
	public void scanDims(BmsCheckTreeNode node,Vector vDim) throws Exception {
		if(node.isSelected()) {
			Object userObject = node.getUserObject();
			if(userObject!=null && userObject instanceof DimVO) {
				vDim.add(userObject);
			}
		}
		
		for (int i = 0; i < node.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)node.getChildAt(i);
			scanDims(child, vDim);
		}
		
	}
}
