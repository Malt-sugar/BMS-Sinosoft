/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.gather;

import java.awt.Label;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.service.bd.BgObj;
import com.sinosoft.bms.service.bd.BmsItemObj;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsCheckTreeEditor;
import com.sinosoft.bms.swing.common.BmsCheckTreeNode;
import com.sinosoft.bms.swing.common.BmsCheckTreeRenderer;
import com.sinosoft.bms.swing.common.ClientEnv;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.ColEditableTableModel;
import com.sinosoft.bms.valueobject.ItemVO;


/**
 * @author sunrui
 *
 */
public class GatherEditPanel extends JPanel {

	public JLabel lblSheetCode = new JLabel("汇总表编码");
	public JTextField txtSheetCode = new JTextField();
	
	public JLabel lblSheetName = new JLabel("汇总表名称");
	public JTextField txtSheetName = new JTextField();
	
	public Label lblSheet = new Label("请选择要进行汇总的表：");
	public JScrollPane spSheet = new JScrollPane();
	public JTable tableSheet = new JTable();
	
	public Label lblRowDim = new Label("请选择行维度：");
	public JScrollPane spRowDim = new JScrollPane();
	public JTree treeRowDim = new JTree();
	
	public Label lblParamDim = new Label("选择汇总的参数维度：");
	public JScrollPane spParamDim = new JScrollPane();
	public JTree treeParamDim = new JTree();
	
	public Label lblColDim = new Label("选择列维度汇总方式：");
	public JComboBox cbColDim = new JComboBox();
	
	public Templete tpBean = null;
	public BgObj objBean = null;
	public BmsItemObj itemBean = null;
	public BmsSheet [] sheets = null;
	
	public String[] templeteHeader = new String[]{"选择","预算表编码","预算表名称"};
	
	
	/**
	 * 
	 */
	public GatherEditPanel() {
		init();
	}

