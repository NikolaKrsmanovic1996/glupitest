package beanvalidation.pracitcebean.model;

import beanvalidation.pracitcebean.validation.CustomerNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Customer {
	
	@NotNull (message = "firstName is required")
	@Size(min = 3, max = 15)
	private String firstName;
	@NotNull(message = "lastName is required")
	@Size(min = 3, max = 15)
	private String lastName;
	@Email(message = "Please enter a valid email address !")
	private String email;
	
	
	@NotNull(message = "This filed is required")
	@Min(value = 1,message = "It must be minimum 1")
	@Max(value = 100, message = "It must be maximum 100")
	private Integer freePasses;
	
	@Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 chars digits")
	private String postalCode;
	@CustomerNaming(value = "kurac" , message = "Mora da zapocinje sa kurac")
	private String codeRegistration;
	
	
	
	public String getCodeRegistration() {
		return codeRegistration;
	}
	public void setCodeRegistration(String codeRegistration) {
		this.codeRegistration = codeRegistration;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getFreePasses() {
		return freePasses;
	}
	public void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	
}
