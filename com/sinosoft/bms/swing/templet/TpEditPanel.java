/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.JTabbedPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.service.bd.Dim;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.TempleteVO;

/**
 * @author sunrui
 *
 */
public class TpEditPanel extends JPanel {

	private JSplitPane mainSplitPanel = null;
	private JPanel leftPanel = null;
	private JPanel rigthPanel = null;
	private JTree tpTree = null;
	private JTabbedPane tpTabPanel = null;
	
	public TpBasicInfoPanel basicInfoPanel = new TpBasicInfoPanel();
	public TpBgObjPanel bgObjPanel = new TpBgObjPanel();
	public ParamDimPanel paramDimPanel = new ParamDimPanel();
	
	public Dim dimBean = null;
	public Templete tpBean = null;

	/**
	 * 
	 */
	public TpEditPanel() {
		super();
		initialize();
	}

	public TpEditPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initialize();
	}

	/**
	 * @param layout
	 */
	public TpEditPanel(LayoutManager layout) {
		super(layout);
		initialize();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public TpEditPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initialize();
	}
	
	public void setBmsTemplete(BmsTemplet [] tps) throws Exception {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("预算表样");
		DefaultMutableTreeNode planTpNode = new DefaultMutableTreeNode("编制类表样");
		DefaultMutableTreeNode anlyseTpNode = new DefaultMutableTreeNode("分析类表样");
		rootNode.add(planTpNode);
		rootNode.add(anlyseTpNode);
		
		
		for (int i = 0; i < tps.length; i++) {
			Character type = tps[i].getTpType();
			TempleteVO tpvo = new TempleteVO(tps[i]);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(tpvo);
			if(type==null||type.charValue()=='0') {
				planTpNode.add(node);
			} else {
				anlyseTpNode.add(node);
			}
		}
		
		DefaultTreeModel tm = new DefaultTreeModel(rootNode);
		getTpTree().setModel(tm);
		
		ClientUtils.expandAll(getTpTree(), new TreePath(rootNode), true);
		
		setRightVisible(false);
	}
	

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(520, 322));
		this.setLayout(new BorderLayout());
		this.add(getMainSplitPanel(), BorderLayout.CENTER);
		
		
		try {
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");
			dimBean = (Dim) ClientBeanFactory.getBean("Dim");
			
			DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("预算表样");
			DefaultMutableTreeNode anlyseTpNode = new DefaultMutableTreeNode("分析类表样");
			DefaultMutableTreeNode planTpNode = new DefaultMutableTreeNode("编制类表样");
			rootNode.add(anlyseTpNode);
			rootNode.add(planTpNode);
			DefaultTreeModel tm = new DefaultTreeModel(rootNode);
			getTpTree().setModel(tm);
						
			BmsDim [] dims = dimBean.queryDim(null);
			BmsDimMember [] members = dimBean.queryDimMem(null);
			paramDimPanel.setDims(dims, members);
			
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	

	/**
	 * This method initializes mainSplitPanel	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getMainSplitPanel() {
		if (mainSplitPanel == null) {
			mainSplitPanel = new JSplitPane();
			mainSplitPanel.setDividerLocation(180);
			mainSplitPanel.setDividerSize(8);
			mainSplitPanel.setOneTouchExpandable(false);
			mainSplitPanel.setLeftComponent(getLeftPanel());
			mainSplitPanel.setRightComponent(getRigthPanel());
		}
		return mainSplitPanel;
	}

	/**
	 * This method initializes leftPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(getTpTree(), BorderLayout.CENTER);
		}
		return leftPanel;
	}

	/**
	 * This method initializes rigthPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRigthPanel() {
		if (rigthPanel == null) {
			rigthPanel = new JPanel();
			rigthPanel.setLayout(new BorderLayout());
			rigthPanel.setPreferredSize(new Dimension(300, 1));
			rigthPanel.add(getTpTabPanel(), BorderLayout.CENTER);
		}
		return rigthPanel;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getTpTree() {
		if (tpTree == null) {
			tpTree = new JTree();
			tpTree.addTreeSelectionListener(new TreeSelectionListener(){

				public void valueChanged(TreeSelectionEvent e) {
					onTreeSelected();
				}
			});
		}
		return tpTree;
	}
	
	public void onTreeSelected() {
		try {
			BmsTemplet tp = getSelectTemplet();
			if(tp!=null) {
				getBasicInfoPanel().setBmsTemplet(tp);
				
				TempleteVO tvo = getSelectTempletVO();
				
				//加载表样关联主体
				if(tvo.getBmsTpBgObjs()==null) {
					BmsTpBgObj [] tpBgObjs = tpBean.queryBmsTpBgObj(tp.getTpId());
					tvo.setBmsTpBgObjs(tpBgObjs);
				}
				getBgObjPanel().setTpBgObjs(tvo.getBmsTpBgObjs());
				
				//加载参数维度
				if(tvo.getBmsTpParamDims()==null) {
					BmsTpParamDim [] tpParamDims = tpBean.queryBmsTpParamDim(tp.getTpId());
					tvo.setBmsTpParamDims(tpParamDims);
				}
				getParamDimPanel().setTpParamDims(tvo.getBmsTpParamDims());
				
				if(tvo.getColDims()==null||tvo.getRowDims()==null) {
					
					tvo.setColDims(tpBean.queryBmsColDim(tp.getTpId()));
					tvo.setRowDims(tpBean.queryBmsRowDim(tp.getTpId()));
					
				}
				
				setRightVisible(true);
			} else {
				setRightVisible(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ClientUtils.showErrMsg(this, ex.getMessage());
		}
	}
	
	public TempleteVO getSelectTempletVO() {
		TreePath path = tpTree.getSelectionPath();
		if(path!=null) {
			Object objNode = path.getLastPathComponent();
			if(objNode!=null && objNode instanceof DefaultMutableTreeNode) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)objNode;
				Object objUserObject = node.getUserObject();
				if(objUserObject!=null && objUserObject instanceof TempleteVO) {
					TempleteVO vo = (TempleteVO)objUserObject;
					return vo;
				}
			}
		}
		
		return null;
	}
	
	public BmsTemplet getSelectTemplet() {
		TreePath path = tpTree.getSelectionPath();
		if(path!=null) {
			Object objNode = path.getLastPathComponent();
			if(objNode!=null && objNode instanceof DefaultMutableTreeNode) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)objNode;
				Object objUserObject = node.getUserObject();
				if(objUserObject!=null && objUserObject instanceof TempleteVO) {
					TempleteVO vo = (TempleteVO)objUserObject;
					return vo.getBmsTemplet();
				}
			}
		}
		
		return null;
	}

	/**
	 * This method initializes tpTabPanel	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTpTabPanel() {
		if (tpTabPanel == null) {
			tpTabPanel = new JTabbedPane();
			tpTabPanel.add("基本信息", basicInfoPanel);
			tpTabPanel.add("下发主体",bgObjPanel);
			tpTabPanel.add("参数维度",paramDimPanel);
		}
		return tpTabPanel;
	}
	
	public void setRightVisible(boolean visible) {
		Component [] comps = getRigthPanel().getComponents();
		for (int i = 0; i < comps.length; i++) {
			comps[i].setVisible(visible);
		}
		
	}
	
	public void setRightEnabled(boolean enabled) {
		Component [] comps = getBasicInfoPanel().getComponents();
		for (int i = 0; i < comps.length; i++) {
			if(!(comps[i] instanceof JLabel)) {
				comps[i].setEnabled(enabled);
			}
		}
		
		try {
			bgObjPanel.bgobjTree.setEditable(enabled);
			paramDimPanel.dimTree.setEditable(enabled);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}

	/**
	 * @return the basicInfoPanel
	 */
	public TpBasicInfoPanel getBasicInfoPanel() {
		return basicInfoPanel;
	}

	/**
	 * @param basicInfoPanel the basicInfoPanel to set
	 */
	public void setBasicInfoPanel(TpBasicInfoPanel basicInfoPanel) {
		this.basicInfoPanel = basicInfoPanel;
	}

	/**
	 * @return the bgObjPanel
	 */
	public TpBgObjPanel getBgObjPanel() {
		return bgObjPanel;
	}

	/**
	 * @param bgObjPanel the bgObjPanel to set
	 */
	public void setBgObjPanel(TpBgObjPanel bgObjPanel) {
		this.bgObjPanel = bgObjPanel;
	}
	
	public void setLeftEnabled(boolean b) {
		tpTree.setEnabled(b);
	}

	/**
	 * @return the paramDimPanel
	 */
	public ParamDimPanel getParamDimPanel() {
		return paramDimPanel;
	}

	/**
	 * @param paramDimPanel the paramDimPanel to set
	 */
	public void setParamDimPanel(ParamDimPanel paramDimPanel) {
		this.paramDimPanel = paramDimPanel;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
