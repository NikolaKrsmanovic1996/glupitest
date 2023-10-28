package com.example.nikolatest.cruddemovezba.service;

import java.util.List;

import com.example.nikolatest.cruddemovezba.entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	Employee getEmployeeById(int id);
	
	Employee updateEmployee(int id, Employee employeToUpdate);
	 
	void deleteEmployee(int id,Employee employeeToDelete);
 	
	Employee createEmployee(Employee employe);

}
