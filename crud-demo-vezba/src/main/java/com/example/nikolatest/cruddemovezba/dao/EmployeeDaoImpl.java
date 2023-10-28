package com.example.nikolatest.cruddemovezba.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.nikolatest.cruddemovezba.entity.Employee;
import com.example.nikolatest.cruddemovezba.exceptionhandler.EmployeeException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		TypedQuery<Employee> queryAllEmployees = entityManager.createQuery("FROM Employee", Employee.class);
		List<Employee> listOfEmployees = queryAllEmployees.getResultList();
		if(listOfEmployees.isEmpty()) {
			throw new EmployeeException("Employee is empty " + listOfEmployees);
		}

		return listOfEmployees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee foundedEmployee = this.entityManager.find(Employee.class, id);
		return foundedEmployee;
	}

//mozes i ovako al je ruzan kod 
	@Override
	public Employee updateEmployee(int id, Employee employeToUpdate) {
		Employee employeeFounded = getEmployeeById(id);
		if (employeeFounded != null) {
			employeeFounded.setEmail(employeToUpdate.getEmail());
			employeeFounded.setFirstName(employeToUpdate.getFirstName());
			employeeFounded.setLastName(employeToUpdate.getLastName());
			this.entityManager.merge(employeeFounded);
		} else {
			throw new EmployeeException("Employee with this id is not founded cannot update it ! " + id);
		}

		return employeeFounded;
	}

	/*
	 * 
	 * @Override public Employee updateEmployee(int id, Employee employeToUpdate) {
	 * this.entityManager.merge(employeToUpdate);
	 * 
	 * return employeToUpdate; } ti mozes i ovako da pises posto merge metoda radi i
	 * save i update
	 * 
	 */

	// Mogao su u kontroleru da mu posaljes ceo body @ReustBody Employee
	// employeeToDelete
	// i onda bi ovde malo promenio implementaciju nebi ti ovako ruzno izgledalo

	/*
	 * U ovom kodu, očekujemo da employeeToDelete sadrži celokupne informacije o
	 * zaposlenom, uključujući i ID koji želite da obrišete. Nakon što dobijete
	 * employeeDb na osnovu prosleđenog ID-a, možete ga uporediti sa
	 * employeeToDelete kako biste potvrdili da postoje podaci o zaposlenom koji
	 * treba da se obriše. Ako postoji odgovarajući zaposleni, uklanjamo ga iz baze
	 * podataka.
	 * 
	 * Ovako bi ti iygledalo kad bi slao sa body
	 * @Override
		public void deleteEmployee(Employee employeeToDelete) {
       Employee employeeDb = getEmployeeById(employeeToDelete.getId());
    	if (employeeDb != null) {
        this.entityManager.remove(employeeDb);
        System.out.println("Employee removed: " + employeeDb.getId());
    	} else {
        throw new EmployeeException("The provided employee does not exist with ID: " + employeeToDelete.getId());
    	}
	}
	 */
	@Override
	public void deleteEmployee(int id, Employee employeeToDelete) {
		Employee employeeDb = getEmployeeById(id);
		if (employeeDb != null) {
			employeeToDelete = employeeDb;
			this.entityManager.remove(employeeToDelete);
			System.out.println("Employee removed " + employeeToDelete.getId());
		} else {
			throw new EmployeeException("The id is null or employee don't exist = " + id);
		}

	}

	@Override
	public Employee createEmployee(Employee employe) {
		Employee createOrUpdateEmployee = this.entityManager.merge(employe);

		return createOrUpdateEmployee;
	}

}
