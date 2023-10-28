package com.nikolakurs.cruddemo.dao;

import java.util.List;

import com.nikolakurs.cruddemo.entity.Student;

public interface StudentDao {
	
	void saveStudent(Student student);
	
	Student getStudentFromDataBase(int id);
	
	Student deleteStudentFromDataBase(int id,Student student);
	
	Student updateStudentFromDataBase(int id,Student student);
	
	List<Student> getList();
	
	List<Student> findByLastName(String lastName);
	

}
