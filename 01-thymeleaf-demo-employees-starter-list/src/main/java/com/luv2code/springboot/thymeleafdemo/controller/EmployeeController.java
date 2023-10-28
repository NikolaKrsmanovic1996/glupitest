package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeService;

	@Autowired
	public EmployeeController(EmployeeService employeService) {
		this.employeService = employeService;
	}


	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> employee = this.employeService.findAll();
		theModel.addAttribute("employees",employee);
	//	Collections.sort(employee, (employee1, employee2) -> employee1.getLastName().compareTo(employee2.getLastName()));
		//reurn html by path
		return "employees/list-employees";
	}
	
	
	/*
	 * Za prikazivanje formi
	 * moras da vezes objekat koji ce biti na formi
	 *  Model služi za prenošenje podataka sa kontrolera na pogled.
	 *  jer on ovde prvo kad ucita odradi get tvoja polja sa entitty
	 */
	@GetMapping("/showFormAdd")
	public String showFormForAdd(Model theModel) {
		//create form attribute to bind the form data
		Employee employeeForForm = new Employee();
		//i onda ceo ovaj objekat vezi tamo na html
		//imas ga search samo employeeForForm
		theModel.addAttribute("employeeForForm", employeeForForm);
		
		return "employees/showFormAdd";
	}
	
	
	/*
	 * a ovde odradi save tog objekta
	 * @ModelAttribute se koristi za vezivanje podataka iz HTML formi sa modelnim objektima
	 *  th:action="@{employees/save}" ovo ti je akcija i putanja na formi opisuje sta se desava
	 *  posto se u formi trazi POST ti napravi POST MAPPING i uzmes iz forme attribute th:object=${employeeForForm}
	 *  i spustis u bazu to je sve
	 */
	@PostMapping("/employees/save")
	public String saveEmployee(@ModelAttribute("employeeForForm") Employee employee) {
		this.employeService.save(employee);
		return "redirect:/employees/list";
	
	}
	
	//UPDATE deo
	@GetMapping("/updateFormEmployee")
	public String updateFormEmployee(@RequestParam("employeeId") int employeeId, Model theModel) {
	    // ...
	    Employee employeeDb = this.employeService.findById(employeeId);
	    theModel.addAttribute("employeeForForm", employeeDb);
	    return "employees/showFormAdd";
	}
	
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
		Employee deleted = this.employeService.findById(employeeId);
		this.employeService.deleteById(employeeId);
		System.out.println("EMPLOYEE " + deleted.toString() + " has been deleted" );
		return "redirect:/employees/list";
		
	}
	
	
	
	/*
	 * mozes celu novu formu da napravis sa npr. updateFormEmployee.html
	 * sve iste atribute da i objekat da vezes
	 * i da opalis novu metodu kao ovu ispod zakomentarisanu
	 * i dao dradis update
	 * a mozes samo novi get mapping update posto je vec taj objekat vezan preko linka update dugmenta proseldjume mu  se taj ID
	 * i onda samo stavis hiiden field i opicis update
	 * sama forma vec po sebi poziva get i set
	 */
	
//	@PostMapping("/employees/update")
//	public String updateEmployee(@ModelAttribute("employeeDb") Employee employeeDb) {
//		//Find by id
//		System.out.println("OBJECT " + employeeDb.toString());
//		this.employeService.save(employeeDb);
//		
//		System.out.println("has been updated to database" + employeeDb);
//		return "redirect:/employees/list";
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	// load employee data
//
//	private List<Employee> theEmployees;
//
//	@PostConstruct
//	private void loadData() {
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}

	// add mapping for "/list"
}
