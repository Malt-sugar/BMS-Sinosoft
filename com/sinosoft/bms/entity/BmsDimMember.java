package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * BmsDimMember generated by hbm2java
 */

public class BmsDimMember  implements java.io.Serializable {


    // Fields    

     private int dimMemId;
     private BmsDim bmsDim;
     private String dimMemCode;
     private String dimMemName;
     private String operSysCode;
     private Integer parentDimMem;
     private Set bmsGsparamDims = new HashSet(0);
     private Set bmsTpParamDims = new HashSet(0);


    // Constructors

    /** default constructor */
    public BmsDimMember() {
    }

	/** minimal constructor */
    public BmsDimMember(int dimMemId) {
        this.dimMemId = dimMemId;
    }
    
    /** full constructor */
    public BmsDimMember(int dimMemId, BmsDim bmsDim, String dimMemCode, String dimMemName, String operSysCode, Integer parentDimMem, Set bmsGsparamDims, Set bmsTpParamDims) {
        this.dimMemId = dimMemId;
        this.bmsDim = bmsDim;
        this.dimMemCode = dimMemCode;
        this.dimMemName = dimMemName;
        this.operSysCode = operSysCode;
        this.parentDimMem = parentDimMem;
        this.bmsGsparamDims = bmsGsparamDims;
        this.bmsTpParamDims = bmsTpParamDims;
    }
    

   
    // Property accessors

    public int getDimMemId() {
        return this.dimMemId;
    }
    
    public void setDimMemId(int dimMemId) {
        this.dimMemId = dimMemId;
    }

    public BmsDim getBmsDim() {
        return this.bmsDim;
    }
    
    public void setBmsDim(BmsDim bmsDim) {
        this.bmsDim = bmsDim;
    }

    public String getDimMemCode() {
        return this.dimMemCode;
    }
    
    public void setDimMemCode(String dimMemCode) {
        this.dimMemCode = dimMemCode;
    }

    public String getDimMemName() {
        return this.dimMemName;
    }
    
    public void setDimMemName(String dimMemName) {
        this.dimMemName = dimMemName;
    }

    public String getOperSysCode() {
        return this.operSysCode;
    }
    
    public void setOperSysCode(String operSysCode) {
        this.operSysCode = operSysCode;
    }

    public Integer getParentDimMem() {
        return this.parentDimMem;
    }
    
    public void setParentDimMem(Integer parentDimMem) {
        this.parentDimMem = parentDimMem;
    }

    public Set getBmsGsparamDims() {
        return this.bmsGsparamDims;
    }
    
    public void setBmsGsparamDims(Set bmsGsparamDims) {
        this.bmsGsparamDims = bmsGsparamDims;
    }

    public Set getBmsTpParamDims() {
        return this.bmsTpParamDims;
    }
    
    public void setBmsTpParamDims(Set bmsTpParamDims) {
        this.bmsTpParamDims = bmsTpParamDims;
    }
   








}
