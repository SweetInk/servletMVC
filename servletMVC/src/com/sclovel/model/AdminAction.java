package com.sclovel.model;

import com.sclovel.servletmvc.ActionContext;
import com.sclovel.servletmvc.ActionSupport;
import com.sclovel.servletmvc.Controller;
import com.sun.corba.se.spi.orbutil.fsm.Action;

public class AdminAction extends ActionSupport{
	public String name;
	public String pwd;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(ActionContext.request==null){
			System.out.println("Null Request");
		}
		if(getName().equals("admin")){
			ActionContext.getContext().put("user", getName());
		return SUCCESS;
		}
		else {
			ActionContext.getContext().put("error", "错误的用户名:"+getName());
			return INPUT;}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
