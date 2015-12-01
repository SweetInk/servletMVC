package com.sclovel.servletmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ServantActivatorOperations;
/**
 * Action ������
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
	 * ����ActionContextʵ��
	 * @param context
	 */
	public static void setActionContext(ActionContext context){
		actionContext.set(context);
	}
	
	/**
	 * ��ȡActionContext ʵ��
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
	 *  <b>Ϊrequest����attribute</b>
	 * @param key Request��Attribute Key
	 * @param value Request��Attribute Value
	 */
	public void put(String key,Object value){
		context.put(key, value);
		request.setAttribute(key, value);
	}
}
