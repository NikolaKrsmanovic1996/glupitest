package com.example.database.practice.dao;

import java.util.List;

import com.example.database.practice.model.Course;
import com.example.database.practice.model.Instructor;
import com.example.database.practice.model.InstructorDetails;

public interface AppDao {
	
	void saveInstructor(Instructor instructor);
	
	Instructor findById(int id);
	
	List<Instructor> deleteListOfInstructors(List<Integer> idsOfInstrucotrs);

	InstructorDetails findDetailsById(int id);
	
	List<Course> findCoursesForTheInstructor(int theId);
	
	Instructor findInstructorByIdJoinFetch(int id);

}
