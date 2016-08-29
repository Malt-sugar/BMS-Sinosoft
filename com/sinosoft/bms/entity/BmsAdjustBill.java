package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * BmsAdjustBill generated by hbm2java
 */

public class BmsAdjustBill  implements java.io.Serializable {


    // Fields    

     private int adjBillId;
     private BmsObject bmsObject;
     private String adjBillCode;
     private String adjReason;
     private Integer applyUser;
     private String applyDate;
     private Integer apprUser;
     private String apprDate;
     private Integer enableUser;
     private String enableDate;
     private BigDecimal adjAmt;
     private Character apprFlag;
     private Character enableFlag;
     private Character holdFlag;


    // Constructors

    /** default constructor */
    public BmsAdjustBill() {
    }

	/** minimal constructor */
    public BmsAdjustBill(int adjBillId) {
        this.adjBillId = adjBillId;
    }
    
    /** full constructor */
    public BmsAdjustBill(int adjBillId, BmsObject bmsObject, String adjBillCode, String adjReason, Integer applyUser, String applyDate, Integer apprUser, String apprDate, Integer enableUser, String enableDate, BigDecimal adjAmt, Character apprFlag, Character enableFlag, Character holdFlag) {
        this.adjBillId = adjBillId;
        this.bmsObject = bmsObject;
        this.adjBillCode = adjBillCode;
        this.adjReason = adjReason;
        this.applyUser = applyUser;
        this.applyDate = applyDate;
        this.apprUser = apprUser;
        this.apprDate = apprDate;
        this.enableUser = enableUser;
        this.enableDate = enableDate;
        this.adjAmt = adjAmt;
        this.apprFlag = apprFlag;
        this.enableFlag = enableFlag;
        this.holdFlag = holdFlag;
    }
    

   
    // Property accessors

    public int getAdjBillId() {
        return this.adjBillId;
    }
    
    public void setAdjBillId(int adjBillId) {
        this.adjBillId = adjBillId;
    }

    public BmsObject getBmsObject() {
        return this.bmsObject;
    }
    
    public void setBmsObject(BmsObject bmsObject) {
        this.bmsObject = bmsObject;
    }

    public String getAdjBillCode() {
        return this.adjBillCode;
    }
    
    public void setAdjBillCode(String adjBillCode) {
        this.adjBillCode = adjBillCode;
    }

    public String getAdjReason() {
        return this.adjReason;
    }
    
    public void setAdjReason(String adjReason) {
        this.adjReason = adjReason;
    }

    public Integer getApplyUser() {
        return this.applyUser;
    }
    
    public void setApplyUser(Integer applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyDate() {
        return this.applyDate;
    }
    
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApprUser() {
        return this.apprUser;
    }
    
    public void setApprUser(Integer apprUser) {
        this.apprUser = apprUser;
    }

    public String getApprDate() {
        return this.apprDate;
    }
    
    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    public Integer getEnableUser() {
        return this.enableUser;
    }
    
    public void setEnableUser(Integer enableUser) {
        this.enableUser = enableUser;
    }

    public String getEnableDate() {
        return this.enableDate;
    }
    
    public void setEnableDate(String enableDate) {
        this.enableDate = enableDate;
    }

    public BigDecimal getAdjAmt() {
        return this.adjAmt;
    }
    
    public void setAdjAmt(BigDecimal adjAmt) {
        this.adjAmt = adjAmt;
    }

    public Character getApprFlag() {
        return this.apprFlag;
    }
    
    public void setApprFlag(Character apprFlag) {
        this.apprFlag = apprFlag;
    }

    public Character getEnableFlag() {
        return this.enableFlag;
    }
    
    public void setEnableFlag(Character enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Character getHoldFlag() {
        return this.holdFlag;
    }
    
    public void setHoldFlag(Character holdFlag) {
        this.holdFlag = holdFlag;
    }
   








}