package com.nishant.third.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nishant.third.models.User;


public interface UsersRepository extends JpaRepository<User,Integer>
{
	User findByUsername(String username);
	
	@Query(value="SELECT * FROM user u where LOWER(u.first_name) LIKE :first_name OR LOWER(u.last_name) LIKE :last_name OR LOWER(u.user_name) LIKE :user_name OR LOWER(u.email) LIKE :email",nativeQuery=true)
	List<User> searchUser(@Param("first_name")String firstname, @Param("last_name")String lastname, @Param("user_name")String username, @Param("email")String email);
	
		
}
