package com.springproject.springfinaldemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source


	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
      auth.jdbcAuthentication()
			  .dataSource(securityDataSource)
			  .usersByUsernameQuery("select username, password, enabled from users where username=?")
			  .authoritiesByUsernameQuery("select username, role from users where username=?");

       /*
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
				.withUser(users.username("john").password("test123").roles("customer"))
				.withUser(users.username("mary").password("test123").roles("MANAGER"));
				*/
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/colleges/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("STUDENT")
				.antMatchers("/register").permitAll()
				.antMatchers("/newuser").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");


	}
	
}






