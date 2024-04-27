package com.magic.securityconfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder en = new BCryptPasswordEncoder();
		
		auth.inMemoryAuthentication().withUser("user1").password(en.encode("admin")).roles("admin", "timeinspector");
		auth.inMemoryAuthentication().withUser("user2").password(en.encode("inspector")).roles("timeinspector");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/employee/list", "/employee/{empid}", "/employee/{department}/list",
				"/employee/currentdate/reports", "/employee/anydate/reports", "/employee/datebetween/reports",
				"/employee/{empid}/currentdate/reports", "/employee/{empid}/anydate/reports", "/employee/{empid}/datebetween/reports")
		.hasRole("admin").antMatchers(HttpMethod.POST, "/employee/add").hasRole("admin")
		.antMatchers(HttpMethod.DELETE, "/employee/{empid}/delete").hasRole("admin")
		.antMatchers(HttpMethod.PUT, "/employee/{empid}/update", "/employee/intime/{tid}/update", "/employee/outtime/{tid}/update")
		.hasRole("admin").antMatchers(HttpMethod.POST, "/employee/intime/record").hasRole("timeinspector")
		.antMatchers(HttpMethod.PUT, "/employee/outtime/{empid}/record").hasRole("timeinspector")
		.anyRequest().fullyAuthenticated().and().httpBasic();
	}
	
	

}
