package beanvalidation.pracitcebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;



@Constraint(validatedBy = CustomerNamingConvetio.class) //klasa za koje ca da vazi pravilo validacije i anotacije
@Target({ElementType.METHOD , ElementType.FIELD}) // a oov znaci gde sve mozes da stavis i da koristis ovu anotaciju na polja sva i metode u ovom primeru
@Retention(RetentionPolicy.RUNTIME) // kako ce se ova anotacija koristiti i skladistiti u prevodu
// RETAIN THIS ANOTATION IN BYTE CODE AND USE IT IN RUNETIME BY THE JVM !!!
public @interface CustomerNaming {
	
	
	 String  value() default "NIK" ; // ako ne proslede neku vrednost defaultna je NIK
	 
	 String message() default "Must start with NIK"; // poruka koju ce mu izbaciti
	 
	 Class<?>[] groups() default {}; // necemo nista defaulnta grupne constrains prazna kolekcija
	 
	 Class<? extends Payload>[] payload() default {};//Payload type that can be attached to a given constraint declaration daje ti dodatne infromacije kada se error pojavi

}
