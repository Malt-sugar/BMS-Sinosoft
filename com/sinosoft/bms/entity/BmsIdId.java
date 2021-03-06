package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:48 by Hibernate Tools 3.1.0.beta4



/**
 * BmsIdId generated by hbm2java
 */

public class BmsIdId  implements java.io.Serializable {


    // Fields    

     private String tableName;
     private Integer currId;


    // Constructors

    /** default constructor */
    public BmsIdId() {
    }

    
    /** full constructor */
    public BmsIdId(String tableName, Integer currId) {
        this.tableName = tableName;
        this.currId = currId;
    }
    

   
    // Property accessors

    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getCurrId() {
        return this.currId;
    }
    
    public void setCurrId(Integer currId) {
        this.currId = currId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BmsIdId) ) return false;
		 BmsIdId castOther = ( BmsIdId ) other; 
         
		 return ( (this.getTableName()==castOther.getTableName()) || ( this.getTableName()!=null && castOther.getTableName()!=null && this.getTableName().equals(castOther.getTableName()) ) )
 && ( (this.getCurrId()==castOther.getCurrId()) || ( this.getCurrId()!=null && castOther.getCurrId()!=null && this.getCurrId().equals(castOther.getCurrId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTableName() == null ? 0 : this.getTableName().hashCode() );
         result = 37 * result + ( getCurrId() == null ? 0 : this.getCurrId().hashCode() );
         return result;
   }   





}
