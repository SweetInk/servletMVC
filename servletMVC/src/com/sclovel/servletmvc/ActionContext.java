package com.sclovel.servletmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ServantActivatorOperations;
/**
 * Action 上下文
 * @author SUCHU
 *
 */
public class ActionContext {
	public  static HttpServletRequest request;
	public  static HttpServletResponse response;
	private static ActionContext instance;
	public static final String session ="ACTION_SESSION";
	private Map<String,Object> context;
	static ThreadLocal<ActionContext> actionContext = new ThreadLocal<ActionContext>();
	private ActionContext(){}
	
	/**
	 * 设置ActionContext实例
	 * @param context
	 */
	public static void setActionContext(ActionContext context){
		actionContext.set(context);
	}
	
	/**
	 * 获取ActionContext 实例
	 * @return
	 */
/*	
	public static ActionContext getContext(){
		return actionContext.get();
	}
	*/
	public static ActionContext getContext(){
		if(null==instance)
			instance = new ActionContext();
		return instance;
	}
	/**
	 *  <b>为request设置attribute</b>
	 * @param key Request的Attribute Key
	 * @param value Request的Attribute Value
	 */
	public void put(String key,Object value){
		context.put(key, value);
		request.setAttribute(key, value);
	}
}
