package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:48 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * BmsObject generated by hbm2java
 */

public class BmsObject  implements java.io.Serializable {


    // Fields    

     private int bgObjId;
     private BmsObjectType bmsObjectType;
     private String bgObjCode;
     private String bgObjName;
     private Integer parentBgObj;
     private String operSysCode;
     private Character useFlag;
     private Set bmsUsers = new HashSet(0);
     private Set bmsAdjustBills = new HashSet(0);
     private Set bmsRoles = new HashSet(0);
     private Set bmsSheets = new HashSet(0);
     private Set bmsTpBgObjs = new HashSet(0);
     private Set bmsUserBgObjs = new HashSet(0);
     private Set bmsSchemes = new HashSet(0);
     private Set bmsSheetItems = new HashSet(0);


    // Constructors

    /** default constructor */
    public BmsObject() {
    }

	/** minimal constructor */
    public BmsObject(int bgObjId) {
        this.bgObjId = bgObjId;
    }
    
    /** full constructor */
    public BmsObject(int bgObjId, BmsObjectType bmsObjectType, String bgObjCode, String bgObjName, Integer parentBgObj, String operSysCode, Character useFlag, Set bmsUsers, Set bmsAdjustBills, Set bmsRoles, Set bmsSheets, Set bmsTpBgObjs, Set bmsUserBgObjs, Set bmsSchemes, Set bmsSheetItems) {
        this.bgObjId = bgObjId;
        this.bmsObjectType = bmsObjectType;
        this.bgObjCode = bgObjCode;
        this.bgObjName = bgObjName;
        this.parentBgObj = parentBgObj;
        this.operSysCode = operSysCode;
        this.useFlag = useFlag;
        this.bmsUsers = bmsUsers;
        this.bmsAdjustBills = bmsAdjustBills;
        this.bmsRoles = bmsRoles;
        this.bmsSheets = bmsSheets;
        this.bmsTpBgObjs = bmsTpBgObjs;
        this.bmsUserBgObjs = bmsUserBgObjs;
        this.bmsSchemes = bmsSchemes;
        this.bmsSheetItems = bmsSheetItems;
    }
    

   
    // Property accessors

    public int getBgObjId() {
        return this.bgObjId;
    }
    
    public void setBgObjId(int bgObjId) {
        this.bgObjId = bgObjId;
    }

    public BmsObjectType getBmsObjectType() {
        return this.bmsObjectType;
    }
    
    public void setBmsObjectType(BmsObjectType bmsObjectType) {
        this.bmsObjectType = bmsObjectType;
    }

    public String getBgObjCode() {
        return this.bgObjCode;
    }
    
    public void setBgObjCode(String bgObjCode) {
        this.bgObjCode = bgObjCode;
    }

    public String getBgObjName() {
        return this.bgObjName;
    }
    
    public void setBgObjName(String bgObjName) {
        this.bgObjName = bgObjName;
    }

    public Integer getParentBgObj() {
        return this.parentBgObj;
    }
    
    public void setParentBgObj(Integer parentBgObj) {
        this.parentBgObj = parentBgObj;
    }

    public String getOperSysCode() {
        return this.operSysCode;
    }
    
    public void setOperSysCode(String operSysCode) {
        this.operSysCode = operSysCode;
    }

    public Character getUseFlag() {
        return this.useFlag;
    }
    
    public void setUseFlag(Character useFlag) {
        this.useFlag = useFlag;
    }

    public Set getBmsUsers() {
        return this.bmsUsers;
    }
    
    public void setBmsUsers(Set bmsUsers) {
        this.bmsUsers = bmsUsers;
    }

    public Set getBmsAdjustBills() {
        return this.bmsAdjustBills;
    }
    
    public void setBmsAdjustBills(Set bmsAdjustBills) {
        this.bmsAdjustBills = bmsAdjustBills;
    }

    public Set getBmsRoles() {
        return this.bmsRoles;
    }
    
    public void setBmsRoles(Set bmsRoles) {
        this.bmsRoles = bmsRoles;
    }

    public Set getBmsSheets() {
        return this.bmsSheets;
    }
    
    public void setBmsSheets(Set bmsSheets) {
        this.bmsSheets = bmsSheets;
    }

    public Set getBmsTpBgObjs() {
        return this.bmsTpBgObjs;
    }
    
    public void setBmsTpBgObjs(Set bmsTpBgObjs) {
        this.bmsTpBgObjs = bmsTpBgObjs;
    }

    public Set getBmsUserBgObjs() {
        return this.bmsUserBgObjs;
    }
    
    public void setBmsUserBgObjs(Set bmsUserBgObjs) {
        this.bmsUserBgObjs = bmsUserBgObjs;
    }

    public Set getBmsSchemes() {
        return this.bmsSchemes;
    }
    
    public void setBmsSchemes(Set bmsSchemes) {
        this.bmsSchemes = bmsSchemes;
    }

    public Set getBmsSheetItems() {
        return this.bmsSheetItems;
    }
    
    public void setBmsSheetItems(Set bmsSheetItems) {
        this.bmsSheetItems = bmsSheetItems;
    }
   








}
