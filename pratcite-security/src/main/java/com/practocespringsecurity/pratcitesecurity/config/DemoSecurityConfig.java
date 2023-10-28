package com.practocespringsecurity.pratcitesecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig{
	
	
	//Customize login secuirty form path by Spring Security Filte Chains
	/*
	 * Dakle iako ti je metoda koja je interfes SecurityFilterChain 
	 * Nju ce spring registrovati jer ona negde je vec implementirana u spring security frameworku
	 * Sto znaci bilo da je klasa ili interfejs pomocu anotacije @Configruation
	 * znaci da custom stvari konfigurises sto je bitno za tebe u tvojoj aplikaciji
	 * u ovom slucaju koristis taj security filter 
	 * SecurityFilterChain je interfejs u 
	 *  Spring Security frameworku koji predstavlja niz sigurnosnih filtera koji se primenjuju na HTTP zahteve. 
	 *  u prevodu Spring context REGISTURJE tvoje beanove sve VIDECE ovu metodu ispod 
	 *  i onda ce da VRATI TAJ TIP njen koja ona implementira posto je interfejs jebeni
	 *  i onda ce primeniti tu tvoju custom logiku
	 *  OVO JE PLUS SAMO BASIC 
	 */
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(config -> 
	            config
	                .requestMatchers("/").hasRole("EMPLOYEE") // Prvo definišete URL koji zahteva određenu ulogu
	                .requestMatchers("/leaders/**").hasRole("MANAGER")
	                .requestMatchers("/system/**").hasRole("ADMIN")
	                .anyRequest().authenticated() // Onda definišete da su ostali zahtevi dostupni samo autentifikovanim korisnicima
	        )
	        .exceptionHandling(config ->
	        config.accessDeniedPage("/access-denied") // ne mras da ides /login-template/access-denided 
	        // automacki ti sam pronadje gde se nalazi samo mu kazi koji html
	        
	        )        
	        .formLogin(form -> 
	            form
	                .loginPage("/login-plain") // a ovo ide na kontrolere
	                .loginProcessingUrl("/authenticateUser") // ovo ide na form akcije
	                .permitAll()
	        )
	        
	        
	        .logout(logout ->
	            logout
	                .permitAll()
	        );
	    return http.build();
	}


	//a ovo govno je built in useri
	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsManager() { UserDetails
	 * john = User.builder(). username("nikola"). password("{noop}test123")
	 * .roles("EMPLOYEE") .build();
	 * 
	 * UserDetails mary = User.builder(). username("mary").
	 * password("{noop}test123"). roles("EMPLOYEE","MANAGER"). build();
	 * 
	 * UserDetails susan = User.builder(). username("susan").
	 * password("{noop}test123"). roles("EMPLOYEE","MANAGER","ADMIN"). build();
	 * 
	 * 
	 * return new InMemoryUserDetailsManager(john,mary,susan); }
	 */

	
	// ovo govno od koda
	//ti je za definisanje spring security filter frameoworka rpvo da gleda jebeno u tvoju bazu
	//Objekat DataSource je standard koji ti veze bazu sa sa springom i sa bazom tvojom usranom aj tako da kazes
	// onda pomocu @Bean anotacije kada se podigne on ce pozvati ovu metodu i izvrsiti je 
	// a mozes logicno custom tabele da pravis za svoju implementaciju
	//samo treba da kazes springu u koju tabelu da gleda 
	// sve ime tabele i ostale kurce je opciono tu imas potpunu slobodu
	//jdbUserDetail.setUsersByUsernameQuery( ovo je taj kod koji to radi govori kurcevom springu koju tabelu da gleda
	// a ovo ? ti je parametar kada se user loguje za nejga da pogleda u bazi koja mu je usrana rola
	@Bean
	public UserDetailsManager userDetailManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbUserDetail = new JdbcUserDetailsManager(dataSource);
		jdbUserDetail.setUsersByUsernameQuery(
				"select user_id,pw,active from members where user_id =?");// ? it will be passed as login form from our app
		
		
		
		jdbUserDetail.setAuthoritiesByUsernameQuery(
				"select user_id,roles from role where user_id=?");
		
		return jdbUserDetail;
	}
	
}
