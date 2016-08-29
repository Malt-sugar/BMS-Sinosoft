/*
 * Created on 2005-11-9
 *
 */
package com.sinosoft.bms.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Administrator
 *
 *
 */
public abstract class CitiQueryAction extends Action {

    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        String rFlag = "";
        String rMess = "";
        String resultStr = "";
        String sqlStr = "sql";
        
        try {
            forward = citiExecute(actionMapping, actionForm, request, response);
            
    		rFlag = (String) request.getAttribute(ICodeConst.ATTR_RESULT_FLAG);
    		rMess = (String) request.getAttribute(ICodeConst.ATTR_RESULT_MESSAGE);
    		resultStr = (String) request.getAttribute(ICodeConst.ATTR_RESULT_STRING);
    		sqlStr = (String) request.getAttribute(ICodeConst.ATTR_SQL_STRING);
    		
            if (forward != null) {
                //如果citiExecute()指定了ActionForward，则使用该ActionForward
                return forward;
            }
            if (rFlag == null || rFlag.equals("")) {
                //如果citiExecute()中未指定返回信息，则返回下面的消息
                rFlag = "Success";
                rMess = "查询成功！";
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            rFlag = "Fail";
            rMess = "查询失败:" + ex.getMessage();
        }
        response.setContentType("text/html; charset=GBK");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma","no-cache");
        if(rFlag.equals("Fail")){
            response.getWriter().println("<script language=\"javascript\">");
            response.getWriter().println("parent.fraInterface.afterSubmit(\"" + rFlag + "\",\"" + rMess + "\");");
            response.getWriter().println("</script>");
        } else {
            response.getWriter().println("<script language=\"javascript\">");
            response.getWriter().println("parent.fraInterface.afterQuery(\"" + resultStr + "\",\"" + sqlStr + "\");");
            response.getWriter().println("</script>");
        }
        return null;
    }

    public abstract ActionForward citiExecute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) throws Exception;
}
