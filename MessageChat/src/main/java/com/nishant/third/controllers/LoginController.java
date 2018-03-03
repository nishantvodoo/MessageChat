package com.nishant.third.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nishant.third.models.User;
import com.nishant.third.models.Users;
import com.nishant.third.service.CustomUserDetailsService;
import com.nishant.third.service.RegistrationService;

@RequestMapping("/")
@RestController
public class LoginController
{
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	RegistrationService registrationService;

	@GetMapping("/test")
	public String test()
	{
		return "Test Successful";
	}

	//@RequestMapping(value = "/register", method = RequestMethod.POST)
	@RequestMapping(value = "/register")
	public String registerUser(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		User user = new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setFirstName(firstname);
//		user.setLastName(lastname);
//		user.setEmail(email);
		
		//Dummy Data
		user.setUsername("user16");
		user.setPassword("P@ssword1");
		user.setFirstName("Firstname");
		user.setLastName("Lastname");
		user.setEmail("user16@lsu.edu");
		
		return registrationService.RegisterUser(user);
	}
}
