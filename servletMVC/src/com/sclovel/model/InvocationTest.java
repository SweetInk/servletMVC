package com.sclovel.model;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sun.java.util.*;




public class InvocationTest {
	public static void main(String[] args) throws IntrospectionException, IllegalArgumentException, InvocationTargetException {
		try {
			Student obj = (Student) Class.forName("com.sclovel.model.Student").newInstance();
			Field[]fields = obj.getClass().getDeclaredFields();
			for(Field f:fields){
				System.out.println(f.getName());
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), obj.getClass());
				Method m = pd.getWriteMethod();
				
				System.out.println(m.getName());
				m.invoke(obj, "test");
			}
			System.out.println(obj.getName());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
