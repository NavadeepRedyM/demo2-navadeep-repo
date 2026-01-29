package com.cg.service; // Defines the package for security and login business logic

import org.springframework.beans.factory.annotation.Autowired; // For automatic dependency injection
import org.springframework.stereotype.Service; // Marks this class as a Service component in Spring

import com.cg.entity.UserDetails; // Import for the UserDetails entity class
import com.cg.repository.ILoginRepository; // Import for the user login database repository

@Service // Tells Spring that this class manages the login and registration logic
public class LoginService {

        @Autowired // Automatically injects the implementation of ILoginRepository
        ILoginRepository repo;
        
       // Method to register and persist a new user into the database
       public void saveUser(UserDetails user) {
    	   repo.save(user); // Calls JpaRepository's save method to store user credentials
       }
      
       // Method to retrieve user details based on a unique username
       public UserDetails checkUser(String user) {
    	    // Calls a custom repository method to find the user for authentication
    	    return repo.checkUser(user);
       }
}
