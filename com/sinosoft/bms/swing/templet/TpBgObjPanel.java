/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;


import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.service.bd.BgObj;
import com.sinosoft.bms.swing.common.BmsCheckTreeEditor;
import com.sinosoft.bms.swing.common.BmsCheckTreeNode;
import com.sinosoft.bms.swing.common.BmsCheckTreeRenderer;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.ObjVO;

/**
 * @author sunrui
 *
 */
public class TpBgObjPanel extends JPanel {

	public JScrollPane spTree = new JScrollPane();
	public JTree bgobjTree = new JTree();
	public BgObj bgobjBean = null;
	
	public BmsCheckTreeNode rootNode = null;
	public ObjVO [] objvos = null;
	public HashMap hmObj = null; //bgobjid->parentid
	/**
	 * 
	 */
	public TpBgObjPanel() {
		init();
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add(spTree,BorderLayout.CENTER);
		spTree.setViewportView(bgobjTree);
		try {
			bgobjBean = (BgObj) ClientBeanFactory.getBean("BgObj");
			List objList = bgobjBean.queryBgObj();
			objvos = new ObjVO[objList.size()];
			hmObj = new HashMap();
			for (int i = 0; i < objvos.length; i++) {
				objvos[i] = new ObjVO((BmsObject)objList.get(i));
				hmObj.put(new Integer(objvos[i].getID()), new Integer(objvos[i].getParentID()));
			}
			rootNode = new BmsCheckTreeNode("预算主体");
			ClientUtils.TreevoToNode(rootNode, objvos);
			DefaultTreeModel tm = new DefaultTreeModel(rootNode);
			bgobjTree.setModel(tm);
			bgobjTree.setEditable(true);
			bgobjTree.setCellRenderer(new BmsCheckTreeRenderer());
			bgobjTree.setCellEditor(new BmsCheckTreeEditor());
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	
	public BmsTpBgObj [] getTpBgObjs() throws Exception {
		
		Vector vTpBgObj = new Vector();
		scanNodesForTpBgObjs(rootNode, vTpBgObj);
		BmsTpBgObj [] vos = new BmsTpBgObj[vTpBgObj.size()];
		vTpBgObj.copyInto(vos);
		
		//产生末级标志
		for (int i = 0; i < vos.length; i++) {
			
			boolean leafFlag = true;
			
			for (int j = 0; j < vos.length; j++) {
				Object objParentID = hmObj.get(new Integer(vos[j].getBmsObject().getBgObjId()));
				if(objParentID!=null) {
					int parentid = ((Integer)objParentID).intValue();
					if(parentid==vos[i].getBmsObject().getBgObjId()) {
						leafFlag=false;
						break;
					}
				}
			}
			vos[i].setLeafFlag(BmsUtils.boolToChar(leafFlag));
		}
		
		return vos;
	}
	
	public void scanNodesForTpBgObjs(BmsCheckTreeNode parentNode,Vector vTpBgObj) throws Exception {
		if(parentNode!=rootNode) {
			if(parentNode.isSelected()) {
				Object userObject = parentNode.getUserObject();
				if(userObject!=null && userObject instanceof ObjVO) {
					ObjVO objVO = (ObjVO)userObject;
					BmsTpBgObj tpbgobj = new BmsTpBgObj();
					tpbgobj.setBmsObject(objVO.getBmsObject());
					vTpBgObj.add(tpbgobj);
				}
			}
		}
		
		for (int i = 0; i < parentNode.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)parentNode.getChildAt(i);
			scanNodesForTpBgObjs(child, vTpBgObj);
		}
		
		
	}
	
	public void setTpBgObjs(BmsTpBgObj [] vos) throws Exception {
		HashMap hmTpBgObj = new HashMap();
		for (int i = 0; i < vos.length; i++) {
			hmTpBgObj.put(new Integer(vos[i].getBmsObject().getBgObjId()), new Object());
		}
		rootNode.setSelected(false);
		scanNodesForSetData(rootNode, hmTpBgObj);
		bgobjTree.treeDidChange();
	}

	public void scanNodesForSetData(BmsCheckTreeNode parentNode,HashMap hmTpBgObj) throws Exception {
		if(parentNode!=rootNode) {
			Object userObject = parentNode.getUserObject();
			if(userObject!=null && userObject instanceof ObjVO) {
				ObjVO objVO = (ObjVO)userObject;
				if(hmTpBgObj.containsKey(new Integer(objVO.getID()))) {
					parentNode.setSelected(true);
				} else {
					parentNode.setSelected(false);
				}
			}
		}
		for (int i = 0; i < parentNode.getChildCount(); i++) {
			BmsCheckTreeNode child = (BmsCheckTreeNode)parentNode.getChildAt(i);
			scanNodesForSetData(child, hmTpBgObj);
		}
	}

}
