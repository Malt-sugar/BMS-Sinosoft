package com.sinosoft.bms.framework;

import java.net.URL;
import java.net.URLDecoder;

/**
 * 启动DODS
 * @author dengqiao
 *
 */
public final class DodsStarter {

	private static final String resourcePath = "sinosoftoa.config";
	
	public static void startup(){
		try {
			org.enhydra.dods.DODS.startup(getPath());
			System.out.println("DODS 启动成功！");
		} catch (Exception ex) {
			System.out.println(" DODS 启动出现异常！ ");
			ex.printStackTrace();
		}
	}
	
	private static URL getResource() {
		URL url = DodsStarter.class.getClassLoader().getResource(resourcePath);
		return url;
	}
	
	public static String getPath() {
		String charSet = "UTF-8";
		String s;
		try {
			s = getResource().getPath();
			return URLDecoder.decode(s, charSet);
		} catch (Exception e) {
		}
		return null;
	}
}
