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

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.swing.common.ColEditableTableModel;
import com.sinosoft.bms.valueobject.ColParentVO;
import com.sinosoft.bms.valueobject.ColTypeVO;

/**
 * @author sunrui
 *
 */
public class ColDimPanel extends JPanel {

	public JScrollPane spTable = new JScrollPane();
	public JTable colTable = new JTable();
	
	public String [] headers = {"列编码","列名","上级列","列类型","公式"};
	
	public JComboBox cbParentCol = new JComboBox();
	public JComboBox cbColType = new JComboBox();
	public JTextField txtColCode = new JTextField();
	
	public ColCodeCellEditor ceColCode = new ColCodeCellEditor(txtColCode);
	public ColEditableTableModel tm = null;
	
	/**
	 * 
	 */
	public ColDimPanel() {
		super();
		init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		this.add(spTable,BorderLayout.CENTER);
		spTable.setViewportView(colTable);
				
		tm = new ColEditableTableModel(new Object[1][headers.length],headers);
		tm.setEditabled(true);
		tm.setColEditableds(new boolean[]{true,true,true,true,true,true});
			
		colTable.setModel(tm);
		
		ceColCode.addCellEditorListener(new CellEditorListener(){

			public void editingCanceled(ChangeEvent e) {
			}

			public void editingStopped(ChangeEvent e) {
				onColCodeEditingStopped(e);
			}
			
		});
		
		//列类型
		for (int i = 0; i < ColTypeVO.getColTypes().length; i++) {
			cbColType.addItem(ColTypeVO.getColTypes()[i]);
		}
		
		colTable.getColumnModel().getColumn(0).setCellEditor(ceColCode);
		colTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbParentCol));
		colTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbColType));
		
		
	}
	
	public BmsTpColDim [] getTpColDims() throws Exception {
		
		int rowCount = tm.getRowCount();
		Vector vDim = new Vector();
				
		for (int i = 0; i < rowCount; i++) {
			Object objCode = tm.getValueAt(i, 0);
			String strCode = objCode==null?null:objCode.toString();
			if(strCode==null || strCode.trim().length()==0) {
				continue;
			}
			
			BmsTpColDim dim = new BmsTpColDim();
			dim.setColCode(strCode);
			
			Object objName = tm.getValueAt(i, 1);
			String strName = objName==null?null:objName.toString();
			if(strName==null || strName.trim().length()==0) throw new Exception("请输入第"+(i+1)+"行的列名");
			dim.setColName(strName);
			
			//将选择的上级列的index存入parentCol字段，后台在获得列主键后需进行替换
			Object objParent = tm.getValueAt(i, 2);
			ColParentVO pvo = objParent==null?null:(ColParentVO)objParent;
			dim.setParentCol(pvo==null?null:new Integer(pvo.getIndex()));
			
			Object objType = tm.getValueAt(i, 3);
			if(objType==null) throw new Exception("第"+(i+1)+"行没有选择列类型");
			ColTypeVO typevo = (ColTypeVO)objType;
			dim.setColType(new Integer(typevo.getColType()));
			
			Object objFormula = tm.getValueAt(i, 4);
			String strFormula = objFormula==null?null:objFormula.toString();
			dim.setFormula(strFormula);
			
			vDim.add(dim);
		}
		
		BmsTpColDim [] dims = new BmsTpColDim[vDim.size()];
		vDim.copyInto(dims);
		return dims;
	}
	
	public void setTpColDims(BmsTpColDim [] tpColDims) throws Exception {
		Object [][] data = new Object[tpColDims.length][];
		
		HashMap hmDimIdIndex = new HashMap();
		for (int i = 0; i < tpColDims.length; i++) {
			hmDimIdIndex.put(new Integer(tpColDims[i].getTpColDimId()),new Integer(i));
		}
		
		for (int i = 0; i < tpColDims.length; i++) {
			data[i] = new Object[headers.length];
			data[i][0] = tpColDims[i].getColCode();
			data[i][1] = tpColDims[i].getColName();
			
			Integer parentID = tpColDims[i].getParentCol();
			ColParentVO pvo = null;
			if(parentID==null) {
				pvo = new ColParentVO();
			} else {
				if(hmDimIdIndex.containsKey(parentID)) {
					int dimindex = ((Integer)hmDimIdIndex.get(parentID)).intValue();
					pvo = new ColParentVO(dimindex,tpColDims[dimindex].getColCode());
				} else {
					pvo = new ColParentVO();
				}
			}
			
			
			data[i][2] = pvo;
			ColTypeVO tvo = new ColTypeVO(tpColDims[i].getColType().intValue());
			data[i][3] = tvo;
			data[i][4] = tpColDims[i].getFormula();
			
		}
		
		tm.setDataVector(data, headers);
		colTable.getColumnModel().getColumn(0).setCellEditor(ceColCode);
		colTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbParentCol));
		colTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbColType));
		
		onColCodeEditingStopped(new ChangeEvent(ceColCode));
	}
	
	public void onColCodeEditingStopped(ChangeEvent e) {
		Object source = e.getSource();
		if(source!=null && source instanceof ColCodeCellEditor) {
			ColCodeCellEditor ce = (ColCodeCellEditor) source;
			int rowCount = tm.getRowCount();
			if(rowCount==0) {
				tm.addRow(new Object[headers.length]);
				return;
			}
			Object value = tm.getValueAt(rowCount-1, 0);
			if(value!=null && value.toString().trim().length()>0) {
				tm.addRow(new Object[headers.length]);
			} else {
				if(rowCount>1) {
					Object value1 = tm.getValueAt(rowCount-2, 0);
					if(value1==null || value1.toString().trim().length()==0) {
						tm.removeRow(rowCount-1);
					}
				}
			}
			
			//更新cbParent
			cbParentCol.removeAllItems();
			cbParentCol.addItem(new ColParentVO());
			rowCount = tm.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				Object colCode = tm.getValueAt(i, 0);
				if(colCode!=null && colCode.toString().trim().length()>0) {
					cbParentCol.addItem(new ColParentVO(i,colCode.toString()));
				}
			}
			
			
		}
	}
	
	

}
