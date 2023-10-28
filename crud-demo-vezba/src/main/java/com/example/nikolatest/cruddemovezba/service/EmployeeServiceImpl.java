package com.example.nikolatest.cruddemovezba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nikolatest.cruddemovezba.dao.EmployeeDao;
import com.example.nikolatest.cruddemovezba.dao.EmployeeRepository;
import com.example.nikolatest.cruddemovezba.entity.Employee;
import com.example.nikolatest.cruddemovezba.exceptionhandler.EmployeeException;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	//private EmployeeDao employeeDao;
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}


	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}


	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		Optional<Employee> resultOfEmployee = this.employeeRepository.findById(id);
		Employee employee = null;
		if(resultOfEmployee.isPresent()) {
			employee = resultOfEmployee.get();
			return employee;
		}else {
			throw new EmployeeException("NOT FOUNDED " + employee.toString());
		}
	}


	@Override
	@Transactional
	public Employee updateEmployee(int id, Employee employeToUpdate) {
		return this.employeeRepository.save(employeToUpdate);
	}


	@Override
	@Transactional
	public void deleteEmployee(int id, Employee employeeToDelete) {
		this.employeeRepository.delete(employeeToDelete);
		
	}


	@Override
	@Transactional
	public Employee createEmployee(Employee employe) {
		return this.employeeRepository.save(employe);
	}

}
