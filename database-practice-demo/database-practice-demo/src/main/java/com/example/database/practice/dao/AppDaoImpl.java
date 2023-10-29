package com.example.database.practice.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.database.practice.model.Course;
import com.example.database.practice.model.Instructor;
import com.example.database.practice.model.InstructorDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class AppDaoImpl  implements AppDao{
	
	
	public EntityManager entityManager;
	
	
	public AppDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}




	@Transactional
	@Override
	public void saveInstructor(Instructor instructor) {
		this.entityManager.persist(instructor);
		
	}

	
	@Override
	public Instructor findById(int id) {
		Instructor founded = this.entityManager.find(Instructor.class, id);
		return founded;
	}

/* 
 * Primer 2 ako ti je lazy da ne moras da kuckas jebeni query tj moras za course posto je lista ma da i ne mora 
 * al jedan od nacina 
 * u sustini ti rucno moras u kodu da pozoves kad ti treba posto je lazy sto je ok
 * A mozes i sa Join Fetch koji ti radi bolje samo drkanje je da se namesti upit lepo da radi sto je sex u boji
 * i jebeni @Transactional da budu u jednoj istoj transakciji kada se radi sa lazy loadinzima mora tako jbg
 * 
 * @Transactional
	@Override
	public Instructor findById(int id) {
		Instructor founded = this.entityManager.find(Instructor.class, id);
		List<Course> courses = findCoursesForTheInstructor(id);		
		founded.setListOfCourses(courses);
		return founded;
	}
 */


	@Override
	public InstructorDetails findDetailsById(int id) {
		InstructorDetails detailsFounded = this.entityManager.find(InstructorDetails.class, id);
		if(detailsFounded !=null) {
			System.out.println(detailsFounded.getInstructor().toString());
			return detailsFounded;
		}else {
			throw new NullPointerException("not founded!");
		}
	}


	@Transactional
	@Override
	public List<Instructor> deleteListOfInstructors(List<Integer> idsOfInstrucotrs) {
		List<Instructor> instructorsToDelete = new ArrayList<>();
		
		for (Integer instructor : idsOfInstrucotrs) {
			Instructor founded = this.entityManager.find(Instructor.class, instructor);
			if(founded !=null) {
				this.entityManager.remove(founded);
				instructorsToDelete.add(founded);
			}else {
				throw new NullPointerException("I didn't find what i wanted to delete !");
			}
		}
		return instructorsToDelete;
		
//	    List<Instructor> instructorsToDelete = entityManager.createQuery("SELECT i FROM Instructor i WHERE i.id IN :ids", Instructor.class)
//	        .setParameter("ids", idsOfInstrucotrs)
//	        .getResultList();
//
//	    instructorsToDelete.forEach(instructor -> entityManager.remove(instructor));
//
//	    return instructorsToDelete;
	}




	//Ovo kada radimo sa lazy moramo rucno da ga pozivamo tj pozivamo ga po potrebi u kodu 
	@Override
	public List<Course> findCoursesForTheInstructor(int theId) {
		TypedQuery<Course> queryForCourses = this.entityManager.createQuery("Select c from Course c WHERE instructor.id= :id",Course.class)
				.setParameter("id", theId);
		
		
	 List<Course> resultList = queryForCourses.getResultList();
	 
	 
	 return resultList;
		
	}




	// i ako ti je sve tamo setovani po defaultu da je lazy sa ovim imas kontrolu vecu nad podacima koje ti
	// dolaze jer koristis join fetch slicno radi kao Eager al ovde imas vecu kontrolu i bolju perfomansu
	// ovde si siguran da ti dolaze tacno ti podaci koje ti zes 
	//a sa eager loading nemas kontrolu i to ti je od ORM alata iz hiberneta koje dolazi
	//sve u svemu ovo ti je bolji fleksibilniji pristup nego eager obican
	@Override
	public Instructor findInstructorByIdJoinFetch(int id) {
	  TypedQuery<Instructor> queryInstuctor = this.entityManager.createQuery("Select i FROM Instructor i "
			  																+"JOIN FETCH i.listOfCourses "
			  																+"WHERE i.id  = :data",Instructor.class)
			  .setParameter("data",id);
			  													
	  
	  Instructor instructorResult = queryInstuctor.getSingleResult();
	  if(instructorResult == null) {
		  System.out.println("NE POSTOJI !!!");
	  }
	  
	  return instructorResult;
	}



	@Transactional
	@Override
	public void updateInstructor(Instructor instructor) {
		this.entityManager.merge(instructor);
		
	}




	@Transactional
	@Override
	public void updateCourse(Course course) {
		this.entityManager.merge(course);
		
	}




	@Override
	public Course findCourseById(int id) {
		Course course = this.entityManager.find(Course.class, id);
		if(course !=null) {
			return course;
		}else {
			throw new NullPointerException("Not founded!");
		}
	}




	@Transactional
	@Override
	public void deleteInstructorByid(int id) {
		Instructor founded = findById(id);
		List<Course> foundedCourse = founded.getListOfCourses();
		
		if(founded !=null) {
			for (Course course : foundedCourse) {
				course.setInstructor(null);
			}
			this.entityManager.remove(founded);
			System.out.println("Instructor removed ! " + founded.toString());
		}
		
	}




	


  
	    
//	    for (Integer id : idsOfInstrucotrs) {
//	        Instructor instructorToDelete = entityManager.find(Instructor.class, id);
//	        
//	        if (instructorToDelete != null) {
//	            entityManager.remove(instructorToDelete);
//	            deletedInstructors.add(instructorToDelete);
//	        }
//	    }
//	   
//	    for (Instructor instructor : deletedInstructors) {
//			System.out.println("Therse are deleted in collection of" + instructor.toString());
//		}
	    
//	    return deletedInstructors;
//	}


}
