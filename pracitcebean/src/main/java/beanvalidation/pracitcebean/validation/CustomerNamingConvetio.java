package beanvalidation.pracitcebean.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerNamingConvetio implements ConstraintValidator<CustomerNaming, String> {

   
	private String nameValueContext;
	
	@Override
	public void initialize(CustomerNaming constraintAnnotation) {
		//a sa ovim si rekao da ce ti oveerajduje defaultnu stvar
		this.nameValueContext = constraintAnnotation.value();
		// prilikom kada koristis ovo govno od klase negde da ti da predlog
		// plus rucno si ga implementario alt s ctrl
	}

	@Override
	public boolean isValid(String theCodeThatIsComingFromHtl, ConstraintValidatorContext context) {
    //context interfejs mozes da postavis error poruke sa njim
    // moras vratiti true ili fales ali sta god te zanima od validacija u ovoj metodi to mzoes odraditi
		if(theCodeThatIsComingFromHtl == null) {
			theCodeThatIsComingFromHtl = nameValueContext;
		}
		boolean trueValueStart = theCodeThatIsComingFromHtl.startsWith(nameValueContext);
		return trueValueStart;
	}

}
