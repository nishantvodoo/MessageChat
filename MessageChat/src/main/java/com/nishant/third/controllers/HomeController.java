package com.nishant.third.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nishant.third.models.Message;
import com.nishant.third.models.Users;
import com.nishant.third.service.CustomUserDetailsService;
import com.nishant.third.service.MessageService;

@RequestMapping("/home")
@RestController
public class HomeController
{
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	MessageService messageService;

	@PreAuthorize("hasAnyRole('Admin','User')")
	@GetMapping("/list")
	public String listUsers()
	{
		Users users = new Users();
		try
		{
			users = customUserDetailsService.userList();
		} catch (NotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		gson.toJson(users);
		String jsonInString = gson.toJson(users);
		return jsonInString;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	// @RequestMapping(value = "/send", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('Admin','User')")
	@RequestMapping(value = "/send")
	public String sendMessage(HttpServletRequest request, HttpServletResponse response)
	{
		String message = request.getParameter("message_content");
		String senderID = request.getParameter("sender_id");

		// Dummy Data to Send a Message
		Message newMessage = new Message();
		newMessage.setMessageContent("Test Message");
		newMessage.setReceiverID(2);

		return messageService.SendMessage(newMessage);
	}

	@PreAuthorize("hasAnyRole('Admin','User')")
	@RequestMapping(value = "/get")
	public String getMessage(HttpServletRequest request, HttpServletResponse response)
	{
		List<Message> messageList = new ArrayList<>();

		try
		{
			messageList = messageService.GetMessages();
		} catch (NotFoundException e)
		{
			e.printStackTrace();
		}

		Gson gson = new Gson();
		gson.toJson(messageList);
		String jsonInString = gson.toJson(messageList);
		return jsonInString;
	}

	// Search User
	@PreAuthorize("hasAnyRole('Admin','User')")
	@RequestMapping(value = "/searchUsers")
	public String searchUsers(HttpServletRequest request, HttpServletResponse response)
	{
		String firstname = request.getParameter("first_name");
		String lastname = request.getParameter("last_name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		// Dummy value
		firstname = "shant";
		lastname = "";
		username = "";
		email = "fmoham";

		Users users = new Users();
		try
		{
			users = customUserDetailsService.searchUser(firstname, lastname, username, email);
		} catch (NotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		gson.toJson(users);
		String jsonInString = gson.toJson(users);
		return jsonInString;
	}

	@PreAuthorize("hasAnyRole('Admin','User')")
	@GetMapping("/index")
	public String index()
	{
		String Menu = "Available endpoints:\n";
		Menu += "/home/list  to list all users\n" ;
		Menu += "/home/searchUsers  to search based on search criteria\n" ;
		Menu += "/home/send  to send a message to another user\n" ;
		Menu += "/home/get  to get all messages reveived\n" ;
		Menu += "/logout  to logout\n" ;
		Menu += "/register  to register a new user\n" ;
		
		
		String Output = Menu.replace("\n", "<br />\n");
		return Output;
	}
}
