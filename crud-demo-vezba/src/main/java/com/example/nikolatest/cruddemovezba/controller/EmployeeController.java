package com.example.nikolatest.cruddemovezba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nikolatest.cruddemovezba.entity.Employee;
import com.example.nikolatest.cruddemovezba.exceptionhandler.EmployeeException;
import com.example.nikolatest.cruddemovezba.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	@RequestMapping("/employees")
	public List<Employee> findAllEmployes(){
		return this.employeeService.findAll();
	}
	
	@RequestMapping("/employeesById/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		Employee employeeById = this.employeeService.getEmployeeById(id);
		if(employeeById !=null) {
			return employeeById;
		}else {
			throw new EmployeeException("Employee not found" + id);
		}
		
		
	}
	
	@PostMapping("/create/employee")
	public Employee createEmployee(@RequestBody Employee newEmployee) {
		return this.employeeService.createEmployee(newEmployee);
	}
	
	
	@PostMapping("/update/empoyeeById/{employeeId}")
	public Employee updateEmployeeByid(@PathVariable int employeeId,@RequestBody Employee empoyee) {
		Employee updatedEmployebyId = this.employeeService.updateEmployee(employeeId, empoyee);
		if(updatedEmployebyId == null) {
			throw new EmployeeException("Employee with this id not found " + updatedEmployebyId);
		}
		return updatedEmployebyId;
		
	}
	
	
	@DeleteMapping("/delete/employeeById/{id}")
	public Employee deleteEmployee(@PathVariable int id,@RequestBody Employee employee) {
		Employee deletedEmployee = this.employeeService.getEmployeeById(id);
		if(deletedEmployee == null) {
			throw new EmployeeException("There is no employee with this id cannot deleted it " + id);
		}else {
			this.employeeService.deleteEmployee(id, employee);
		}
		System.out.println("This one is deleted " + "\n" + employee.toString());
		
		return employee;
	}
	
	
	
}


