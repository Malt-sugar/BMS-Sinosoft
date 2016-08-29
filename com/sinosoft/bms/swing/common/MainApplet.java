/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.fife.plaf.OfficeXP.OfficeXPLookAndFeel;

import com.sinosoft.bms.clientcommon.ClientCallRemote;


/**
 * @author sunrui
 *
 */
public class MainApplet extends JApplet {

	protected JPanel mainPanel = null;
	public Properties prop = null;  //  @jve:decl-index=0:
	private JPanel topPanel = null;
	protected FuncPanel funcPanel = null;

	/**
	 * @throws HeadlessException
	 */
	public MainApplet() throws HeadlessException {
		super();
		try {
			
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			
			Properties logProp = new Properties();
			logProp.load(getClass().getClassLoader().getResourceAsStream("log4j.properties"));
			
			PropertyConfigurator.configure(logProp);
			
			System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "error");
			System.setProperty("sun.java2d.noddraw", "true");
			
			//System.setProperty("swing.noxp", "true");
			
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			System.setProperty(OfficeXPLookAndFeel.THEME_PROPERTY, OfficeXPLookAndFeel.THEME_CLASSIC);
			UIManager.setLookAndFeel("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
			//UIManager.setLookAndFeel("org.fife.plaf.Office2003.Office2003LookAndFeel");
			//UIManager.setLookAndFeel("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel");
			Font font = new Font("宋体",Font.PLAIN,12);
			initGlobalFontSetting(font);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * This method initializes this
	 * 
	 */
	public void init() {
		ClientEnv.getInstance().setMainApplet(this);
        this.setSize(new Dimension(369, 168));
        this.setContentPane(getMainPanel());
		
        try {
			String strFuncPanelClass = getParameter("funcPanelClass");
			
			ClientCallRemote.host = getParameter("serverName");
			ClientCallRemote.port = Integer.parseInt(getParameter("serverPort"));
			ClientCallRemote.contextPath = getParameter("contextPath");
			ClientCallRemote.jsessionid = getParameter("JSESSIONID");
			
			ClientEnv.getInstance().setUserID(Integer.parseInt(getParameter("userID")));
			
			Class funcPanelClass = Class.forName(strFuncPanelClass);
			Constructor constructor= funcPanelClass.getConstructor(new Class[]{MainApplet.class});
			Object objFuncPanel = constructor.newInstance(new Object[]{this});
			funcPanel = (FuncPanel)objFuncPanel;
			getMainPanel().add(funcPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
        
	}

	/**
	 * This method initializes mainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(getTopPanel(), BorderLayout.NORTH);
			//mainPanel.add(getContentPanel(), BorderLayout.CENTER);
		}
		return mainPanel;
	}

	/**
	 * This method initializes topPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTopPanel() {
		if (topPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setVgap(2);
			topPanel = new JPanel();
			//topPanel.setBackground(new Color(236,233,216));
			topPanel.setLayout(flowLayout);
			topPanel.setPreferredSize(new Dimension(0, 30));
		}
		return topPanel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final MainApplet applet = new MainApplet();
		
		Properties prop = new Properties();
		prop.setProperty("funcPanelClass", args[0]);
		prop.setProperty("userID", "25");
		prop.setProperty("serverName", "localhost");
		prop.setProperty("serverPort", "9090");
		prop.setProperty("contextPath", "bms");
		applet.setProp(prop);
		
		Frame frame = new Frame("调试");
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event) {
				applet.stop();
				applet.destroy();
				System.exit(0);
			}
		});
		
		frame.add("Center", applet);
		frame.show();
		applet.init();
		applet.start();
		frame.pack();
		frame.setLocation(100, 100);
		frame.setSize(800, 600);
		frame.validate();
	}
	
	public void initGlobalFontSetting(Font fnt)
	{
		
		//System.getProperties().list(System.out);
		FontUIResource fontRes = new FontUIResource(fnt);
		
		for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			
//			if(value instanceof ColorUIResource) {
//				ColorUIResource colorRes = (ColorUIResource) value;
//				System.out.println(key+"="+colorRes.getRGB());
//			}
			
			//System.out.println(key+"="+value);
			
			if(value instanceof FontUIResource)UIManager.put(key, fontRes);
		}
		
//		try {
//			Properties colorProp = new Properties();
//			colorProp.load(getClass().getClassLoader().getResourceAsStream("com/sinosoft/bms/swing/common/color.properties"));
//			Enumeration colorKeys = colorProp.keys();
//			while(colorKeys.hasMoreElements()) {
//				Object colorKey = colorKeys.nextElement();
//				Object value = colorProp.get(colorKey);
//				
//				UIManager.put(colorKey, new ColorUIResource(new Color(Integer.parseInt(value.toString()))));
//				
//			}
//		}  catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
		InsetsUIResource uires = new InsetsUIResource(0,0,0,0);
		UIManager.getDefaults().put("TextField.margin", uires);
		UIManager.getDefaults().put("Tree.rowHeight", new Integer(18));
		UIManager.getDefaults().put("Tree.lineTypeDashed", Boolean.TRUE);

	}

	/* (non-Javadoc)
	 * @see java.applet.Applet#getParameter(java.lang.String)
	 */
	public String getParameter(String name) {
		if(prop!=null) {
			//调试模式由此加载
			return prop.getProperty(name);
		}
		return super.getParameter(name);
	}

	/**
	 * @return the prop
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public void setButtons(TitleButton [] buttons) {
		getTopPanel().removeAll();
		if(buttons!=null) {
			for (int i = 0; i < buttons.length; i++) {
				getTopPanel().add(buttons[i],null);
				buttons[i].bMouseInButton=false;
				buttons[i].repaint();
				
				ActionListener [] actionListeners = buttons[i].getActionListeners();
				for (int j = 0; j < actionListeners.length; j++) {
					buttons[i].removeActionListener(actionListeners[j]);
				}
				
				buttons[i].addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						if(e.getSource()!=null && e.getSource() instanceof TitleButton) {
							TitleButton btn = (TitleButton)e.getSource();
							if(btn.isEnabled()) {
								funcPanel.onButtonClick(btn);
							}
						}
					}
					
				});

			}
		}
		getTopPanel().revalidate();
		getTopPanel().repaint();
	}
	

	/**
	 * @return the funcPanel
	 */
	public FuncPanel getFuncPanel() {
		return funcPanel;
	}

	/**
	 * @param funcPanel the funcPanel to set
	 */
	public void setFuncPanel(FuncPanel funcPanel) {
		this.funcPanel = funcPanel;
	}

}
