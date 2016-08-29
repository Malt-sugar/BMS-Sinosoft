/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinosoft.bms.entity.BmsPrdSch;
import com.sinosoft.bms.entity.BmsScheme;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.RefPanel;
import com.sinosoft.bms.swing.refmodel.BgSchRefModel;
import com.sinosoft.bms.swing.refmodel.PrdSchRefModel;
import com.sinosoft.bms.swing.refmodel.RoleClassRefModel;
import com.sinosoft.bms.valueobject.TpTypeVO;
import com.sinosoft.bms.valueobject.UseFlagVO;

/**
 * @author sunrui
 *
 */
public class TpBasicInfoPanel extends JPanel {

	public JLabel lblTpCode = new JLabel("表样编码");
	public JLabel lblTpName = new JLabel("表样名称");
	public JLabel lblBgSch = new JLabel("预算方案");
	public JLabel lblPrdSch = new JLabel("期间方案");
	public JLabel lblUse = new JLabel("启用标志");
	public JLabel lblTpType = new JLabel("表样类型");
	public JLabel lblInputRC = new JLabel("编制角色类");
	public JLabel lblApprRC = new JLabel("审核角色类");
	public JLabel lblEnabledRC = new JLabel("批复角色类");
	
	public JTextField txtTpCode = new JTextField();
	public JTextField txtTpName = new JTextField();
	public RefPanel refBgSch = new RefPanel();
	public RefPanel refPrdSch = new RefPanel();
	public JComboBox cbUse = new JComboBox();
	public JComboBox cbTptype = new JComboBox();
	public RefPanel refInputRC = new RefPanel();
	public RefPanel refApprRC = new RefPanel();
	public RefPanel refEnabledRC = new RefPanel();
	
	public BmsTemplet bmsTemplet = null;

	/**
	 * 
	 */
	public TpBasicInfoPanel() {
		super();
		initialize();
	}

