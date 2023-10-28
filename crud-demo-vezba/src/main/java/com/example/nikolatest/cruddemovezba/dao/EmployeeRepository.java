package com.example.nikolatest.cruddemovezba.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nikolatest.cruddemovezba.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

	
	/*
	 * Mozes i sa DAOIMPL klasama da se radi preko entityManagera
	 * ali bolje je i lakse je za pisanje sa EXTEDOVANJEM interfejsa JpaRepository
	 * Mnogo manje koda i lakse se radi
	 * a drugo prosledis mu kao 1 parametar Employee tj TIP entita
	 * pa onda Integer a to predstavlja tvoj primery key
	 * 
	 */
}
