package com.nikolakurs.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikolakurs.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao{

	private EntityManager entityManager;

	@Autowired
	public StudentDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {
	
		this.entityManager.persist(student);
		System.out.println("Student successfuly saved into database !" + student.toString());	
	}

	@Override
	public Student getStudentFromDataBase(int id) {
		return this.entityManager.find(Student.class,id);
	}

	@Override
	@Transactional
	public Student deleteStudentFromDataBase(int id, Student student) {
        Student student1 = getStudentFromDataBase(id);
		this.entityManager.remove(student1);
		return student;
	}

	@Override
	@Transactional
	public Student updateStudentFromDataBase(int id, Student student) {
		this.entityManager.merge(student);
		return student;
	}

	@Override
	public List<Student> getList() {
		TypedQuery<Student> query = this.entityManager.createQuery("FROM Student",Student.class);
		
		List<Student> listOfAllStudent = query.getResultList();
		
		return listOfAllStudent;
		
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		TypedQuery<Student> queryByLastName = this.entityManager.createQuery("From Student WHERE lastName=:theData",Student.class);
		
		queryByLastName.setParameter("theData",lastName);
		
		
		List<Student> listByLastname = queryByLastName.getResultList();
		
		
		return listByLastname;
		
		
	}
	
	
	
	

}
