/**
 *
 * Created on 2009-5-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @author sunrui
 *
 */
public class BmsDouble implements Serializable {

	public double value = 0.0d;
	public int fractionDigits = 2; //Ð¡ÊýÎ»
	public boolean percent = false;
	
	public BmsDouble() {
		super();
	}

	public BmsDouble(double value) {
		this.value=value;
	}
	
	public BmsDouble(BigDecimal decimal) {
		this.value=(decimal==null)?0.0d:decimal.doubleValue();
	}
	
	public BmsDouble(String str) {
		try {
			str = str.replaceAll(",", "");
			this.value=Double.parseDouble(str);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	
	public double doubleValue() {
		return value;
	}
	
	public String toString() {
		String str = "";
		try {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(fractionDigits);
			nf.setMinimumFractionDigits(fractionDigits);
			if(isPercent()) {
				str = nf.format(value*100)+"%";
			} else {
				str = nf.format(value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String [] args) {
		BmsDouble val = new BmsDouble(0.456);
		val.setFractionDigits(2);
		val.setPercent(false);
		System.out.println(val.toString());
	}

	/**
	 * @return the fractionDigits
	 */
	public int getFractionDigits() {
		return fractionDigits;
	}

	/**
	 * @param fractionDigits the fractionDigits to set
	 */
	public void setFractionDigits(int fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the percent
	 */
	public boolean isPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(boolean percent) {
		this.percent = percent;
	}

}
