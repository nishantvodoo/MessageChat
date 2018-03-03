package com.nishant.third.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nishant.third.repository.UsersRepository;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// TODO Auto-generated method stub
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("**/home/**")
			.authenticated()
			.anyRequest()
			.permitAll()
			.and()
			.formLogin().defaultSuccessUrl("/home/index", true)
			.permitAll();
	}
	
	
	
	//No Password Encoding applied. Saving raw password into the database
	private PasswordEncoder getPasswordEncoder()
	{
		return new PasswordEncoder()
		{
			@Override
			public String encode(CharSequence charSequence)
			{
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s)
			{
				// TODO Auto-generated method stub
				return true;
			}
		};
	}
}
