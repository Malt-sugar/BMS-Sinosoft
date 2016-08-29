/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.TreeCellEditor;

/**
 * @author sunrui
 *
 */
public class BmsCheckTreeEditor extends JPanel implements TreeCellEditor {
	protected JCheckBox check;

	protected TreeLabel label;
	
	protected BmsCheckTreeNode chkNode = null;
	
	protected JTree tree = null;
	
	/**
	 * 
	 */
	public BmsCheckTreeEditor() {
		setLayout(null);
		add(check = new JCheckBox());
		add(label = new TreeLabel());
		check.setBackground(UIManager.getColor("Tree.textBackground"));
		label.setForeground(UIManager.getColor("Tree.textForeground"));
		check.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				if(chkNode!=null) {
					chkNode.setSelected(check.isSelected());
				}
				if(tree!=null) {
					tree.treeDidChange();
				}
			}});
	}

	/**
	 * @param isDoubleBuffered
	 */
	public BmsCheckTreeEditor(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * @param layout
	 */
	public BmsCheckTreeEditor(LayoutManager layout) {
		super(layout);
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public BmsCheckTreeEditor(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}



	
	public class TreeLabel extends JLabel {
		boolean isSelected;

		boolean hasFocus;

		public TreeLabel() {
		}

		public void setBackground(Color color) {
			if (color instanceof ColorUIResource) {
				color = null;
			}
			super.setBackground(color);
		}

		public void paint(Graphics g) {
			String str;
			if ((str = getText()) != null) {
				if (0 < str.length()) {
					if (isSelected) {
						g.setColor(UIManager.getColor("Tree.selectionBackground"));
						this.setForeground(UIManager.getColor("Tree.selectionForeground"));
					} else {
						g.setColor(UIManager.getColor("Tree.textBackground"));
						this.setForeground(UIManager.getColor("Tree.foreground"));
					}
					Dimension d = getPreferredSize();
					int imageOffset = 0;
					Icon currentI = getIcon();
					if (currentI != null) {
						imageOffset = currentI.getIconWidth()
								+ Math.max(0, getIconTextGap() - 1);
					}
					g.fillRect(imageOffset, 0, d.width - 1 - imageOffset,
							d.height);
					if (hasFocus) {
						g.setColor(UIManager
								.getColor("Tree.selectionBorderColor"));
						g.drawRect(imageOffset, 0, d.width - 1 - imageOffset,
								d.height - 1);
					}
				}
			}
			super.paint(g);
		}

		public Dimension getPreferredSize() {
			Dimension retDimension = super.getPreferredSize();
			if (retDimension != null) {
				retDimension = new Dimension(retDimension.width + 3,
						retDimension.height);
			}
			return retDimension;
		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		}

		public void setFocus(boolean hasFocus) {
			this.hasFocus = hasFocus;
		}
	}
	
	
	public Dimension getPreferredSize() {
		Dimension d_check = check.getPreferredSize();
		Dimension d_label = label.getPreferredSize();
		return new Dimension(d_check.width + d_label.width,
				(d_check.height < d_label.height ? d_label.height
						: d_check.height)+20);
	}

	public void doLayout() {
		Dimension d_check = check.getPreferredSize();
		Dimension d_label = label.getPreferredSize();
		int y_check = 0;
		int y_label = 0;
		if (d_check.height < d_label.height) {
			y_check = (d_label.height - d_check.height) / 2;
		} else {
			y_label = (d_check.height - d_label.height) / 2;
		}
		check.setLocation(0, 0);
		check.setBounds(0, 0, d_check.width, d_check.height);
		label.setLocation(d_check.width, y_label);
		label.setBounds(d_check.width, y_label, d_label.width, d_label.height);
	}
	
	public void setBackground(Color color) {
		if (color instanceof ColorUIResource) {
			color = null;
		}
		super.setBackground(color);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeCellEditor#getTreeCellEditorComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int)
	 */
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		this.tree=tree;
		String stringValue = tree.convertValueToText(value, isSelected,
				expanded, leaf, row, true);
		System.out.println();
		
		
		setEnabled(tree.isEnabled());
		chkNode = (BmsCheckTreeNode) value;
		check.setSelected(((BmsCheckTreeNode) value).isSelected());
		label.setFont(tree.getFont());
		label.setText(stringValue);
		label.setSelected(isSelected);
		//label.setFocus(true);
		
		if (leaf) {
			label.setIcon(UIManager.getIcon("Tree.leafIcon"));
		} else if (expanded) {
			label.setIcon(UIManager.getIcon("Tree.openIcon"));
		} else {
			label.setIcon(UIManager.getIcon("Tree.closedIcon"));
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
	 */
	public void addCellEditorListener(CellEditorListener l) {
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#cancelCellEditing()
	 */
	public void cancelCellEditing() {
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		chkNode.setSelected(check.isSelected(),tree);
		return chkNode;
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
	 */
	public boolean isCellEditable(EventObject anEvent) {
		if(tree!=null) {
			return tree.isEnabled();
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
	 */
	public void removeCellEditorListener(CellEditorListener l) {
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
	 */
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#stopCellEditing()
	 */
	public boolean stopCellEditing() {
		return true;
	}
}
