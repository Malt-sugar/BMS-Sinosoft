package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4



/**
 * BmsControl generated by hbm2java
 */

public class BmsControl  implements java.io.Serializable {


    // Fields    

     private BmsControlId id;
     private BmsItem bmsItem;
     private BmsScheme bmsScheme;


    // Constructors

    /** default constructor */
    public BmsControl() {
    }

	/** minimal constructor */
    public BmsControl(BmsControlId id) {
        this.id = id;
    }
    
    /** full constructor */
    public BmsControl(BmsControlId id, BmsItem bmsItem, BmsScheme bmsScheme) {
        this.id = id;
        this.bmsItem = bmsItem;
        this.bmsScheme = bmsScheme;
    }
    

   
    // Property accessors

    public BmsControlId getId() {
        return this.id;
    }
    
    public void setId(BmsControlId id) {
        this.id = id;
    }

    public BmsItem getBmsItem() {
        return this.bmsItem;
    }
    
    public void setBmsItem(BmsItem bmsItem) {
        this.bmsItem = bmsItem;
    }

    public BmsScheme getBmsScheme() {
        return this.bmsScheme;
    }
    
    public void setBmsScheme(BmsScheme bmsScheme) {
        this.bmsScheme = bmsScheme;
    }
   








}
