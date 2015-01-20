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
        e.setId(20);
        e.setName("Mukul");
        e.setSalary(5000);
	
	//dao.saveEmployee(e);
        dao.saveEmployee(e);
}
}