	public TpBasicInfoPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initialize();
	}

	/**
	 * @param layout
	 */
	public TpBasicInfoPanel(LayoutManager layout) {
		super(layout);
		initialize();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public TpBasicInfoPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initialize();
	}

	public void initialize() {
		
		int x = 10;
		int y = 10;
		int h = 25;
		int w = 80;
		
		lblTpCode.setBounds(x, y, w, h);
		lblTpName.setBounds(x, y+=h, w, h);
		lblBgSch.setBounds(x, y+=h, w, h);
		lblPrdSch.setBounds(x, y+=h, w, h);
		lblUse.setBounds(x, y+=h, w, h);
		lblTpType.setBounds(x, y+=h, w, h);
		lblInputRC.setBounds(x, y+=h, w, h);
		lblApprRC.setBounds(x, y+=h, w, h);
		lblEnabledRC.setBounds(x, y+=h, w, h);
		
		refBgSch.setRefModel(new BgSchRefModel());
		refPrdSch.setRefModel(new PrdSchRefModel());
		refInputRC.setRefModel(new RoleClassRefModel());
		refApprRC.setRefModel(new RoleClassRefModel());
		refEnabledRC.setRefModel(new RoleClassRefModel());
		
		
		x=x+w;
		y=15;
		h=20;
		w=150;
		
		txtTpCode.setBounds(x, y, w, h);
		txtTpName.setBounds(x, y+=h+5, w, h);
		refBgSch.setBounds(x, y+=h+5, w, h);
		refPrdSch.setBounds(x, y+=h+5, w, h);
		cbUse.setBounds(x, y+=h+5, w/2, h);
		cbTptype.setBounds(x, y+=h+5, w, h);
		refInputRC.setBounds(x, y+=h+5, w, h);
		refApprRC.setBounds(x, y+=h+5, w, h);
		refEnabledRC.setBounds(x, y+=h+5, w, h);
		
//		try {
//			PrdSch beanPrdSch = (PrdSch)ClientBeanFactory.getBean("PrdSch");
//			BmsPrdSch [] arrPrdSch = beanPrdSch.queryAll();
//			BmsPrdSch prdsch = beanPrdSch.queryById(1);
//			if(arrPrdSch!=null) {
//				for (int i = 0; i < arrPrdSch.length; i++) {
//					refPrdSch.addItem(arrPrdSch[i].getPrdSchCode()+" "+arrPrdSch[i].getPrdSchName());
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			ClientUtils.showErrMsg(this, e.getMessage());
//		}
		
		cbUse.addItem(new UseFlagVO(UseFlagVO.USEFALG_INUSE));
		cbUse.addItem(new UseFlagVO(UseFlagVO.USEFLAG_NOTUSE));
		
		cbTptype.addItem(new TpTypeVO(TpTypeVO.TPTYPE_INPUT));
		cbTptype.addItem(new TpTypeVO(TpTypeVO.TPTYPE_ANALYSE));
		
		this.setLayout(null);
		this.setSize(new Dimension(300, 300));
		this.add(lblTpCode, null);
		this.add(lblTpName, null);
		this.add(lblBgSch,null);
		this.add(lblPrdSch, null);
		this.add(lblUse, null);
		this.add(lblTpType,null);
		this.add(lblInputRC, null);
		this.add(lblApprRC, null);
		this.add(lblEnabledRC, null);
		
		this.add(txtTpCode,null);
		this.add(txtTpName,null);
		this.add(refBgSch,null);
		this.add(refPrdSch,null);
		this.add(cbUse,null);
		this.add(cbTptype,null);
		this.add(refInputRC,null);
		this.add(refApprRC,null);
		this.add(refEnabledRC,null);
		
	}
	
	
	/**
	 * @return the bmsTemplet
	 */
	public BmsTemplet getBmsTemplet() throws Exception {
		
		
		BmsTemplet tp = new BmsTemplet();
		tp.setTpId(bmsTemplet.getTpId());
		tp.setTpCode(txtTpCode.getText());
		tp.setTpName(txtTpName.getText());	
		ClientUtils.setRefToId(refPrdSch, tp, "setBmsPrdSch");
		ClientUtils.setRefToId(refBgSch, tp, "setBmsScheme");
		tp.setUseFlag(((UseFlagVO)cbUse.getSelectedItem()).toCharValue());
		tp.setTpType(((TpTypeVO)cbTptype.getSelectedItem()).toCharValue());
		tp.setInputRoleId(new Integer(refInputRC.getRefId()));
		tp.setApprRoleId(new Integer(refApprRC.getRefId()));
		tp.setEnableRoleId(new Integer(refEnabledRC.getRefId()));
		
		return tp;
	}

	/**
	 * @param bmsTemplet the bmsTemplet to set
	 */
	public void setBmsTemplet(BmsTemplet tp) throws Exception {
		this.bmsTemplet = tp;
		txtTpCode.setText(tp.getTpCode());
		txtTpName.setText(tp.getTpName());
		
		ClientUtils.setIdToRef(refBgSch, tp, "getBmsScheme");
		ClientUtils.setIdToRef(refPrdSch, tp, "getBmsPrdSch");
		//refBgSch.setRefId(tp.getBmsScheme()==null?0:tp.getBmsScheme().getBgSchId());
		//refPrdSch.setRefId(tp.getBmsPrdSch()==null?0:tp.getBmsPrdSch().getPrdSchId());
		cbUse.setSelectedItem(new UseFlagVO(tp.getUseFlag()));
		cbTptype.setSelectedItem(new TpTypeVO(tp.getTpType()));
		refInputRC.setRefId(tp.getInputRoleId()==null?0:tp.getInputRoleId().intValue());
		refApprRC.setRefId(tp.getApprRoleId()==null?0:tp.getApprRoleId().intValue());
		refEnabledRC.setRefId(tp.getEnableRoleId()==null?0:tp.getEnableRoleId().intValue());
		
		
	}



}
