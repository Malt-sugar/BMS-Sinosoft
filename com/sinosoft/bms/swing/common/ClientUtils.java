/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


/**
 * @author sunrui
 *
 */
public class ClientUtils {

	/**
	 * 
	 */
	public ClientUtils() {
	}

	public static void showErrMsg(Component parent,String msg) {
		JOptionPane.showMessageDialog(parent, msg, "出错", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showMsg(Component parent,String msg) {
		JOptionPane.showMessageDialog(parent, msg, "信息", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String getClassGetName(Class cls) throws Exception {
		return "get"+cls.getName().substring(cls.getPackage().getName().length()+1);
	}
	
	public static String getClassSetName(Class cls) throws Exception {
		return "set"+cls.getName().substring(cls.getPackage().getName().length()+1);
	}
	
	public static void setRefToId(RefPanel rp,Object entity,Class cls) throws Exception {
		setRefToId(rp, entity, getClassSetName(cls));
	}
	
	public static void setRefToId(RefPanel rp,Object entity,String methodName) throws Exception {
		int refid = rp.getRefId();
		if(entity==null) return;
		
		Method [] methods = entity.getClass().getMethods();
		Method setMethod = null;
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			if(m.getName().equals(methodName)) {
				setMethod = m;
				break;
			}
		}
		
		if(setMethod==null) throw new Exception("未找到设置方法");
		Class [] params = setMethod.getParameterTypes();
		if(params.length!=1) throw new Exception("设置方法参数数量不为1");
		Class type = params[0];
		if(refid==0) {
			setMethod.invoke(entity, new Object[]{null});
			return;
		}
		Constructor construct = type.getConstructor(new Class[]{int.class});
		Object obj = construct.newInstance(new Object[]{new Integer(refid)});
		setMethod.invoke(entity, new Object[]{obj});
		
		
		
	}
	
	public static void setIdToRef(RefPanel rp,Object entity,Class cls) throws Exception {
		setIdToRef(rp, entity, getClassGetName(cls));
	}
	
	public static void setIdToRef(RefPanel rp,Object entity,String methodName) throws Exception {
		if(entity==null) {
			rp.setRefId(0);
			return;
		}
		Method method = entity.getClass().getMethod(methodName, new Class[0]);
		Class type = method.getReturnType();
		Object typeObj = method.invoke(entity, new Object[0]);
		if(typeObj==null) {
			rp.setRefId(0);
			return;
		}
		Method [] typeMethods = type.getMethods();
		Method idMethod = null;
		for (int i = 0; i < typeMethods.length; i++) {
			if(typeMethods[i].getReturnType()==int.class) {
				idMethod = typeMethods[i];
				break;
			}
		}
		
		if(idMethod==null) throw new Exception ("未找到目标类型的ID方法");
		Object objId = idMethod.invoke(typeObj, new Object[0]);
		rp.setRefId(((Integer)objId).intValue());
		
	}
	
	 public static void expandAll(JTree tree, TreePath parent, boolean expand) {
		  // Traverse children
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}

		// Expansion or collapse must be done bottom-up
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	 }

	 public static ImageIcon loadIcon(String gificon) throws Exception {
		 ClientUtils obj = new ClientUtils();
		 ImageIcon icon = new ImageIcon(obj.getClass().getClassLoader().getResource(gificon));
		 return icon;
	 }
	 
	 public static IconUIResource loadIconRes(String gificon) {
		 try {
			 return new IconUIResource(loadIcon(gificon));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 

	 public static void TreevoToNode(BmsCheckTreeNode parentNode,ITreeVO [] treeVos) throws Exception {
		 Object userObject = parentNode.getUserObject();
		 int parentid = 0;
		 if(userObject!=null && userObject instanceof ITreeVO) {
			 ITreeVO tvo = (ITreeVO) userObject;
			 parentid = tvo.getID();
		 }
		 for (int i = 0; i < treeVos.length; i++) {
			if(treeVos[i].getParentID()==parentid) {
				BmsCheckTreeNode node = new BmsCheckTreeNode(treeVos[i]);
				parentNode.add(node);
				TreevoToNode(node, treeVos);
			}
		}
	 }
	 
	 public static boolean showYesNoDlg(Component parent, String content) throws Exception {
		 return (JOptionPane.showConfirmDialog(parent, content, "确认", 
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
	 }
	
	
}
