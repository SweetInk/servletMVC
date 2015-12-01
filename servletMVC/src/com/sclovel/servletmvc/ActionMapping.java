package com.sclovel.servletmvc;

import java.util.HashMap;
import java.util.Map;

public class ActionMapping {
	private String name;
	private String className;
	private Map<Object,Object> result = new HashMap<Object,Object>();
	private String methodName;
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Map<Object, Object> getResult() {
		return result;
	}
	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}
}
