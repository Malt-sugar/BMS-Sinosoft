package com.sinosoft.bms.entity;
// Generated 2009-5-19 11:36:48 by Hibernate Tools 3.1.0.beta4



/**
 * BmsPeriodId generated by hbm2java
 */

public class BmsPeriodId  implements java.io.Serializable {


    // Fields    

     private String bgPrdCode;
     private String bgPrdName;
     private Integer prdSchId;
     private Character prdType;
     private Integer prdYear;
     private Integer startMonth;
     private Integer endMonth;
     private String startDate;
     private String endDate;


    // Constructors

    /** default constructor */
    public BmsPeriodId() {
    }

    
    /** full constructor */
    public BmsPeriodId(String bgPrdCode, String bgPrdName, Integer prdSchId, Character prdType, Integer prdYear, Integer startMonth, Integer endMonth, String startDate, String endDate) {
        this.bgPrdCode = bgPrdCode;
        this.bgPrdName = bgPrdName;
        this.prdSchId = prdSchId;
        this.prdType = prdType;
        this.prdYear = prdYear;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    

   
    // Property accessors

    public String getBgPrdCode() {
        return this.bgPrdCode;
    }
    
    public void setBgPrdCode(String bgPrdCode) {
        this.bgPrdCode = bgPrdCode;
    }

    public String getBgPrdName() {
        return this.bgPrdName;
    }
    
    public void setBgPrdName(String bgPrdName) {
        this.bgPrdName = bgPrdName;
    }

    public Integer getPrdSchId() {
        return this.prdSchId;
    }
    
    public void setPrdSchId(Integer prdSchId) {
        this.prdSchId = prdSchId;
    }

    public Character getPrdType() {
        return this.prdType;
    }
    
    public void setPrdType(Character prdType) {
        this.prdType = prdType;
    }

    public Integer getPrdYear() {
        return this.prdYear;
    }
    
    public void setPrdYear(Integer prdYear) {
        this.prdYear = prdYear;
    }

    public Integer getStartMonth() {
        return this.startMonth;
    }
    
    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return this.endMonth;
    }
    
    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BmsPeriodId) ) return false;
		 BmsPeriodId castOther = ( BmsPeriodId ) other; 
         
		 return ( (this.getBgPrdCode()==castOther.getBgPrdCode()) || ( this.getBgPrdCode()!=null && castOther.getBgPrdCode()!=null && this.getBgPrdCode().equals(castOther.getBgPrdCode()) ) )
 && ( (this.getBgPrdName()==castOther.getBgPrdName()) || ( this.getBgPrdName()!=null && castOther.getBgPrdName()!=null && this.getBgPrdName().equals(castOther.getBgPrdName()) ) )
 && ( (this.getPrdSchId()==castOther.getPrdSchId()) || ( this.getPrdSchId()!=null && castOther.getPrdSchId()!=null && this.getPrdSchId().equals(castOther.getPrdSchId()) ) )
 && ( (this.getPrdType()==castOther.getPrdType()) || ( this.getPrdType()!=null && castOther.getPrdType()!=null && this.getPrdType().equals(castOther.getPrdType()) ) )
 && ( (this.getPrdYear()==castOther.getPrdYear()) || ( this.getPrdYear()!=null && castOther.getPrdYear()!=null && this.getPrdYear().equals(castOther.getPrdYear()) ) )
 && ( (this.getStartMonth()==castOther.getStartMonth()) || ( this.getStartMonth()!=null && castOther.getStartMonth()!=null && this.getStartMonth().equals(castOther.getStartMonth()) ) )
 && ( (this.getEndMonth()==castOther.getEndMonth()) || ( this.getEndMonth()!=null && castOther.getEndMonth()!=null && this.getEndMonth().equals(castOther.getEndMonth()) ) )
 && ( (this.getStartDate()==castOther.getStartDate()) || ( this.getStartDate()!=null && castOther.getStartDate()!=null && this.getStartDate().equals(castOther.getStartDate()) ) )
 && ( (this.getEndDate()==castOther.getEndDate()) || ( this.getEndDate()!=null && castOther.getEndDate()!=null && this.getEndDate().equals(castOther.getEndDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBgPrdCode() == null ? 0 : this.getBgPrdCode().hashCode() );
         result = 37 * result + ( getBgPrdName() == null ? 0 : this.getBgPrdName().hashCode() );
         result = 37 * result + ( getPrdSchId() == null ? 0 : this.getPrdSchId().hashCode() );
         result = 37 * result + ( getPrdType() == null ? 0 : this.getPrdType().hashCode() );
         result = 37 * result + ( getPrdYear() == null ? 0 : this.getPrdYear().hashCode() );
         result = 37 * result + ( getStartMonth() == null ? 0 : this.getStartMonth().hashCode() );
         result = 37 * result + ( getEndMonth() == null ? 0 : this.getEndMonth().hashCode() );
         result = 37 * result + ( getStartDate() == null ? 0 : this.getStartDate().hashCode() );
         result = 37 * result + ( getEndDate() == null ? 0 : this.getEndDate().hashCode() );
         return result;
   }   





}
