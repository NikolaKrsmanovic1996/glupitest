package com.nikolakurs.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nikolakurs.cruddemo.dao.StudentDaoImpl;
import com.nikolakurs.cruddemo.entity.Student;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
		
		
	}

	
	@Bean
	public CommandLineRunner commandLine(StudentDaoImpl studentDaoImpl) {
		return runner -> {
			/*
			 * Student student = new Student("Dzon", "Nezbit",
			 * "jebemoogijaucmar@gmail.com");
			 * 
			 * studentDaoImpl.saveStudent(student);
			 */
			
//			System.out.println("Student from database = " + studentDaoImpl.getStudentFromDataBase(student.getId()));
			
			//List<Student> listOfAllStudentsFromDB = studentDaoImpl.getList();
			
			/*
			 * for (Student students : listOfAllStudentsFromDB) {
			 * System.out.println("From database \n " + students); }
			 */
			Student studentFromDataBase = studentDaoImpl.getStudentFromDataBase(10);
//			System.out.println(studentFromDataBase.toString());
//			Student updated = update(studentFromDataBase);
//			studentDaoImpl.updateStudentFromDataBase(studentFromDataBase.getId(), updated);
			
			
			studentDaoImpl.deleteStudentFromDataBase(studentFromDataBase.getId(), studentFromDataBase);
			
//			List<Student> byLastName = studentDaoImpl.findByLastName("xxx");
//			System.out.println(byLastName);
		};	
	}
	
	private Student update(Student student) {
		student.setEmail("ogiPusiMopsuKurac");
		student.setFirstName("OgiPusiMopsuIOcuKurac");
		student.setLastName("LastNameOgi");
		return student;
	}
}
