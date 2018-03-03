package com.nishant.third.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nishant.third.models.Message;


public interface MessageRepository extends JpaRepository<Message,Integer>
{
	//This query can be modified to either get only the unread messages or read messages.
	//Similarly, additional query can be added to mark individual messages to read or unread.
	
	//For simplicity I retrieve all message belonging to the receiver	
	@Query(value="SELECT *FROM message WHERE receiver_id = ?1",nativeQuery=true)
    List<Message> GetAllMessages(int ReceiverID);
	
}