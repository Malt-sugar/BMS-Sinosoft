/**
 *
 * Created on 2009-4-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author sunrui
 *
 */
public abstract class BmsDataAction extends Action {
	protected static Logger logger = Logger.getLogger("BmsQueryAction");
	
	public static final String ACTION_NEW_SAVE = "NewSave";
	public static final String ACTION_EDIT_QUERY = "EditQuery";
	public static final String ACTION_EDIT_SAVE = "EditSave";
	public static final String ACTION_DELETE  = "DELETE";
	
	
	/**
	 * 
	 */
	public BmsDataAction() {
	}
	
	
	public abstract ActionForward bmsExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception ;


	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		try {
			forward = bmsExecute(mapping, form, request, response);
			Object obj = request.getAttribute("action");
			if(obj!=null) {
				String action = obj.toString();
				/*
				if(action.equalsIgnoreCase("query")) {
					Object objData = request.getAttribute("data");
					String jscontent = "";
					if(objData!=null) {
						Object [][] data = (Object[][])objData;
						jscontent+="var data = [";
						for (int i = 0; i < data.length; i++) {
							jscontent+="[";
							for (int j = 0; j < data[i].length; j++) {
								jscontent+="'"+data[i][j]+"'";
								if(j<data[i].length-1) jscontent+=",";
							}
							jscontent+="]\n";
							if(i<data.length-1) jscontent+=",";
						}
						jscontent+="];\n";
					}
					jscontent+="parent.grid.setData(data);";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);
					
				}
				*/
				if(action.equalsIgnoreCase(ACTION_NEW_SAVE)) {
					String jscontent = "";
					jscontent+="parent.afterNewSave(true);";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);
				} else if(action.equalsIgnoreCase(ACTION_EDIT_SAVE)) {
					String jscontent = "";
					jscontent+="parent.afterEditSave(true);";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);
				} else if(action.equalsIgnoreCase(ACTION_EDIT_QUERY)) {
					String jscontent = "";
					Object objData = request.getAttribute("data");
					if(objData==null) throw new Exception("未查询到该记录，可能已经被删除");
					Object [][] data = (Object[][])objData;
					
					jscontent+="var editObj = new Object();\n";
					for (int i = 0; i < data.length; i++) {
						jscontent+="editObj."+data[i][0]+"=\""+data[i][1]+"\";\n";
					}
					
					jscontent+="parent.afterEditQry(true,editObj);";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);					
				}else if(action.equalsIgnoreCase(ACTION_DELETE)){
						String jscontent = "";
						jscontent="parent.fraInterface.afterDelete();";
						request.setAttribute("jscontent", jscontent);
						request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);					
										
				}else {
					throw new Exception ("BmsDataAction指定的Action方式不存在！");
				}
			} else {
				throw new Exception ("BmsDataAction未指定具体的处理方式！");
			}
		} catch (Exception e) {
			logger.error("BmsQueryAction发生异常", e);
			
			Object obj = request.getAttribute("action");
			if(obj!=null) {
				String action = obj.toString();
				if(action.equalsIgnoreCase(ACTION_NEW_SAVE)) {
					String jscontent = "";
					jscontent+="parent.afterNewSave(false,\""+e.getMessage()+"\");";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);
				} else if(action.equalsIgnoreCase(ACTION_EDIT_SAVE)) {
					String jscontent = "";
					jscontent+="parent.afterEditSave(false,\""+e.getMessage()+"\");";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);
				} else if(action.equalsIgnoreCase(ACTION_EDIT_QUERY)) {
					String jscontent = "";
					jscontent+="parent.afterEditQry(false,\""+e.getMessage()+"\");";
					request.setAttribute("jscontent", jscontent);
					request.getRequestDispatcher("/main/querydata.jsp").forward(request, response);
				} else {
					request.setAttribute("exception", e);
					request.getRequestDispatcher("/main/errmsg.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/main/errmsg.jsp").forward(request, response);
			}
		}
		
		return forward;
	}
	
	
	

}
