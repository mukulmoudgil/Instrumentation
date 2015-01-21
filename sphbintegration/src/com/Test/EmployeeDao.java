package com.Test;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class EmployeeDao {
HibernateTemplate template;
public void setTemplate(HibernateTemplate template) {
	this.template = template;
}

public void saveEmployee(Employee e){
        System.out.println("Open Session count" + " :"
                + template.getSessionFactory().getStatistics().getSessionOpenCount());
        template.saveOrUpdate(e);
        System.out.println("Open Session count" + " :"
                + template.getSessionFactory().getStatistics().getSessionOpenCount());
        System.out.println("connect Count" + " :" + template.getSessionFactory().getStatistics().getConnectCount());
        System.out.println("Close Count" + " :" + template.getSessionFactory().getStatistics().getSessionCloseCount());
        System.out.println("Close Count" + " :" + template.getSessionFactory().getStatistics().getSessionCloseCount());
    }

public void deleteEmployee(Employee e){
	template.delete(e);
}
}
