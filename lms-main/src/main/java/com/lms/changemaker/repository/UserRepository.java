package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public	User getUserByUserName(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public	User getUserByUserEmail(String email);
	
	
	@Query("SELECT u FROM User u WHERE u.type = :type")
	public	List<User> getUserByType(int type);


	@Modifying
	@Query("UPDATE User u set u.token =:token  WHERE u.userId =:userId")
    void saveUserToken(String token, int userId);
}
