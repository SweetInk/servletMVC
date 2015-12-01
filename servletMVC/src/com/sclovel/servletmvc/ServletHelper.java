package com.sclovel.servletmvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/** 
* 
*----------Dragon be here!----------/ 
* 　　　┏┓　　　┏┓ 
* 　　┏┛┻━━━┛┻┓ 
* 　　┃　　　　　　　┃ 
* 　　┃　　　━　　　┃ 
* 　　┃　┳┛　┗┳　┃ 
* 　　┃　　　　　　　┃ 
* 　　┃　　　┻　　　┃ 
* 　　┃　　　　　　　┃ 
* 　　┗━┓　　　┏━┛ 
* 　　　　┃　　　┃神兽保佑 
* 　　　　┃　　　┃代码无BUG！ 
* 　　　　┃　　　┗━━━┓ 
* 　　　　┃　　　　　　　┣┓ 
* 　　　　┃　　　　　　　┏┛ 
* 　　　　┗┓┓┏━┳┓┏┛ 
* 　　　　　┃┫┫　┃┫┫ 
* 　　　　　┗┻┛　┗┻┛ 
* ━━━━━━神兽出没━━━━━━by:suchu
*/  
public final class ServletHelper {
	private static final ThreadLocal<ServletHelper> local = new ThreadLocal<ServletHelper>();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private  ServletHelper(HttpServletRequest res, HttpServletResponse resp){
		this.request = res;
		this.response = resp;
	}
	
	public static void init(HttpServletRequest request,HttpServletResponse response){
		local.set(new ServletHelper(request, response));
	}
	
	public static void destroy(){
		local.remove();
	}
	
	private static HttpServletRequest getRequset(){
		return local.get().request;
	}
	
	private static HttpServletResponse getResponse(){
		return local.get().response;
	}
	private static HttpSession getSession(){
		return getRequset().getSession();
	}
	private static ServletContext getServletContext(){
		return getRequset().getServletContext();
	}
	public static void put(String key,Object value){
		getRequset().setAttribute(key, value);
		 
	}
	
	
}
