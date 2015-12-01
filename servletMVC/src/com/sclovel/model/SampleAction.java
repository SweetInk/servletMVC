package com.sclovel.model;

import java.io.IOException;

import com.sclovel.servletmvc.ActionContext;
import com.sclovel.servletmvc.ActionSupport;

public class SampleAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext.response.getWriter().println("Hello,This a SampleAction");
		return super.execute();
	}
	public String methodTest() throws IOException{
		ActionContext.response.getWriter().println("Hello,This a action methodTest");
		System.out.println("÷¥––¡À");
		return SUCCESS;
	}
}
