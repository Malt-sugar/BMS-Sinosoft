package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * BmsScheme generated by hbm2java
 */

public class BmsScheme  implements java.io.Serializable {


    // Fields    

     private int bgSchId;
     private BmsObject bmsObject;
     private String bgSchCode;
     private String bgSchName;
     private String startDate;
     private String endDat;
     private Character useFlag;
     private Set bmsControls = new HashSet(0);
     private Set bmsAlerts = new HashSet(0);
     private Set bmsTemplets = new HashSet(0);


    // Constructors

    /** default constructor */
    public BmsScheme() {
    }

	/** minimal constructor */
    public BmsScheme(int bgSchId) {
        this.bgSchId = bgSchId;
    }
    
    /** full constructor */
    public BmsScheme(int bgSchId, BmsObject bmsObject, String bgSchCode, String bgSchName, String startDate, String endDat, Character useFlag, Set bmsControls, Set bmsAlerts, Set bmsTemplets) {
        this.bgSchId = bgSchId;
        this.bmsObject = bmsObject;
        this.bgSchCode = bgSchCode;
        this.bgSchName = bgSchName;
        this.startDate = startDate;
        this.endDat = endDat;
        this.useFlag = useFlag;
        this.bmsControls = bmsControls;
        this.bmsAlerts = bmsAlerts;
        this.bmsTemplets = bmsTemplets;
    }
    

   
    // Property accessors

    public int getBgSchId() {
        return this.bgSchId;
    }
    
    public void setBgSchId(int bgSchId) {
        this.bgSchId = bgSchId;
    }

    public BmsObject getBmsObject() {
        return this.bmsObject;
    }
    
    public void setBmsObject(BmsObject bmsObject) {
        this.bmsObject = bmsObject;
    }

    public String getBgSchCode() {
        return this.bgSchCode;
    }
    
    public void setBgSchCode(String bgSchCode) {
        this.bgSchCode = bgSchCode;
    }

    public String getBgSchName() {
        return this.bgSchName;
    }
    
    public void setBgSchName(String bgSchName) {
        this.bgSchName = bgSchName;
    }

    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDat() {
        return this.endDat;
    }
    
    public void setEndDat(String endDat) {
        this.endDat = endDat;
    }

    public Character getUseFlag() {
        return this.useFlag;
    }
    
    public void setUseFlag(Character useFlag) {
        this.useFlag = useFlag;
    }

    public Set getBmsControls() {
        return this.bmsControls;
    }
    
    public void setBmsControls(Set bmsControls) {
        this.bmsControls = bmsControls;
    }

    public Set getBmsAlerts() {
        return this.bmsAlerts;
    }
    
    public void setBmsAlerts(Set bmsAlerts) {
        this.bmsAlerts = bmsAlerts;
    }

    public Set getBmsTemplets() {
        return this.bmsTemplets;
    }
    
    public void setBmsTemplets(Set bmsTemplets) {
        this.bmsTemplets = bmsTemplets;
    }
   








}
