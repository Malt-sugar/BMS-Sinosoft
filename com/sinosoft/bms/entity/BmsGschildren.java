package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4



/**
 * BmsGschildren generated by hbm2java
 */

public class BmsGschildren  implements java.io.Serializable {


    // Fields    

     private BmsGschildrenId id;
     private BmsSheet bmsSheet;


    // Constructors

    /** default constructor */
    public BmsGschildren() {
    }

	/** minimal constructor */
    public BmsGschildren(BmsGschildrenId id) {
        this.id = id;
    }
    
    /** full constructor */
    public BmsGschildren(BmsGschildrenId id, BmsSheet bmsSheet) {
        this.id = id;
        this.bmsSheet = bmsSheet;
    }
    

   
    // Property accessors

    public BmsGschildrenId getId() {
        return this.id;
    }
    
    public void setId(BmsGschildrenId id) {
        this.id = id;
    }

    public BmsSheet getBmsSheet() {
        return this.bmsSheet;
    }
    
    public void setBmsSheet(BmsSheet bmsSheet) {
        this.bmsSheet = bmsSheet;
    }
   








}
