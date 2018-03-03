package com.nishant.third.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nishant.third.models.CustomUserDetails;
import com.nishant.third.models.User;
import com.nishant.third.models.Users;
import com.nishant.third.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = usersRepository.findByUsername(username);
		if (user == null)
		{
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);
	}

	public Users userList() throws NotFoundException
	{
		List<User> userList = new ArrayList<>();
		userList = usersRepository.findAll();

		Set<User> users = new HashSet<User>(userList);

		Users usersObject = new Users();
		usersObject.setUsers(users);
		return usersObject;
	}

	public Users searchUser(String firstname, String lastname, String username, String email) throws NotFoundException
	{
		// Check if value is not null or empty
		if (!(firstname.equalsIgnoreCase("")) && !(firstname == null))
		{
			firstname = "%" + firstname + "%";
		}
		if (!(lastname.equalsIgnoreCase("")) && !(lastname == null))
		{
			lastname = "%" + lastname + "%";
		}
		if (!(username.equalsIgnoreCase("")) && !(username == null))
		{
			username = "%" + username + "%";
		}
		if (!(email.equalsIgnoreCase("")) && !(email == null))
		{
			email = "%" + email + "%";
		}

		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(username);
		System.out.println(email);
		
		List<User> userList = new ArrayList<>();
		userList = usersRepository.searchUser(firstname, lastname, username, email);

		Set<User> users = new HashSet<User>(userList);

		Users usersObject = new Users();
		usersObject.setUsers(users);
		return usersObject;
	}
}
