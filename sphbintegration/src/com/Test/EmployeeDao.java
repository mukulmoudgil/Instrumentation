package com.Test;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class EmployeeDao {
HibernateTemplate template;
public void setTemplate(HibernateTemplate template) {
	this.template = template;
}

public void saveEmployee(Employee e){
        template.saveOrUpdate(e);

}

public void deleteEmployee(Employee e){
	template.delete(e);
}
}
