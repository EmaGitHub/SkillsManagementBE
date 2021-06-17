package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// white list
		.antMatchers("/", "/index", "/css", "/js/*").permitAll()
		// permission required
		//.antMatchers("/api/*").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.GET, "/api/*").hasAnyAuthority("READ")
		.antMatchers(HttpMethod.DELETE, "/api/dismiss/*").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST).hasAnyAuthority("WRITE")
		.anyRequest().authenticated().and().httpBasic()
		// disabling csrf here, you should enable it before using in production (POST)
        .and().csrf().disable();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		//Recupero da DB
		UserDetails user = User.builder()
				.username("emanuele")
				.password(this.passwordEncoder.encode("password"))
				.roles("ADMIN", "USER")	//ROLE_ADMIN, ROLE_USER
				.authorities("WRITE", "READ")
				.build();
		
		UserDetails user2 = User.builder()
				.username("giuseppe")
				.password(this.passwordEncoder.encode("password"))
				.roles("USER")	//ROLE_USER
				.authorities("READ")
				.build();
		
		return new InMemoryUserDetailsManager(user, user2);
	}
	
	
}
