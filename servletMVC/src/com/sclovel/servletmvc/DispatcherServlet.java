package com.sclovel.servletmvc;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Core of The Page Dispatcher
 * @author SUCHU
 *
 */
public class DispatcherServlet extends HttpServlet {
	/**
	 *  Sample MVC Servlet
	 */
	private static final long serialVersionUID = -6912234400094738011L;
	protected static Map<Object,Object> action = null;
	private static int i = 0;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("转发器初始化...");
		String xmlPath = getServletContext().getRealPath("/WEB-INF/classes/mvc-config.xml");
		System.out.println("path:"+xmlPath);
		action = XMLConfig.getMvcConfig(xmlPath);
		ActionContext.getContext();
		System.out.println("rest/sample:"+action.get("rest/sample")==null);
		super.init();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("----OVERRIDE METHOD-----");
	System.out.println(req.getServletPath());
	Map<String,String[]> mp =req.getParameterMap();
	ActionContext.request = req;
	ActionContext.response = resp;
	for(Map.Entry<String,String[]> t:mp.entrySet())
		System.out.println(t.getKey()+"="+t.getValue());
	ViewResolver.getInstance().PageResolve(req, resp);
	}
}
