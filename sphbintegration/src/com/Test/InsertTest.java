package com.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SuppressWarnings("deprecation")
public class InsertTest {
public static void main(String[] args) {
	
	Resource r=new ClassPathResource("applicationContext.xml");
	BeanFactory factory=new XmlBeanFactory(r);
	
	EmployeeDao dao=(EmployeeDao)factory.getBean("d");
	
	Employee e=new Employee();
        e.setId(37);
        e.setName("Admin");
        e.setSalary(10000);
        StackTraceElement[] currentThread = Thread.currentThread().getStackTrace();
        for (StackTraceElement ct : currentThread) {
            System.out.println(ct.getMethodName().toString());
        }
	//dao.saveEmployee(e);
        dao.saveEmployee(e);
}
}
