package com.sinosoft.bms.swing.templet;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.sinosoft.bmscell.base.core.ComparatorUtils;
import com.sinosoft.bmscell.ref.BorderStyleColor;
import com.sinosoft.bmscell.ref.CellBorderDialog;
import com.sinosoft.bmscell.ref.CellBorderStyle;
import com.sinosoft.bmscell.ref.CellStyleDialog;
import com.sinosoft.bmscell.ref.ColumnRow;
import com.sinosoft.bmscell.ref.MyDialog;
import com.sinosoft.bmscell.report.AbstractReport;
import com.sinosoft.bmscell.report.CellElement;
import com.sinosoft.bmscell.report.CellStyle;
import com.sinosoft.bmscell.report.FloatElement;
import com.sinosoft.bmscell.report.Report;
import com.sinosoft.bmscell.report.SpreadSheet;
import com.sinosoft.bmscell.report.core.DynamicValueList;
import com.sinosoft.bmscell.report.core.ReportCoreConstants;
import com.sinosoft.bmscell.report.core.ReportHelper;
import com.sinosoft.bmscell.report.core.ReportUtils;
import com.sinosoft.bmscell.report.core.cadapter.CellAdapter;
import com.sinosoft.bmscell.report.core.paint.PaintUtil;
import com.sinosoft.bmscell.report.core.paint.SeparatorPainter;
import com.sinosoft.bmscell.report.gui.CellSelection;
import com.sinosoft.bmscell.report.gui.Grid;
import com.sinosoft.bmscell.report.gui.SpreadSheetPane;
import com.sinosoft.bmscell.report.io.html.ColumnRowValue;

public class DesignSpreadSheetPane extends SpreadSheetPane
{
	private static final long serialVersionUID = 1L;
	public RowColChangeListener rowColChangeListener = null;
	
	private CellBorderDialog m_CellBorderDialog = null;
	private CellStyleDialog m_CellStyleDialog = null;

	public CellBorderDialog getCellBorderDialog() {
		if (m_CellBorderDialog == null) {
			m_CellBorderDialog = new CellBorderDialog(this);
		}
		return m_CellBorderDialog;
	}
	
	public CellStyleDialog getCellStyleDialog() {
		if (m_CellStyleDialog == null) {
			m_CellStyleDialog = new CellStyleDialog(this);
		}
		return m_CellStyleDialog;
	}
	
