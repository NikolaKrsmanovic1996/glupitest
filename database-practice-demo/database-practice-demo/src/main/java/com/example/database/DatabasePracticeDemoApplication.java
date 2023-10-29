package com.example.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.database.practice.dao.AppDao;
import com.example.database.practice.model.Course;
import com.example.database.practice.model.Instructor;
import com.example.database.practice.model.InstructorDetails;

@SpringBootApplication
public class DatabasePracticeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabasePracticeDemoApplication.class, args);
		
	
	}
	
	@Bean
	 CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			
//			List<Integer> idsToDelete = Arrays.asList(3,4,5);
//			appDao.deleteListOfInstructors(idsToDelete);
//			
//			int theId = 1;
//			Instructor founded = appDao.findById(theId);
////			System.out.println("Eo ga ! " + founded.toString());
//			
//			List<Course> courses = appDao.findCoursesForTheInstructor(theId);
//			
//			founded.setListOfCourses(courses);
//			
//			System.out.println(founded.toString());
			
//			Instructor founded = appDao.findInstructorByIdJoinFetch(theId);
//			System.out.println(founded.toString());
			deletreCourse(appDao);
//			updateInstructor(appDao);
//			updateCourse(appDao);
//			createInstructor(appDao);
	//		getDetailsInstById(appDao);
		};
		
	}
	
	
	private void deletreCourse(AppDao appDao) {
		appDao.deleteInstructorByid(2);
	}
	
	private void updateCourse(AppDao appDao) {
		
		int theid = 1;
		
		Course founded = appDao.findCourseById(theid);
		founded.setTitle("KurcevTitle");
		appDao.updateCourse(founded);
		
		
		
	}
	
	private void updateInstructor(AppDao appDao) {
		int theId = 1;
		Instructor foundedInstructor = appDao.findById(theId);
	
		foundedInstructor.setLastName("asdasdsd");

		appDao.updateInstructor(foundedInstructor);
		
		
		System.out.println("Instruvtor succcessfulyy updated !" + foundedInstructor.toString());
		
		
		
	}

	private void createInstructor(AppDao appDao) {
		Instructor instructor1 = new Instructor("Nikola", "Stagarevic", "nikola@Stagarevic.com");
		InstructorDetails inDetails = new InstructorDetails("JAVA_COURSE", "Pika Wow");
		inDetails.setInstructor(instructor1);
		instructor1.setInstructorDetails(inDetails);
		
		Course course = new Course();
		course.setTitle("Glupi Title");
		
		
		instructor1.add(course);
	
		//POSTO TI JE CASCADE_TYPE ALL SACUVACE I NA DRUGE TABELE KOME SU POVEZANI ENTITETI 
		appDao.saveInstructor(instructor1);;
		System.out.println("SAVED !" + instructor1.toString());
	}
	
	private void getDetailsInstById(AppDao dao) {
		dao.findDetailsById(1);
	}

}
