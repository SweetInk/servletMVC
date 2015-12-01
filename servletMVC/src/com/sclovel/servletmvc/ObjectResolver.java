package com.sclovel.servletmvc;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.Symbol;

public class ObjectResolver {
	@SuppressWarnings("null")
	public static <T>Object getClassInstance (T t,Map<String,String[]> param){
		T obj = null;
		Map<String,String> map = new HashMap<>();
		for(Map.Entry<String,String[]> mp:param.entrySet()){
			map.put(mp.getKey()	, mp.getValue()[0]);
		}
		Class cls = null;
		cls = (Class) t;
		try {
			obj = (T) cls.newInstance();
			Field fields[] = cls.getFields();
			int i = 0;
			for(Field field:fields){
			//	System.out.println("Filed Name:"+field.getName());
				PropertyDescriptor pd = null;
				String fieldName = field.getName();
				String paramValue = map.get(fieldName);
			//	System.out.println("paramValue:"+paramValue);
				try {
					pd = new PropertyDescriptor(fields[i].getName(), cls);
					if(paramValue!=null){
						Method method = pd.getWriteMethod();
						System.out.println("Method Name:"+method.getName());
						try {
							method.invoke(obj, paramValue);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					continue;
				//	e.printStackTrace();
				}
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
		
	}
}
