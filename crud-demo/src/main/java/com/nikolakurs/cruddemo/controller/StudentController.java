package com.nikolakurs.cruddemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolakurs.cruddemo.dao.StudentDaoImpl;
import com.nikolakurs.cruddemo.entity.Student;
import com.nikolakurs.cruddemo.exeptionhandler.StudentErrorResponse;
import com.nikolakurs.cruddemo.exeptionhandler.StudentException;

@RestController
public class StudentController{
	
	private StudentDaoImpl studentDao;
	

	@Autowired
	public StudentController(StudentDaoImpl studentDao) {
		this.studentDao = studentDao;
	}
	
	
	

	@RequestMapping("/studentById")
	private List<Student> getStudents() {
		List<Student> listOfStudents = studentDao.getList();
		return listOfStudents;
	}
	
	@RequestMapping("/byLastname/{lastName}")
	private List<Student> byLastName(@PathVariable String lastName) {
		List<Student> lastNames = studentDao.findByLastName(lastName);
		return lastNames;
	}
	
	@RequestMapping("/testId/{id}")
	private List<Student> updateStudent(@PathVariable int id) {
	    Student student = studentDao.getStudentFromDataBase(id);  
	    if(student == null) {
	    	throw new StudentException(" Student is not founded = " +id);
	    }
	    List<Student> listOfStudents = new ArrayList<>();
	    listOfStudents.add(student);
	    return listOfStudents;
	}

	
	
	
}
