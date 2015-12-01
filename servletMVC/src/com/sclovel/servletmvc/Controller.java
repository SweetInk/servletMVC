package com.sclovel.servletmvc;
public @interface Controller {
	  String value() default "";
	String method() ;
}
