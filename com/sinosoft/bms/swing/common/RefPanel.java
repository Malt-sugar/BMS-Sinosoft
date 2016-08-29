/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author sunrui
 *
 */
public class RefPanel extends JPanel {

	public JTextField txtCodeName = null;
	public RefButton refButton = null;
	public RefModel refModel = null;
	public RefDialog refDialog = null;
	
	public static final int REF_DATA = 0;
	public static final int REF_DATE = 1;
	
	public int refId = 0;
	public String refCode = null;
	public String refName = null;
	
	public int refType = REF_DATA;
	
	public Object [][] data = null;
	
	public RefPanel() {
		init();
	}
	
	public void init() {
		setLayout(new BorderLayout());
		txtCodeName = new JTextField();
		
		try {
			
			ImageIcon btnIcon = new ImageIcon(getClass().getClassLoader().getResource("com/sinosoft/bms/swing/icons/ref.gif"));
			refButton = new RefButton(btnIcon);
			refButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					onRefButtonClicked();
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		add(refButton,BorderLayout.EAST);
		add(txtCodeName, BorderLayout.CENTER);
		refButton.setPreferredSize(new Dimension(20,20));
		
		
	}
	
	public void onRefButtonClicked() {
		if(refType==REF_DATA) {
			
			getRefDialog().setLocationRelativeTo(ClientEnv.getInstance().getMainApplet());
			getRefDialog().setVisible(true);
			
			if(getRefDialog().getDialogResult()==RefDialog.DLG_OK) {
				Object [] selData = getRefDialog().getSelData();
				refId = ((Integer)selData[0]).intValue();
				refCode = selData[1].toString();
				refName = selData[2].toString();
				txtCodeName.setText("["+refCode+"]"+refName);
			}
			
		}
	}

	public int getRefId() throws Exception {
		return refId;
	}
	
	public String getRefCode() throws Exception {
		return refCode;
	}
	
	public String getRefName() throws Exception {
		return refName;
	}

	/**
	 * @return the refModel
	 */
	public RefModel getRefModel() {
		return refModel;
	}



	/**
	 * @param refModel the refModel to set
	 */
	public void setRefModel(RefModel refModel) {
		this.refModel = refModel;
	}



	/**
	 * @return the txtCodeName
	 */
	public JTextField getTxtCodeName() {
		return txtCodeName;
	}



	/**
	 * @param txtCodeName the txtCodeName to set
	 */
	public void setTxtCodeName(JTextField txtCodeName) {
		this.txtCodeName = txtCodeName;
	}

	/**
	 * @return the refButton
	 */
	public RefButton getRefButton() {
		return refButton;
	}

	/**
	 * @param refButton the refButton to set
	 */
	public void setRefButton(RefButton refButton) {
		this.refButton = refButton;
	}

	/**
	 * @return the refDialog
	 */
	public RefDialog getRefDialog() {
		if(refDialog==null) {
			
			refDialog = new RefDialog(this);
			refDialog.setRefModel(getRefModel());
			refDialog.setLocationRelativeTo(this);
			refDialog.refreshData();
		}
		return refDialog;
	}

	/**
	 * @param refDialog the refDialog to set
	 */
	public void setRefDialog(RefDialog refDialog) {
		this.refDialog = refDialog;
	}

	/**
	 * @return the refType
	 */
	public int getRefType() {
		return refType;
	}

	/**
	 * @param refType the refType to set
	 */
	public void setRefType(int refType) {
		this.refType = refType;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		txtCodeName.setEnabled(enabled);
		refButton.setEnabled(enabled);
	}

	
	/**
	 * @param refid the refid to set
	 */
	public void setRefId(int refId) {
		this.refId=0;
		refCode=null;
		refName=null;
		txtCodeName.setText(null);
		if(data==null) {
			getRefDialog();
		}
		if(data!=null) {
			for (int i = 0; i < data.length; i++) {
				if(data[i][0]!=null && data[i][0] instanceof Integer) {
					int id = ((Integer)data[i][0]).intValue();
					if(id==refId) {
						this.refId=refId;
						refCode=data[i][1].toString();
						refName=data[i][2].toString();
						txtCodeName.setText("["+refCode+"]"+refName);
					}
				}
			}
		}
		
		
	}

	
}
