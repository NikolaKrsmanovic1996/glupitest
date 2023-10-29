package com.example.database.practice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String nameOfInstructor;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String emai;
	@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_details_id")
	private InstructorDetails instructorDetails;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "instructor" ,cascade = {CascadeType.MERGE,
																		  CascadeType.PERSIST,
																		  CascadeType.REFRESH,
																		  CascadeType.DETACH})
	private List<Course> listOfCourses;


	//Convinience method for bi directional relationship
	public void add(Course tempCourse) {
		if(listOfCourses == null) {
			this.listOfCourses = new ArrayList<>();
		}
		listOfCourses.add(tempCourse);
		tempCourse.setInstructor(this);
	}
	
	
	
	public Instructor() {
		
	}
	

	public Instructor(String nameOfInstructor, String lastName, String emai) {
		this.nameOfInstructor = nameOfInstructor;
		this.lastName = lastName;
		this.emai = emai;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameOfInstructor() {
		return nameOfInstructor;
	}
	public void setNameOfInstructor(String nameOfInstructor) {
		this.nameOfInstructor = nameOfInstructor;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmai() {
		return emai;
	}
	public void setEmai(String emai) {
		this.emai = emai;
	}

	public InstructorDetails getInstructorDetails() {
		return instructorDetails;
	}
	
	public void setInstructorDetails(InstructorDetails instructorDetails) {
		this.instructorDetails = instructorDetails;
	}


	public List<Course> getListOfCourses() {
		if(this.listOfCourses == null) {
			return new ArrayList<Course>();
		}
		
		return listOfCourses;
	}



	public void setListOfCourses(List<Course> listOfCourses) {
		if(this.listOfCourses == null) {
			this.listOfCourses = new ArrayList<>();
		}else {
			this.listOfCourses = listOfCourses;
		}
	}



	@Override
	public String toString() {
		return "Instructor [id=" + id + ", nameOfInstructor=" + nameOfInstructor + ", lastName=" + lastName + ", emai="
				+ emai + ", instructorDetails=" + instructorDetails + ", listOfCourses=" + listOfCourses + "]";
	}








	

}
