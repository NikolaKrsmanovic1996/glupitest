package com.practocespringsecurity.pratcitesecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.practocespringsecurity.pratcitesecurity.dao.EmployeeDao;
import com.practocespringsecurity.pratcitesecurity.model.Employee;
import com.practocespringsecurity.pratcitesecurity.repostiory.EmployeeRepository;

@Service
public class EmployeeServiceImpl  implements EmployeeDao{
	
	private EmployeeRepository employeeRepository;

	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	

	@Override
	public List<Employee> findAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee findById(int idOfEmployee) {
		Optional<Employee> foundedEmployee = this.employeeRepository.findById(idOfEmployee);
		if(foundedEmployee.isPresent()) {
			return foundedEmployee.get();
		}else {
			throw new NullPointerException();
		}
	}

	@Override
	public void save(Employee employee) {
		this.employeeRepository.save(employee);
		System.out.println("Employee saved to database !" + employee.toString());
		
	}

	@Override
	public void deleteEmployeeById(int theId) {
		try {
			this.employeeRepository.deleteById(theId);
		} catch (EmptyResultDataAccessException  e) {
			System.out.println("Employee not found by this id " + theId);
		}
		
	}


}
