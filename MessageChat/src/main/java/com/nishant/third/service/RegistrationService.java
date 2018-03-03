package com.nishant.third.service;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nishant.third.extensions.extensions;
import com.nishant.third.models.User;
import com.nishant.third.models.UserRole;
import com.nishant.third.repository.UsersRepository;

@Service
public class RegistrationService
{
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UsersRepository usersRepository;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public String RegisterUser(User user)
	{
		// Check if Email and Password is valid
		boolean isEmailValid = extensions.isValidEmailAddress(user.getEmail());
		boolean isPasswordValid = extensions.isValidPassword(user.getPassword());
		// Note: Other fields can also be pre-checked without hitting the
		// database.
		// For this example I am only checking Email and Password

		if (!isEmailValid || !isPasswordValid)
		{
			return "Invalid Email or Password format. Refer documentation";
		} else
		{
			Session sess = null;
			Transaction tran = null;
			try
			{
				sess = sessionFactory.openSession();
				tran = sess.beginTransaction();
				User newuser = new User();
				newuser.setUsername(user.getUsername());
				newuser.setPassword(user.getPassword());
				newuser.setFirstName(user.getFirstName());
				newuser.setLastName(user.getLastName());
				newuser.setEmail(user.getEmail());
				newuser.setIsDeleted(false);
				sess.save(newuser);
				tran.commit();
				try
				{		
					tran = sess.beginTransaction();
					
					UserRole userrole = new UserRole();
					userrole.setRoleID(1);
					userrole.setUserID(usersRepository.findByUsername(user.getUsername()).getUserID());
					
					
					sess.save(userrole);
					tran.commit();
				} 
				catch (Exception ex)
				{
					return ex.getCause().getMessage();
				} finally
				{
				}				
				
			} catch (Exception ex)
			{
				sess.close();
				return ex.getCause().getMessage();
			} finally
			{
			}
			return "Account created successfully!";
		}
	}
}
