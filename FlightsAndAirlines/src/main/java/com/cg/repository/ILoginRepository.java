package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.UserDetails;

@Repository
public interface ILoginRepository extends JpaRepository<UserDetails, Integer>{
        
    @Query("SELECT u FROM UserDetails u WHERE u.userName = ?1")
	   public UserDetails checkUser(String user);
}
