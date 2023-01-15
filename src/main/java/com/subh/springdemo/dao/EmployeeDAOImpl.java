package com.subh.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.entity.EmployeeLoginManager;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Employee> getEmployees() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query ... sort by last name
		Query<Employee> theQuery = currentSession.createQuery("from Employee order by lastName", Employee.class);
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public Employee getEmployee(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Employee theEmployee = currentSession.get(Employee.class, theId);
		return theEmployee;
	}

	@Override
	public void deleteEmployee(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Employee theEmployee = currentSession.get(Employee.class, theId);
		currentSession.delete(theEmployee);
	}
	
	@Override
	public Employee getEmployeeBySecurityCode(String empSecurityCode) {
		Session currentSession = sessionFactory.getCurrentSession();
		EmployeeLoginManager employeeLoginManager = (EmployeeLoginManager) currentSession
				.createQuery("from EmployeeLoginManager where empSecurityCode=:empSecurityCode")
				.setParameter("empSecurityCode", empSecurityCode).getSingleResult();
		int theId=(int) currentSession
				.createQuery("select id from Employee where employeeLoginManager.id=:employeeLoginManagerId")
				.setParameter("employeeLoginManagerId", employeeLoginManager.getId()).getSingleResult();
		Employee theEmployee = currentSession.get(Employee.class, theId);
		return theEmployee;
	}

}
