package com.bss.csr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
 
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.bss"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
       
        //auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder());
    }
	
	/*@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("tony1").password("tony1").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
    }*/
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.formLogin().loginProcessingUrl("/login");
      	
    	http.authorizeRequests()
	       /* .antMatchers("/", "resource**").permitAll()
	        .antMatchers("/Customer*","/Edit-**","/Edit**","/LandingPage","/index","/list**").access("hasRole('CSR')")
	        .antMatchers("/Article**").access("hasRole('INV')")
	        .antMatchers("/Ticket**","/ticket**","/getFault","/SaveDept","/NewTicket","/SaveTransThread","/SearchTicket","/CreateNewAccount","/updateticket**","/sendmail").access("hasRole('TICKET')")
	        .antMatchers("/admin**","/adminedituser**","/listuser").access("hasRole('ADMIN')")*/
	        .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
	        .usernameParameter("username").passwordParameter("password").permitAll()      
	        /*.and().csrf()*/
	        .and().logout().permitAll()
	       .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())/*.accessDeniedPage("/Access_Denied")*/;
      
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}