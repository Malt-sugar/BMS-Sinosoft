package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * BmsTpParamDim generated by hbm2java
 */

public class BmsTpParamDim  implements java.io.Serializable {


    // Fields    

     private int tpParamDimId;
     private BmsDim bmsDim;
     private BmsTemplet bmsTemplet;
     private BmsDimMember bmsDimMember;
     private Set bmsSheets = new HashSet(0);


    // Constructors

    /** default constructor */
    public BmsTpParamDim() {
    }

	/** minimal constructor */
    public BmsTpParamDim(int tpParamDimId) {
        this.tpParamDimId = tpParamDimId;
    }
    
    /** full constructor */
    public BmsTpParamDim(int tpParamDimId, BmsDim bmsDim, BmsTemplet bmsTemplet, BmsDimMember bmsDimMember, Set bmsSheets) {
        this.tpParamDimId = tpParamDimId;
        this.bmsDim = bmsDim;
        this.bmsTemplet = bmsTemplet;
        this.bmsDimMember = bmsDimMember;
        this.bmsSheets = bmsSheets;
    }
    

   
    // Property accessors

    public int getTpParamDimId() {
        return this.tpParamDimId;
    }
    
    public void setTpParamDimId(int tpParamDimId) {
        this.tpParamDimId = tpParamDimId;
    }

    public BmsDim getBmsDim() {
        return this.bmsDim;
    }
    
    public void setBmsDim(BmsDim bmsDim) {
        this.bmsDim = bmsDim;
    }

    public BmsTemplet getBmsTemplet() {
        return this.bmsTemplet;
    }
    
    public void setBmsTemplet(BmsTemplet bmsTemplet) {
        this.bmsTemplet = bmsTemplet;
    }

    public BmsDimMember getBmsDimMember() {
        return this.bmsDimMember;
    }
    
    public void setBmsDimMember(BmsDimMember bmsDimMember) {
        this.bmsDimMember = bmsDimMember;
    }

    public Set getBmsSheets() {
        return this.bmsSheets;
    }
    
    public void setBmsSheets(Set bmsSheets) {
        this.bmsSheets = bmsSheets;
    }
   








}
