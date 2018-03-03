package com.nishant.third.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_role")
public class UserRole
{
	@Id
	@Column(name = "user_id")
    private int UserID;
	
	
    @Column(name = "role_id")
    private int RoleID;

	public int getUserID()
	{
		return UserID;
	}

	public void setUserID(int userID)
	{
		UserID = userID;
	}

	public int getRoleID()
	{
		return RoleID;
	}

	public void setRoleID(int roleID)
	{
		RoleID = roleID;
	}
	
}
