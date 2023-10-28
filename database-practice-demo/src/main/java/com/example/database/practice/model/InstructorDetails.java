package com.example.database.practice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "course_details")
	private String courseDetails;
	@Column(name = "hobby")
	private String hobby;
	/*
	 * mappedBy tells hibernate
	 * Look at InstructorDetails property in Instructor
	 * use information from Instructor class @JoinColumn
	 * // (mappedBy = "instructorDetails" refer to instructorDetails in Instructor class
	 */
	@OneToOne(mappedBy = "instructorDetails", cascade = {CascadeType.DETACH, //sa ovom listom si samo rekao sve da radi cascade osim remove
													     CascadeType.MERGE, // samo stavi u service klasi instructor da je null 
													     CascadeType.PERSIST, // da ako obgise instruorDetails da neobrise instructora
													     CascadeType.REFRESH})
	private Instructor instructor;
	
	public InstructorDetails() {
		
	}
	
	public InstructorDetails(String courseDetails,String hobby) {
		this.courseDetails = courseDetails;
		this.hobby = hobby;
	}


	public String getCourseDetails() {
		return courseDetails;
	}


	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}


	public String getHobby() {
		return hobby;
	}
	
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Instructor getInstructor() {
		return instructor;
	}
	
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorDetails [id=" + id + ", hobby=" + hobby + "]";
	}




	

	
	
	
}
