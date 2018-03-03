package com.nishant.third.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="message_id")
	private int MessageID;
	
	@Column(name="time_generated")
	private Date TimeGenerated;
	
	@Column(name="message_content")
	private String MessageContent;
	
	@Column(name="sender_id")
	private int SenderID;
	
	@Column(name="receiver_id")
	private int ReceiverID;
	
	@Column(name="is_read")
	private boolean IsRead;
	
	@Column(name="is_deleted")
	private boolean IsDeleted;

	public int getMessageID()
	{
		return MessageID;
	}

	public void setMessageID(int messageID)
	{
		MessageID = messageID;
	}

	public Date getTimeGenerated()
	{
		return TimeGenerated;
	}

	public void setTimeGenerated(Date timeGenerated)
	{
		TimeGenerated = timeGenerated;
	}

	public String getMessageContent()
	{
		return MessageContent;
	}

	public void setMessageContent(String messageContent)
	{
		MessageContent = messageContent;
	}

	public int getSenderID()
	{
		return SenderID;
	}

	public void setSenderID(int senderID)
	{
		SenderID = senderID;
	}

	public int getReceiverID()
	{
		return ReceiverID;
	}

	public void setReceiverID(int receiverID)
	{
		ReceiverID = receiverID;
	}

	public boolean isIsRead()
	{
		return IsRead;
	}

	public void setIsRead(boolean isRead)
	{
		IsRead = isRead;
	}

	public boolean isIsDeleted()
	{
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted)
	{
		IsDeleted = isDeleted;
	}
}
