/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.actions;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.entity.BmsRoleClass;
import com.sinosoft.bms.entity.BmsScheme;
import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class CodeSelectAction extends Action {

	protected static Logger logger = Logger.getLogger(CodeSelectAction.class);

	/**
	 * 
	 */
	public CodeSelectAction() {
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String jscontent = "";
		try {
			
			String selectName = request.getParameter("selectName");
			String param0 = request.getParameter("param0");
			
			Object [][] data = doSelect(selectName, new Object[]{param0});
			
			jscontent+="var data=[\n";
			
			if(data!=null) {
				for (int i = 0; i < data.length; i++) {
					jscontent+="[";
					
					for (int j = 0; j < data[i].length; j++) {
						jscontent+="'"+data[i][j]+"'";
						if(j<data[i].length-1) {
							jscontent+=",";
						}
					}
					
					jscontent+="]";
					
					if(i<data.length-1) {
						jscontent+=",\n";
					}
				}
			}
			
			jscontent+="];\n";
			
			jscontent+="parent.afterCodeSelected(true,data);\n";
			
		} catch (Exception e) {
			logger.error("CodeSelectAction·¢Éú´íÎó", e);
			jscontent+="parent.afterCodeSelected(false,'"+e.getMessage()+"');\n";
		}
		
		request.setAttribute("jscontent", jscontent);
		request.getRequestDispatcher("/main/doscript.jsp").forward(request, response);
		
		return null;
	}
	
	public Object[][] doSelect(String selectName,Object [] params) throws Exception {
		DAO dao = (DAO)BeanFactory.getBean("dao");
		Vector vData = new Vector();
		Object [][] data = null;
		
		if(selectName.equalsIgnoreCase("dimMem")) {
			List dimMemList = dao.query("from BmsDimMember order by dimMemCode");
			for (int i = 0; i < dimMemList.size(); i++) {
				BmsDimMember dimMem = (BmsDimMember) dimMemList.get(i);
				Object [] objDimMem = new Object[3];
				objDimMem[0]=new Integer(dimMem.getDimMemId());
				objDimMem[1]=dimMem.getDimMemCode();
				objDimMem[2]=dimMem.getDimMemName();
				vData.add(objDimMem);
			}
		}
		if(selectName.equalsIgnoreCase("dim")) {
			List dimList = dao.query("from BmsDim order by dimCode");
			for(int i=0;i<dimList.size();i++) {
				BmsDim dim = (BmsDim) dimList.get(i);
				Object [] objDim = new Object[3];
				objDim[0] = new Integer(dim.getDimId());
				objDim[1] = dim.getDimCode();
				objDim[2] = dim.getDimName();
				vData.add(objDim);
			}
		}
		if(selectName.equalsIgnoreCase("userRole")) {
			List roleList = dao.query("from BmsRole order by roleId");
			for(int i=0;i<roleList.size();i++) {
				BmsRole role = (BmsRole) roleList.get(i);
				Object [] objRole = new Object[3];
				objRole[0] = new Integer(role.getRoleId());
				objRole[1] = role.getRoleCode();
				objRole[2] = role.getRoleName();
				vData.add(objRole);
			}
		}
		if(selectName.equalsIgnoreCase("bgObj")) {
			List objectList = dao.query("from BmsObject order by bgObjId");
			for(int i=0;i<objectList.size();i++) {
				BmsObject bmsObj = (BmsObject) objectList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(bmsObj.getBgObjId());
				object[1] = bmsObj.getBgObjCode();
				object[2] = bmsObj.getBgObjName();
				vData.add(object);
			}
		}
		if(selectName.equalsIgnoreCase("objectType")) {
			List objTypeList = dao.query("from BmsObjectType order by bgObjTypeId");
			for(int i=0;i<objTypeList.size();i++) {
				BmsObjectType objType = (BmsObjectType) objTypeList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(objType.getBgObjTypeId());
				object[1] = objType.getBjObjTypeCode();
				object[2] = objType.getBjObjTypeName();
				vData.add(object);
			}
		}		
		if(selectName.equalsIgnoreCase("bmsItem")) {
			List objTypeList = dao.query("from BmsItem order by itemId");
			for(int i=0;i<objTypeList.size();i++) {
				BmsItem objType = (BmsItem) objTypeList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(objType.getItemId());
				object[1] = objType.getItemCode();
				object[2] = objType.getItemName();
				vData.add(object);
			}
		}		
		if(selectName.equalsIgnoreCase("roleClass")) {
			List objTypeList = dao.query("from BmsRoleClass order by rcid");
			for(int i=0;i<objTypeList.size();i++) {
				BmsRoleClass objType = (BmsRoleClass) objTypeList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(objType.getRcid());
				object[1] = objType.getRccode();
				object[2] = objType.getRcname();
				vData.add(object);
			}
		}		
		if(selectName.equalsIgnoreCase("bmsSheet")) {
			List objTypeList = dao.query("from BmsSheet order by sheetId");
			for(int i=0;i<objTypeList.size();i++) {
				BmsSheet objType = (BmsSheet) objTypeList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(objType.getSheetId());
				object[1] = objType.getSheetCode();
				object[2] = objType.getSheetTitle();
				vData.add(object);
			}
		}		
		if(selectName.equalsIgnoreCase("bmsUser")) {
			List objTypeList = dao.query("from BmsUser order by userId");
			for(int i=0;i<objTypeList.size();i++) {
				BmsUser objType = (BmsUser) objTypeList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(objType.getUserId());
				object[1] = objType.getUserCode();
				object[2] = objType.getUserName();
				vData.add(object);
			}
		}		
		if(selectName.equalsIgnoreCase("bmsScheme")) {
			List objTypeList = dao.query("from bmsScheme order by bgSchId");
			for(int i=0;i<objTypeList.size();i++) {
				BmsScheme objType = (BmsScheme) objTypeList.get(i);
				Object [] object = new Object[3];
				object[0] = new Integer(objType.getBgSchId());
				object[1] = objType.getBgSchCode();
				object[2] = objType.getBgSchName();
				vData.add(object);
			}
		}		
		
		
		data = new Object[vData.size()][];
		vData.copyInto(data);
		
		return data;
	}

}