	public void init() {
		try {
			
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");
			itemBean = (BmsItemObj)ClientBeanFactory.getBean("BmsItemObj");
			objBean = (BgObj)ClientBeanFactory.getBean("BgObj");
			
			this.setLayout(null);
			
			lblSheetCode.setBounds(10, 0, 70, 30);
			txtSheetCode.setBounds(80, 5, 150, 20);
			
			lblSheetName.setBounds(10, 30, 70, 30);
			txtSheetName.setBounds(80, 35, 150, 20);
			
			lblSheet.setBounds(10,60,605,30);
			spSheet.setBounds(10,90,605,150);
			spSheet.setViewportView(tableSheet);
			loadSheetData();
			
			lblRowDim.setBounds(10, 245, 300, 30);
			spRowDim.setBounds(10, 275, 300, 150);
			spRowDim.setViewportView(treeRowDim);
			loadRowDimData();
			
			lblParamDim.setBounds(315, 245, 300, 30);
			spParamDim.setBounds(315, 275, 300, 150);
			spParamDim.setViewportView(treeParamDim);
			loadParamDimData();
			
			lblColDim.setBounds(10,430,300,30);
			cbColDim.setBounds(10, 460, 120, 20);
			
			cbColDim.addItem("按列编码汇总");
			cbColDim.addItem("汇总计划值");
						
			this.add(lblSheetCode);
			this.add(txtSheetCode);
			this.add(lblSheetName);
			this.add(txtSheetName);
			this.add(lblSheet);
			this.add(spSheet);
			this.add(lblRowDim);
			this.add(spRowDim);
			this.add(lblParamDim);
			this.add(spParamDim);
			this.add(lblColDim);
			this.add(cbColDim);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	
	public void loadParamDimData() throws Exception {
		BmsCheckTreeNode rootNode = new BmsCheckTreeNode("01 险种");
		rootNode.add(new BmsCheckTreeNode("0101 个险"));
		rootNode.add(new BmsCheckTreeNode("0102 团险"));
		rootNode.add(new BmsCheckTreeNode("0103 银保"));
		
		DefaultTreeModel tm = new DefaultTreeModel(rootNode);
		treeParamDim.setModel(tm);
		treeParamDim.setEditable(true);
		treeParamDim.setCellRenderer(new BmsCheckTreeRenderer());
		treeParamDim.setCellEditor(new BmsCheckTreeEditor());
	}
	
	public void loadRowDimData() throws Exception {
		BmsCheckTreeNode rootNode = new BmsCheckTreeNode("预算项目");
		
		BmsItem [] items = itemBean.queryAllItems();
		ItemVO [] vos = new ItemVO[items.length];
		for (int i = 0; i < vos.length; i++) {
			vos[i] = new ItemVO(items[i]);
		}
		ClientUtils.TreevoToNode(rootNode, vos);
		
		
		//rootNode.add(new BmsCheckTreeNode("01 营业费用"));
		DefaultTreeModel tm = new DefaultTreeModel(rootNode);
		treeRowDim.setModel(tm);
		treeRowDim.setEditable(true);
		treeRowDim.setCellRenderer(new BmsCheckTreeRenderer());
		treeRowDim.setCellEditor(new BmsCheckTreeEditor());
	}
	
	
	
	public void loadSheetData() throws Exception {
		
		//BmsTemplet [] tps = tpBean.queryAll();
		
		String sql = "";
		sql+="select sheetid                                                  ";
		sql+="from bms_sheet st,bms_object obj,bms_userbgobj uo,bms_templet tp,           ";
		sql+="     bms_roleclass rc,bms_role r,bms_userrole ur                            ";
		sql+="where st.bgobjid=obj.bgobjid and uo.bgobjid=obj.parentbgobj                 ";
		sql+="      and st.tpid=tp.tpid and ur.userid=uo.userid                           ";
		sql+="      and rc.rcid=r.rcid and r.roleid=ur.roleid and tp.inputroleid=rc.rcid  ";
		sql+="      and uo.userid="+ClientEnv.getInstance().getUserID()+"                                                     ";
		
		sheets = tpBean.queryBmsSheet(sql);
		
		Object [][] data = new Object[sheets.length][];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[3];
			data[i][0] = Boolean.FALSE;
			data[i][1] = sheets[i].getSheetCode();
			data[i][2] = sheets[i].getSheetTitle();
		}
		
		ColEditableTableModel tm = new ColEditableTableModel(data,templeteHeader){
			public Class getColumnClass(int index) {
				Class [] c = new Class[]{Boolean.class,String.class,String.class};
				return c[index];
			}
		};
		tm.setEditabled(true);
		tm.setColEditableds(new boolean[]{true,false,false});
		
		tableSheet.setModel(tm);
		tableSheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel tcm = tableSheet.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);
		tcm.getColumn(1).setPreferredWidth(150);
		tcm.getColumn(2).setPreferredWidth(150);
		//tc.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
		//tc.setCellRenderer(new DefaultTableCellRenderer(new JCheckBox()));
		
	}
	
	public BmsSheet [] getSelectedSheets() throws Exception {
		
		Vector vSheet = new Vector();
		for (int i = 0; i < sheets.length; i++) {
			Boolean selected = (Boolean)tableSheet.getValueAt(i, 0);
			if(selected!=null && selected.booleanValue()) {
				vSheet.add(sheets[i]);
			}
		}
		BmsSheet[] selSheets = new BmsSheet[vSheet.size()];
		vSheet.copyInto(selSheets);
		return selSheets;
	}
	
	public void saveData() throws Exception {
		
		BmsSheet [] selSheets = getSelectedSheets();
		if(selSheets.length==0) throw new Exception ("请选择要汇总的预算表");
		
		BmsSheet sheet = new BmsSheet();
		sheet.setSheetCode(txtSheetCode.getText());
		sheet.setSheetTitle(txtSheetName.getText());
		sheet.setIsGatherSheet(BmsUtils.boolToChar(true));
		sheet.setAddUser(new Integer(ClientEnv.getInstance().getUserID()));
		sheet.setBmsTemplet(selSheets[0].getBmsTemplet());
		
		BmsObject bgobj = objBean.queryObjByID(selSheets[0].getBmsObject().getBgObjId());
		sheet.setBmsObject(new BmsObject(bgobj.getParentBgObj().intValue()));
		sheet.setApprFlag(BmsUtils.boolToChar(false));
		sheet.setGatheredFlag(BmsUtils.boolToChar(false));
		sheet.setRepFlag(BmsUtils.boolToChar(false));
		
		Integer sheetid = tpBean.insertSheet(sheet);
		tpBean.saveGatherSheetItems(sheetid.intValue(), selSheets);
		
	}

}
