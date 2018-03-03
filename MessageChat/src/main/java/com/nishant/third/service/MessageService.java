package com.nishant.third.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nishant.third.models.CustomUserDetails;
import com.nishant.third.models.Message;
import com.nishant.third.models.User;
import com.nishant.third.repository.MessageRepository;
import com.nishant.third.repository.UsersRepository;

@Service
public class MessageService
{
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SessionFactory sessionFactory;
		
	public void setSessionFactory(SessionFactory sessionFactory) {
	     this.sessionFactory = sessionFactory;
	 }	
	
	public String SendMessage(Message message)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
		Session sess = null;
	    Transaction tran = null;
		try
		{
			sess = sessionFactory.openSession();
		    tran = sess.beginTransaction();
				
			Message newMessage = new Message();
			newMessage.setMessageContent(message.getMessageContent());
			newMessage.setSenderID(customUser.getUserID());
			newMessage.setReceiverID(message.getReceiverID());
			newMessage.setIsDeleted(false);
			newMessage.setIsRead(false);
			sess.save(newMessage);
			tran.commit();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return "Message Failed";
		} finally
		{
			sess.close();
		}
		return "Message Sent";
	}
	
	
	public List<Message> GetMessages() throws NotFoundException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
		
		List<Message> messageList = new ArrayList<>();
		messageList = messageRepository.GetAllMessages(customUser.getUserID());
		
		Session sess = null;
	    Transaction tran = null;
		try
		{
			sess = sessionFactory.openSession();
		    tran = sess.beginTransaction();
				
		    for (Message message : messageList)
			{
				message.setIsRead(true);
				sess.update(message);
			}
			tran.commit();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			sess.close();
		}
		
		return messageList;
	}

}
