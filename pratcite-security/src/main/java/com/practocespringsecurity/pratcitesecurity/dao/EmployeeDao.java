package com.practocespringsecurity.pratcitesecurity.dao;

import java.util.List;

import com.practocespringsecurity.pratcitesecurity.model.Employee;

public interface EmployeeDao {
	
	List<Employee> findAllEmployees();
	
	Employee findById(int idOfEmployee);
	
	void save(Employee employee);
	
	void deleteEmployeeById(int theId);

}
