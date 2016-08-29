package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:47 by Hibernate Tools 3.1.0.beta4



/**
 * BmsUserMenu generated by hbm2java
 */

public class BmsUserMenu  implements java.io.Serializable {


    // Fields    

     private BmsUserMenuId id;
     private BmsUser bmsUser;
     private BmsMenu bmsMenu;


    // Constructors

    /** default constructor */
    public BmsUserMenu() {
    }

	/** minimal constructor */
    public BmsUserMenu(BmsUserMenuId id) {
        this.id = id;
    }
    
    /** full constructor */
    public BmsUserMenu(BmsUserMenuId id, BmsUser bmsUser, BmsMenu bmsMenu) {
        this.id = id;
        this.bmsUser = bmsUser;
        this.bmsMenu = bmsMenu;
    }
    

   
    // Property accessors

    public BmsUserMenuId getId() {
        return this.id;
    }
    
    public void setId(BmsUserMenuId id) {
        this.id = id;
    }

    public BmsUser getBmsUser() {
        return this.bmsUser;
    }
    
    public void setBmsUser(BmsUser bmsUser) {
        this.bmsUser = bmsUser;
    }

    public BmsMenu getBmsMenu() {
        return this.bmsMenu;
    }
    
    public void setBmsMenu(BmsMenu bmsMenu) {
        this.bmsMenu = bmsMenu;
    }
   








}
