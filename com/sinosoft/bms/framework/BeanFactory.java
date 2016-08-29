/**
 *
 * Created on 2009-4-7
 * @author sunrui
 *
 */
package com.sinosoft.bms.framework;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sunrui
 *
 */
public class BeanFactory {

	protected static ApplicationContext ctx = null;
	protected static Logger logger = Logger.getLogger(BeanFactory.class);
	
	static {
		try {
			logger.info("��ʼ����ApplicationContext...");
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			logger.info("����ApplicationContext�ɹ���");
		} catch (Error e) {
			logger.error("����ApplicationContextʧ�ܣ�", e);
			throw e;
		}
		catch (Exception e) {
			logger.error("����ApplicationContextʧ�ܣ�", e);
		}
	}
	
	/**
	 * 
	 */
	public BeanFactory() {
	}
	
	public static Object getBean(String name) throws Exception {
		return ctx.getBean(name);
	}

}
