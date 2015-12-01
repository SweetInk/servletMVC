package com.sclovel.servletmvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public final  class ViewResolver {
	private final static ViewResolver instance = new ViewResolver();
	private ViewResolver(){}
	
	public static ViewResolver getInstance(){
		synchronized (instance) {
			return instance;
		}
	}
	public void pageResolve(ActionSupport action,HttpServletRequest requset,HttpServletResponse response){
		
	}
	public void errorPageResolve(String servletPath ,HttpServletRequest requset,HttpServletResponse resp) throws IOException{
		resp.getWriter().println("<center><h1>500 Internal Server Error</h1></center>\r\n");
		resp.getWriter().println("Not found such url or action: <font color='red'>"+servletPath+"</font>\r\n");
		resp.getWriter().println("<hr><center>Apache Tomcat/7.0.61</center>\r\n");
	}
	public void PageResolve(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		System.out.println("*-----PageResovle-----*");
		String servletPath = req.getServletPath();
		String actionName  = servletPath.substring(servletPath.indexOf("/")+1,servletPath.lastIndexOf("."));
		Map<String,String[]> mp =req.getParameterMap();
		System.out.println("servlet:"+actionName);
		ActionMapping mapping = (ActionMapping) DispatcherServlet.action.get(actionName);
		if(mapping==null){
			errorPageResolve(servletPath, req, resp);
			return;
		}
		String rs = null;
		ActionSupport con = null;
		try {
			System.out.println("className:"+mapping.getClassName());
			con = (ActionSupport) ObjectResolver.getClassInstance(Class.forName(mapping.getClassName()), mp);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			if(con==null){
				System.out.println("空指针");
			}
			if(mapping.getMethodName().equals(""))
				try {
					rs = con.execute();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else{
				Method m = null;
				try {
					m = con.getClass().getMethod(mapping.getMethodName());
					try {
						m.invoke(con, null);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(m==null){
					System.out.println("没有找到该方法..."+mapping.getMethodName());
					try {
						rs = con.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
		 
		System.out.println("result:"+rs);
		String frd = null;
		if(mapping.getResult().get(rs)!=null)
		frd = mapping.getResult().get(rs).toString();
		System.out.println("forward url:"+frd);
		if(frd!=null)
			req.getRequestDispatcher(frd).forward(req, resp);
	}
}