	ActionListener MyActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent evt) 
		{
			try
			{
				String cmd = ((JMenuItem) evt.getSource()).getText().trim();  //  @jve:decl-index=0:
				if(cmd.equals("合并"))
					onMerge();
				else if(cmd.equals("拆分"))
					onUnMerge();
				else if(cmd.equals("样式"))
					onStyle();
				else if(cmd.equals("边框"))
					onBroad();
				else if(cmd.equals("插入整行"))
				{
					Report report = DesignSpreadSheetPane.this.getReport();
					CellSelection cellselection = DesignSpreadSheetPane.this.getCellSelection();
					if (cellselection != null) {
						int i = cellselection.getRow();
						for (int j = cellselection.getRowSpan() - 1; j >= 0; j--)
							report.insertRow(i);
					}	
				}
				else if(cmd.equals("插入整列"))
				{
					Report report = DesignSpreadSheetPane.this.getReport();
					CellSelection cellselection = DesignSpreadSheetPane.this.getCellSelection();
					if (cellselection != null) {
						int i = cellselection.getColumn();
						for (int j = cellselection.getColumnSpan() - 1; j >= 0; j--)
							report.insertColumn(i);
					}
				}
				else if(cmd.equals("删除整行"))
				{
					Report report = DesignSpreadSheetPane.this.getReport();
					CellSelection cellselection = DesignSpreadSheetPane.this.getCellSelection();
					if (cellselection != null) {
						int i = cellselection.getRow();
						for (int j = cellselection.getRowSpan() - 1; j >= 0; j--)
							report.removeRow(i);
					}
				}
				else if(cmd.equals("删除整列"))
				{
					Report report = DesignSpreadSheetPane.this.getReport();
					CellSelection cellselection = DesignSpreadSheetPane.this.getCellSelection();
					if (cellselection != null) {
						int i = cellselection.getColumn();
						for (int j = cellselection.getColumnSpan() - 1; j >= 0; j--)
							report.removeColumn(i);
					}
				}	
				
				repaint();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	};
	public void onMerge() throws Exception 
	{
		if (this.canMergeCell())
		{
			this.mergeCell();
			this.repaint();	
		}
	}
	public void onUnMerge() throws Exception 
	{	
		if (this.canUnMergeCell())
		{
			this.unMergeCell();
			this.repaint();
		}
	}
	
	public boolean isAllEquals(List list) {
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			for (int j = i + 1; j < list.size(); j++)
				if (!ComparatorUtils.equals(obj, list.get(j)))
					return false;
		}
		return true;
	}
	
	public void onCellStyle() {
		CellElement cellelement = null;
		FloatElement floatelement = this
				.getSelectedFloatElement();
		if (floatelement == null) {
			CellSelection cellselection = this.getCellSelection();
			if (cellselection != null) {
				int i = cellselection.getEditColumn();
				int j = cellselection.getEditRow();
				Report report = this.getReport();
				CellAdapter celladapter = ReportHelper.getCellAdapter(report,
						i, j);
				cellelement = celladapter.getCellElement();
			}
		}
		CellStyle cellstyle = null;
		if (cellelement != null && cellelement.getCellStyle() != null)
			try {
				cellstyle = (CellStyle) cellelement.getCellStyle().clone();
			} catch (CloneNotSupportedException clonenotsupportedexception) {
				clonenotsupportedexception.printStackTrace();
			}
		if (cellstyle == null)
			try {
				cellstyle = (CellStyle) ReportCoreConstants.DefaultCellStyle
						.clone();
			} catch (CloneNotSupportedException clonenotsupportedexception1) {
				clonenotsupportedexception1.printStackTrace();
			}
		CellStyle cellstyle1 = null;
		try {
			cellstyle1 = (CellStyle) cellstyle.clone();
		} catch (CloneNotSupportedException clonenotsupportedexception2) {
			clonenotsupportedexception2.printStackTrace();
		}
		getCellStyleDialog().populate(cellstyle);
		getCellStyleDialog().populate(cellelement);
		getCellStyleDialog().setVisible(true);

		if (getCellStyleDialog().getResult() == MyDialog.ID_OK) {
			getCellStyleDialog().update(cellstyle);
			Report report1 = this.getReport();
			CellSelection cellselection1 = this.getCellSelection();
			for (int l = 0; l < cellselection1.getRowSpan(); l++) {
				for (int i1 = 0; i1 < cellselection1.getColumnSpan(); i1++) {
					int j1 = i1 + cellselection1.getColumn();
					int k1 = l + cellselection1.getRow();
					CellAdapter celladapter1 = ReportHelper.getCellAdapter(
							report1, j1, k1);
					CellElement cellelement1 = celladapter1.getCellElement();
					if (cellelement1 == null) {
						cellelement1 = new CellElement(j1
								- celladapter1.getStartColumn(), k1
								- celladapter1.getStartRow());
						celladapter1.addCellElement(cellelement1);
					}
					ReportUtils.applyCellStyle(cellstyle1, cellstyle,
							cellelement1.getCellStyle());
					getCellStyleDialog().update(cellelement1);
					Object obj = cellelement1.getValue();
					if (obj != null
							&& ((obj instanceof String)
									|| (obj instanceof Number) || (obj instanceof SeparatorPainter))) {
						DynamicValueList dynamicvaluelist = ReportHelper
								.getColumnWidthList(report1);
						int l1 = cellelement1.getColumn();
						int i2 = cellelement1.getRow();
						double d = PaintUtil.analyzeCellElementPreferredHeight(
								cellelement1, dynamicvaluelist.getRangeValue(
										l1, l1 + cellelement1.getColumnSpan()));
						if (d > report1.getRowHeight(i2)
								&& (report1 instanceof AbstractReport))
							((AbstractReport) report1).setRowHeight(k1, d);
					}
				}
			}
		}
	}
	public void onStyle() throws Exception 
	{
		onCellStyle();
	}
	public void onBroad() throws Exception 
	{
		doBroad();
		
	}
	
	public void doBroad()
	{
		CellSelection cellselection = this.getCellSelection();
		int i = cellselection.getColumn();
		int j = cellselection.getRow();
		int k = cellselection.getColumnSpan();
		int l = cellselection.getRowSpan();
		Hashtable hashtable = new Hashtable();
		Report report = this.getReport();
		
		if (j - 1 >= 0)
		{
			for (int i1 = i; i1 < i + k; i1++)
			{
				CellAdapter celladapter = ReportHelper.getCellAdapter(report,
						i1, j - 1);
				CellElement cellelement = celladapter.getCellElement();
				if (cellelement != null)
				{
					CellStyle cellstyle = cellelement.getCellStyle();
					if (cellstyle.getBorderBottom() != 0)
					{
						for (int j2 = celladapter.getColumn(); j2 < celladapter
								.getColumn()
								+ celladapter.getColumnSpan(); j2++)
							hashtable.put(new ColumnRow(j2, j, false),
									new BorderStyleColor(cellstyle
											.getBorderBottom(), cellstyle
											.getBorderBottomColor()));
					}
				}
			}
		}
		if (i - 1 >= 0)
		{
			for (int j1 = j; j1 < j + l; j1++)
			{
				CellAdapter celladapter1 = ReportHelper.getCellAdapter(report,
						i - 1, j1);
				CellElement cellelement1 = celladapter1.getCellElement();
				if (cellelement1 != null)
				{
					CellStyle cellstyle1 = cellelement1.getCellStyle();
					if (cellstyle1.getBorderRight() != 0)
					{
						for (int k2 = celladapter1.getRow(); k2 < celladapter1
								.getRow()
								+ celladapter1.getRowSpan(); k2++)
							hashtable.put(new ColumnRow(i, k2, true),
									new BorderStyleColor(cellstyle1
											.getBorderRight(), cellstyle1
											.getBorderRightColor()));
					}
				}
			}
		}
		for (int k1 = i; k1 < i + k; k1++)
		{
			CellAdapter celladapter2 = ReportHelper.getCellAdapter(report, k1,
					j + l);
			CellElement cellelement2 = celladapter2.getCellElement();
			if (cellelement2 != null)
			{
				CellStyle cellstyle2 = cellelement2.getCellStyle();
				if (cellstyle2.getBorderTop() != 0)
				{
					for (int l2 = celladapter2.getColumn(); l2 < celladapter2
							.getColumn()
							+ celladapter2.getColumnSpan(); l2++)
						hashtable.put(new ColumnRow(l2, j + l, false),
								new BorderStyleColor(cellstyle2.getBorderTop(),
										cellstyle2.getBorderTopColor()));
				}
			}
		}
		for (int l1 = j; l1 < j + l; l1++)
		{
			CellAdapter celladapter3 = ReportHelper.getCellAdapter(report, i
					+ k, l1);
			CellElement cellelement3 = celladapter3.getCellElement();
			if (cellelement3 != null)
			{
				CellStyle cellstyle3 = cellelement3.getCellStyle();
				if (cellstyle3.getBorderLeft() != 0)
				{
					for (int j3 = celladapter3.getRow(); j3 < celladapter3
							.getRow()
							+ celladapter3.getRowSpan(); j3++)
						hashtable.put(new ColumnRow(i + k, j3, true),
								new BorderStyleColor(
										cellstyle3.getBorderLeft(), cellstyle3
												.getBorderLeftColor()));
				}
			}
		}
		for (int i2 = i; i2 < i + k; i2++)
		{
			for (int i3 = j; i3 < j + l; i3++)
			{
				CellAdapter celladapter4 = ReportHelper.getCellAdapter(report,
						i2, i3);
				CellElement cellelement4 = celladapter4.getCellElement();
				if (cellelement4 != null)
				{
					CellStyle cellstyle4 = cellelement4.getCellStyle();
					if (cellstyle4.getBorderTop() != 0)
					{
						for (int l3 = celladapter4.getColumn(); l3 < celladapter4
								.getColumn()
								+ celladapter4.getColumnSpan(); l3++)
							hashtable.put(new ColumnRow(l3, celladapter4
									.getRow(), false), new BorderStyleColor(
									cellstyle4.getBorderTop(), cellstyle4
											.getBorderTopColor()));
					}
					if (cellstyle4.getBorderLeft() != 0)
					{
						for (int i4 = celladapter4.getRow(); i4 < celladapter4
								.getRow()
								+ celladapter4.getRowSpan(); i4++)
							hashtable.put(new ColumnRow(celladapter4
									.getColumn(), i4, true),
									new BorderStyleColor(cellstyle4
											.getBorderLeft(), cellstyle4
											.getBorderLeftColor()));
					}
					if (cellstyle4.getBorderBottom() != 0)
					{
						for (int j4 = celladapter4.getColumn(); j4 < celladapter4
								.getColumn()
								+ celladapter4.getColumnSpan(); j4++)
							hashtable.put(new ColumnRow(j4, celladapter4
									.getRow()
									+ celladapter4.getRowSpan(), false),
									new BorderStyleColor(cellstyle4
											.getBorderBottom(), cellstyle4
											.getBorderBottomColor()));
					}
					if (cellstyle4.getBorderRight() != 0)
					{
						for (int k4 = celladapter4.getRow(); k4 < celladapter4
								.getRow()
								+ celladapter4.getRowSpan(); k4++)
							hashtable.put(new ColumnRow(celladapter4
									.getColumn()
									+ celladapter4.getColumnSpan(), k4, true),
									new BorderStyleColor(cellstyle4
											.getBorderRight(), cellstyle4
											.getBorderRightColor()));
					}
				}
			}

		}
		CellBorderStyle cellborderstyle = new CellBorderStyle();
		ArrayList arraylist = new ArrayList();
		for (int k3 = i; k3 < i + k; k3++)
		{
			BorderStyleColor borderstylecolor = (BorderStyleColor) hashtable
					.get(new ColumnRow(k3, j, false));
			arraylist.add(borderstylecolor);
		}
		if (arraylist.size() > 0)
			if (isAllEquals(arraylist))
			{
				BorderStyleColor borderstylecolor1 = (BorderStyleColor) arraylist
						.get(0);
				if (borderstylecolor1 != null)
				{
					cellborderstyle.setTopColor(borderstylecolor1.getColor());
					cellborderstyle.setTopStyle(borderstylecolor1.getStyle());
				}
			} else
			{
				cellborderstyle.setTopColor(java.awt.Color.gray);
				cellborderstyle.setTopStyle(15);
			}
		arraylist.clear();
		for (int l4 = j; l4 < j + l; l4++)
		{
			BorderStyleColor borderstylecolor2 = (BorderStyleColor) hashtable
					.get(new ColumnRow(i, l4, true));
			arraylist.add(borderstylecolor2);
		}
		if (arraylist.size() > 0)
			if (isAllEquals(arraylist))
			{
				BorderStyleColor borderstylecolor3 = (BorderStyleColor) arraylist
						.get(0);
				if (borderstylecolor3 != null)
				{
					cellborderstyle.setLeftColor(borderstylecolor3.getColor());
					cellborderstyle.setLeftStyle(borderstylecolor3.getStyle());
				}
			} else
			{
				cellborderstyle.setLeftColor(java.awt.Color.gray);
				cellborderstyle.setLeftStyle(15);
			}
		arraylist.clear();
		for (int i5 = i; i5 < i + k; i5++)
		{
			BorderStyleColor borderstylecolor4 = (BorderStyleColor) hashtable
					.get(new ColumnRow(i5, j + l, false));
			arraylist.add(borderstylecolor4);
		}
		if (arraylist.size() > 0)
			if (isAllEquals(arraylist))
			{
				BorderStyleColor borderstylecolor5 = (BorderStyleColor) arraylist
						.get(0);
				if (borderstylecolor5 != null)
				{
					cellborderstyle
							.setBottomColor(borderstylecolor5.getColor());
					cellborderstyle
							.setBottomStyle(borderstylecolor5.getStyle());
				}
			} else
			{
				cellborderstyle.setBottomColor(java.awt.Color.gray);
				cellborderstyle.setBottomStyle(15);
			}
		arraylist.clear();
		for (int j5 = j; j5 < j + l; j5++)
		{
			BorderStyleColor borderstylecolor6 = (BorderStyleColor) hashtable
					.get(new ColumnRow(i + k, j5, true));
			arraylist.add(borderstylecolor6);
		}
		if (arraylist.size() > 0)
			if (isAllEquals(arraylist))
			{
				BorderStyleColor borderstylecolor7 = (BorderStyleColor) arraylist
						.get(0);
				if (borderstylecolor7 != null)
				{
					cellborderstyle.setRightColor(borderstylecolor7.getColor());
					cellborderstyle.setRightStyle(borderstylecolor7.getStyle());
				}
			} else
			{
				cellborderstyle.setRightColor(java.awt.Color.gray);
				cellborderstyle.setRightStyle(15);
			}
		boolean flag = false;
		if (cellselection.getColumnSpan() > 1 || cellselection.getRowSpan() > 1)
		{
			flag = true;
			arraylist.clear();
			for (int k5 = i + 1; k5 < i + k; k5++)
			{
				for (int i6 = j; i6 < j + l; i6++)
				{
					BorderStyleColor borderstylecolor9 = (BorderStyleColor) hashtable
							.get(new ColumnRow(k5, i6, true));
					arraylist.add(borderstylecolor9);
				}
			}
			if (arraylist.size() > 0)
				if (isAllEquals(arraylist))
				{
					BorderStyleColor borderstylecolor8 = (BorderStyleColor) arraylist
							.get(0);
					if (borderstylecolor8 != null)
					{
						cellborderstyle.setVerticalColor(borderstylecolor8
								.getColor());
						cellborderstyle.setVerticalStyle(borderstylecolor8
								.getStyle());
					}
				} else
				{
					cellborderstyle.setVerticalColor(java.awt.Color.gray);
					cellborderstyle.setVerticalStyle(15);
				}
			arraylist.clear();
			for (int j6 = i; j6 < i + k; j6++)
			{
				for (int k6 = j + 1; k6 < j + l; k6++)
				{
					BorderStyleColor borderstylecolor11 = (BorderStyleColor) hashtable
							.get(new ColumnRowValue(j6, k6, new Boolean(false)));
					arraylist.add(borderstylecolor11);
				}
			}
			if (arraylist.size() > 0)
				if (isAllEquals(arraylist))
				{
					BorderStyleColor borderstylecolor10 = (BorderStyleColor) arraylist
							.get(0);
					if (borderstylecolor10 != null)
					{
						cellborderstyle.setHorizontalColor(borderstylecolor10
								.getColor());
						cellborderstyle.setHorizontalStyle(borderstylecolor10
								.getStyle());
					}
				} else
				{
					cellborderstyle.setHorizontalColor(java.awt.Color.gray);
					cellborderstyle.setHorizontalStyle(15);
				}
		}
		int l5 = 1;
		java.awt.Color color = java.awt.Color.black;
		if (cellborderstyle.getLeftStyle() != 0
				&& cellborderstyle.getLeftStyle() != 15)
		{
			l5 = cellborderstyle.getLeftStyle();
			color = cellborderstyle.getLeftColor();
		} else if (cellborderstyle.getTopStyle() != 0
				&& cellborderstyle.getTopStyle() != 15)
		{
			l5 = cellborderstyle.getTopStyle();
			color = cellborderstyle.getTopColor();
		} else if (cellborderstyle.getBottomStyle() != 0
				&& cellborderstyle.getBottomStyle() != 15)
		{
			l5 = cellborderstyle.getBottomStyle();
			color = cellborderstyle.getBottomColor();
		} else if (cellborderstyle.getRightStyle() != 0
				&& cellborderstyle.getRightStyle() != 15)
		{
			l5 = cellborderstyle.getRightStyle();
			color = cellborderstyle.getRightColor();
		} else if (cellborderstyle.getVerticalStyle() != 0
				&& cellborderstyle.getVerticalStyle() != 15)
		{
			l5 = cellborderstyle.getVerticalStyle();
			color = cellborderstyle.getVerticalColor();
		} else if (cellborderstyle.getHorizontalStyle() != 0
				&& cellborderstyle.getHorizontalStyle() != 15)
		{
			l5 = cellborderstyle.getHorizontalStyle();
			color = cellborderstyle.getHorizontalColor();
		}
		getCellBorderDialog().populate(cellborderstyle, flag, l5, color);
		getCellBorderDialog().setVisible(true);

		if (getCellBorderDialog().getResult() == MyDialog.ID_OK)
		{
			CellBorderStyle cellborderstyle1 = getCellBorderDialog().update();
			if (cellborderstyle.getTopStyle() != cellborderstyle1.getTopStyle()
					|| !ComparatorUtils.equals(cellborderstyle.getTopColor(),
							cellborderstyle1.getTopColor()))
			{
				if (j - 1 >= 0)
				{
					for (int i7 = i; i7 < i + k; i7++)
					{
						CellAdapter celladapter5 = ReportHelper.getCellAdapter(
								report, i7, j - 1);
						CellElement cellelement5 = celladapter5
								.getCellElement();
						if (cellelement5 != null)
						{
							CellStyle cellstyle5 = cellelement5.getCellStyle();
							cellstyle5.setBorderBottom(0);
							cellstyle5
									.setBorderBottomColor(java.awt.Color.black);
						}
					}
				}
			}
			if (cellborderstyle.getLeftStyle() != cellborderstyle1
					.getLeftStyle()
					|| !ComparatorUtils.equals(cellborderstyle.getLeftColor(),
							cellborderstyle1.getLeftColor()))
			{
				if (i - 1 >= 0)
				{
					for (int j7 = j; j7 < j + l; j7++)
					{
						CellAdapter celladapter6 = ReportHelper.getCellAdapter(
								report, i - 1, j7);
						CellElement cellelement6 = celladapter6
								.getCellElement();
						if (cellelement6 != null)
						{
							CellStyle cellstyle6 = cellelement6.getCellStyle();
							cellstyle6.setBorderRight(0);
							cellstyle6
									.setBorderRightColor(java.awt.Color.black);
						}
					}
				}
			}
			if (cellborderstyle.getBottomStyle() != cellborderstyle1
					.getBottomStyle()
					|| !ComparatorUtils.equals(
							cellborderstyle.getBottomColor(), cellborderstyle1
									.getBottomColor()))
			{
				for (int k7 = i; k7 < i + k; k7++)
				{
					CellAdapter celladapter7 = ReportHelper.getCellAdapter(
							report, k7, j + l);
					CellElement cellelement7 = celladapter7.getCellElement();
					if (cellelement7 != null)
					{
						CellStyle cellstyle7 = cellelement7.getCellStyle();
						cellstyle7.setBorderTop(0);
						cellstyle7.setBorderTopColor(java.awt.Color.black);
					}
				}
			}
			if (cellborderstyle.getRightStyle() != cellborderstyle1
					.getRightStyle()
					|| !ComparatorUtils.equals(cellborderstyle.getRightColor(),
							cellborderstyle1.getRightColor()))
			{
				for (int l7 = j; l7 < j + l; l7++)
				{
					CellAdapter celladapter8 = ReportHelper.getCellAdapter(
							report, i + k, l7);
					CellElement cellelement8 = celladapter8.getCellElement();
					if (cellelement8 != null)
					{
						CellStyle cellstyle8 = cellelement8.getCellStyle();
						cellstyle8.setBorderLeft(0);
						cellstyle8.setBorderLeftColor(java.awt.Color.black);
					}
				}
			}
			if (cellborderstyle.getTopStyle() != cellborderstyle1.getTopStyle()
					|| !ComparatorUtils.equals(cellborderstyle.getTopColor(),
							cellborderstyle1.getTopColor())
					|| cellborderstyle.getLeftStyle() != cellborderstyle1
							.getLeftStyle()
					|| !ComparatorUtils.equals(cellborderstyle.getLeftColor(),
							cellborderstyle1.getLeftColor())
					|| cellborderstyle.getBottomStyle() != cellborderstyle1
							.getBottomStyle()
					|| !ComparatorUtils.equals(
							cellborderstyle.getBottomColor(), cellborderstyle1
									.getBottomColor())
					|| cellborderstyle.getRightStyle() != cellborderstyle1
							.getRightStyle()
					|| !ComparatorUtils.equals(cellborderstyle.getRightColor(),
							cellborderstyle1.getRightColor())
					|| cellborderstyle.getVerticalStyle() != cellborderstyle1
							.getVerticalStyle()
					|| !ComparatorUtils.equals(cellborderstyle
							.getVerticalColor(), cellborderstyle1
							.getVerticalColor())
					|| cellborderstyle.getHorizontalStyle() != cellborderstyle1
							.getHorizontalStyle()
					|| !ComparatorUtils.equals(cellborderstyle
							.getHorizontalColor(), cellborderstyle1
							.getHorizontalColor()))
			{
				for (int i8 = i; i8 < i + k; i8++)
				{
					for (int j8 = j; j8 < j + l; j8++)
					{
						CellAdapter celladapter9 = ReportHelper.getCellAdapter(
								report, i8, j8);
						CellElement cellelement9 = celladapter9
								.getCellElement();
						if (cellelement9 == null)
						{
							cellelement9 = new CellElement(i8
									- celladapter9.getStartColumn(), j8
									- celladapter9.getStartRow());
							celladapter9.addCellElement(cellelement9);
							celladapter9.setCellElement(cellelement9);
						}
						CellStyle cellstyle9 = cellelement9.getCellStyle();
						if (celladapter9.getColumn() == i)
						{
							if (cellborderstyle.getLeftStyle() != cellborderstyle1
									.getLeftStyle()
									|| !ComparatorUtils.equals(cellborderstyle
											.getLeftColor(), cellborderstyle1
											.getLeftColor()))
							{
								cellstyle9.setBorderLeft(cellborderstyle1
										.getLeftStyle());
								cellstyle9.setBorderLeftColor(cellborderstyle1
										.getLeftColor());
							}
						} else if (cellborderstyle.getVerticalStyle() != cellborderstyle1
								.getVerticalStyle()
								|| !ComparatorUtils.equals(cellborderstyle
										.getVerticalColor(), cellborderstyle1
										.getVerticalColor()))
						{
							cellstyle9.setBorderLeft(cellborderstyle1
									.getVerticalStyle());
							cellstyle9.setBorderLeftColor(cellborderstyle1
									.getVerticalColor());
						}
						if (celladapter9.getColumn()
								+ celladapter9.getColumnSpan() == i + k)
						{
							if (cellborderstyle.getRightStyle() != cellborderstyle1
									.getRightStyle()
									|| !ComparatorUtils.equals(cellborderstyle
											.getRightColor(), cellborderstyle1
											.getRightColor()))
							{
								cellstyle9.setBorderRight(cellborderstyle1
										.getRightStyle());
								cellstyle9.setBorderRightColor(cellborderstyle1
										.getRightColor());
							}
						} else if (cellborderstyle.getVerticalStyle() != cellborderstyle1
								.getVerticalStyle()
								|| !ComparatorUtils.equals(cellborderstyle
										.getVerticalColor(), cellborderstyle1
										.getVerticalColor()))
						{
							cellstyle9.setBorderRight(cellborderstyle1
									.getVerticalStyle());
							cellstyle9.setBorderRightColor(cellborderstyle1
									.getVerticalColor());
						}
						if (celladapter9.getRow() == j)
						{
							if (cellborderstyle.getTopStyle() != cellborderstyle1
									.getTopStyle()
									|| !ComparatorUtils.equals(cellborderstyle
											.getTopColor(), cellborderstyle1
											.getTopColor()))
							{
								cellstyle9.setBorderTop(cellborderstyle1
										.getTopStyle());
								cellstyle9.setBorderTopColor(cellborderstyle1
										.getTopColor());
							}
						} else if (cellborderstyle.getHorizontalStyle() != cellborderstyle1
								.getHorizontalStyle()
								|| !ComparatorUtils.equals(cellborderstyle
										.getHorizontalColor(), cellborderstyle1
										.getHorizontalColor()))
						{
							cellstyle9.setBorderTop(cellborderstyle1
									.getHorizontalStyle());
							cellstyle9.setBorderTopColor(cellborderstyle1
									.getHorizontalColor());
						}
						if (celladapter9.getRow() + celladapter9.getRowSpan() == j
								+ l)
						{
							if (cellborderstyle.getBottomStyle() != cellborderstyle1
									.getBottomStyle()
									|| !ComparatorUtils.equals(cellborderstyle
											.getBottomColor(), cellborderstyle1
											.getBottomColor()))
							{
								cellstyle9.setBorderBottom(cellborderstyle1
										.getBottomStyle());
								cellstyle9
										.setBorderBottomColor(cellborderstyle1
												.getBottomColor());
							}
						} else if (cellborderstyle.getHorizontalStyle() != cellborderstyle1
								.getHorizontalStyle()
								|| !ComparatorUtils.equals(cellborderstyle
										.getHorizontalColor(), cellborderstyle1
										.getHorizontalColor()))
						{
							cellstyle9.setBorderBottom(cellborderstyle1
									.getHorizontalStyle());
							cellstyle9.setBorderBottomColor(cellborderstyle1
									.getHorizontalColor());
						}
					}
				}
			}
		}
	}
	
	public ActionListener getMyActionListener()
	{
		return MyActionListener;
	}

	public void setMyActionListener(ActionListener myActionListener)
	{
		MyActionListener = myActionListener;
	}

	public DesignSpreadSheetPane() {

		super(new SpreadSheet());
		init();
	}

	public DesignSpreadSheetPane(SpreadSheet spreadsheet) {
		super(spreadsheet);
		init();
	}

	public DesignSpreadSheetPane(SpreadSheet spreadsheet, Grid grid) {
		super(spreadsheet, grid);
		init();
	}

	public void init() {
		setRowHeaderSelectedBackground(Color.gray);
		setColumnHeaderSelectedBackground(Color.gray);
		setMyActionListener(MyActionListener);
		this.setShowPaperMarginLine(false);
	}

	public JPopupMenu createRowPopupMenu(int rowIndex, Point point) {
		JPopupMenu rowPopupMenu = new JPopupMenu();

		JMenuItem InsertRowItem = new JMenuItem("插入整行");
		rowPopupMenu.add(InsertRowItem);
		InsertRowItem.addActionListener(getMyActionListener());

		JMenuItem DeleteRowItem = new JMenuItem("删除整行");
		rowPopupMenu.add(DeleteRowItem);
		DeleteRowItem.addActionListener(getMyActionListener());

		JCheckBoxMenuItem RowEndlessItem = new JCheckBoxMenuItem("行扩展",isRowEndless());
		rowPopupMenu.add(RowEndlessItem);
		RowEndlessItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRowEndless(!isRowEndless());
			}
		});
		return rowPopupMenu;
	}

	public JPopupMenu createColumnPopupMenu(int columnIndex, Point point) {
		JPopupMenu columnPopupMenu = new JPopupMenu();

		JMenuItem InsertColItem = new JMenuItem("插入整列");
		columnPopupMenu.add(InsertColItem);
		InsertColItem.addActionListener(getMyActionListener());

		JMenuItem DeleteColItem = new JMenuItem("删除整列");
		columnPopupMenu.add(DeleteColItem);
		DeleteColItem.addActionListener(getMyActionListener());

		JCheckBoxMenuItem ColEndlessItem = new JCheckBoxMenuItem("列扩展",isColumnEndless());
		columnPopupMenu.add(ColEndlessItem);
		ColEndlessItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColumnEndless(!isColumnEndless());
			}
		});
		return columnPopupMenu;
	}

	public JPopupMenu createPopupMenu() {
		JPopupMenu cellPopupMenu = new JPopupMenu();

		JMenuItem mergeMenuItem = new JMenuItem("合并");
		cellPopupMenu.add(mergeMenuItem);
		mergeMenuItem.addActionListener(getMyActionListener());

		JMenuItem unMergeItem = new JMenuItem("拆分");
		cellPopupMenu.add(unMergeItem);
		unMergeItem.addActionListener(getMyActionListener());
		cellPopupMenu.addSeparator();
	
/*		
		JMenuItem formulaItem = new JMenuItem("公式");
		cellPopupMenu.add(formulaItem);
		formulaItem.addActionListener(getMyActionListener());
		JMenuItem KPIItem = new JMenuItem("指标");
		cellPopupMenu.add(KPIItem);
		KPIItem.addActionListener(getMyActionListener());
		JMenuItem AreaInput = new JMenuItem("区域录入");
		cellPopupMenu.add(AreaInput);
		AreaInput.addActionListener(getMyActionListener());
		JMenuItem AreaName = new JMenuItem("区域命名");
		cellPopupMenu.add(AreaName);
		AreaName.addActionListener(getMyActionListener());

		cellPopupMenu.addSeparator();
		
*/		
		JMenuItem BorderItem = new JMenuItem("边框");
		cellPopupMenu.add(BorderItem);
		BorderItem.addActionListener(getMyActionListener());
		JMenuItem StryleItem = new JMenuItem("样式");
		cellPopupMenu.add(StryleItem);
		StryleItem.addActionListener(getMyActionListener());
/*
		if (m_rptvo.getTYPE().intValue() == 1) {
			JMenuItem repeatItem = new JMenuItem("设置重复区域");
			cellPopupMenu.add(repeatItem);
			repeatItem.addActionListener(getMyActionListener());
		}

		 if (m_rptvo.getTYPE().intValue() == 2) {
		 JMenuItem sortItem = new JMenuItem("指定排名列");
		 cellPopupMenu.add(sortItem);
		 sortItem.addActionListener(getMyActionListener());
		
		 }
*/
		cellPopupMenu.addSeparator();
		JCheckBoxMenuItem item1 = new JCheckBoxMenuItem("网格线",
				isShowGridLine());
		cellPopupMenu.add(item1);
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setShowGridLine(!isShowGridLine());
			}
		});
		JCheckBoxMenuItem item2 = new JCheckBoxMenuItem("辅助线",
				isShowPaperMarginLine());
		cellPopupMenu.add(item2);
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setShowPaperMarginLine(!isShowPaperMarginLine());
			}
		});
		return cellPopupMenu;
	}
	
	
	public void addMouseListenerToGrid(MouseListener l){
		grid.addMouseListener(l);
	}
	
	public void initParam(String cmd) 
	{
		if (cmd.trim().equalsIgnoreCase("删除整行")) 
		{
			Report report = getReport();
			CellSelection cellselection = getCellSelection();
			if (cellselection != null) 
			{
				int i = cellselection.getRow();
				if(getRowColChangeListener()!=null) {
					getRowColChangeListener().onRowDeleted(i);
				}
				for (int j = cellselection.getRowSpan() - 1; j >= 0; j--)
					report.removeRow(i);
			}
		}
		else if (cmd.trim().equalsIgnoreCase("删除整列")) {
			Report report = getReport();
			CellSelection cellselection = getCellSelection();
			if (cellselection != null) {
				int i = cellselection.getColumn();
				if(getRowColChangeListener()!=null) {
					getRowColChangeListener().onColDeleted(i);
				}
				for (int j = cellselection.getColumnSpan() - 1; j >= 0; j--)
					report.removeColumn(i);
			}
		}
		else if (cmd.trim().equalsIgnoreCase("插入整行")) {
			Report report = getReport();
			CellSelection cellselection = getCellSelection();
			if (cellselection != null) {
				int i = cellselection.getRow();
				if(getRowColChangeListener()!=null) {
					getRowColChangeListener().onRowInserted(i);
				}
				for (int j = cellselection.getRowSpan() - 1; j >= 0; j--)
					report.insertRow(i);
			}
		} else if (cmd.trim().equalsIgnoreCase("插入整列")) {
			Report report = getReport();
			CellSelection cellselection = getCellSelection();
			if (cellselection != null) {
				int i = cellselection.getColumn();
				if(getRowColChangeListener()!=null) {
					getRowColChangeListener().onRowInserted(i);
				}
				for (int j = cellselection.getColumnSpan() - 1; j >= 0; j--)
					report.insertColumn(i);
			}
		}
		
		this.validate();
		this.repaint();
	}
	/**
	 * @return Returns the rowColChangeListener.
	 */
	public RowColChangeListener getRowColChangeListener() {
		return rowColChangeListener;
	}
	/**
	 * @param rowColChangeListener The rowColChangeListener to set.
	 */
	public void setRowColChangeListener(
			RowColChangeListener rowColChangeListener) {
		this.rowColChangeListener = rowColChangeListener;
	}
}