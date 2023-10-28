package beanvalidation.pracitcebean.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beanvalidation.pracitcebean.model.Customer;
import jakarta.validation.Valid;

@Controller
public class CustomerController {
	
	
	@RequestMapping("/")
	public String showControllerFormPage(Model theModel) {
		theModel.addAttribute("customer", new Customer());
		return "/index.html";
		
	}
	
	/*
	 *    <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="erros"></span>
	 *    BindingResult je interfejs koji se koristi zajedno sa @Valid anotacijom
	 *    Ta anotacija @Valid se koristi kada zelsi da polja u klasi imaju neka predenisana pravila
	 *    i za svaki error koji se desi na UI on ti preko BindingResult interfejsa ces ih razresiti
	 *    
	 *    @Valid i @Validated se koriste za predefinisanje nekakvih pravila u objektima i poljima
	 *    
	 *    Cak i mozes da grupises pravila da napravis neku random klasu koja ce ti sluziti samo za nekakva pravila
	 *    i kada stavljas na polja da definises to polje tj grupises
	 *    npr 
	 *    
	 *    @Valid(min = 20; group = NamingConvetionClassGroup.class)  
	 *    private String name
	 *    
	 *    
	 *    @InitBinder sluzi za
	 */
	@PostMapping("/createCustomer")
	public String showConfirmationPage(@Valid @ModelAttribute("customer") Customer customer,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "/index.html";
		}else {
			return "/confirmationPage.html";	
		}
	}
	
	
	/* a ovo bi ti bilo ako nebi hteo preko message propertija da pravis exeptione
	 * mozes direktno custom ovako da mu napravis i da ti baci ovaj expetion
	 * 
	 * postoje razni tipovi expetion gresaka tj izuzetaka npr mi ovde koristimo typeMissmatch
	 * sto bi u prevodu znacilo bukvalno los tip postavljen podatak postavljen
	 * a ima i dosta drugih
	 * @PostMapping("/createCustomer")
	public String showConfirmationPage(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        for (ObjectError error : bindingResult.getAllErrors()) {
	            if (error.getObjectName().equals("customer") && error.getCode().equals("typeMismatch")) {
	                throw new IllegalArgumentException("Invalid number: " + error.getDefaultMessage());
	            }
	        }
	        return "/index.html";
	    } else {
	        return "/confirmationPage.html";    
	    }
	}
	
	 */
	/*
	 * @InitBinder je anotacija u Spring Framework-u koja omogućava podešavanje
	 * posebnih metoda u kontroleru koji se izvršavaju pre vezivanja podataka (data
	 * binding) ili validacije. Ova anotacija se koristi za prilagođavanje načina na
	 * koji se obrađuju podaci iz HTTP zahteva pre nego što se proslede kontroleru.
	 * 
	 * 
	 * WebDataBinder je ključna komponenta u Spring MVC koja služi za vezivanje
	 * podataka (data binding) iz HTTP zahteva u Java objekte koji se koriste u
	 * kontrolerima. Kada koristimo @InitBinder, možemo konfigurisati WebDataBinder
	 * kako bismo prilagodili proces vezivanja podataka pre nego što se podaci
	 * proslede kontroleru.
	 * 
	 */
	@InitBinder
	public void cutWhiteSpaces(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}
	
	
	
	
	
	
}
