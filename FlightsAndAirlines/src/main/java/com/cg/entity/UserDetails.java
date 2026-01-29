package com.cg.entity; // Defines the package for data models/entities

import jakarta.persistence.Column; // Import to specify column mapping details
import jakarta.persistence.Entity; // Marks this class as a database-mapped entity
import jakarta.persistence.GeneratedValue; // Annotation to manage primary key generation
import jakarta.persistence.GenerationType; // Strategy for auto-incrementing IDs
import jakarta.persistence.Id; // Marks the field as the primary key
import jakarta.persistence.Table; // Specifies the specific database table name

@Entity // Instructs JPA that this class represents a table in the database
@Table(name="users") // Maps this entity specifically to the "users" table
public class UserDetails {
     
	@Id // Sets 'id' as the unique Primary Key for the table
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Database handles auto-increment for this ID
	private int id; // Unique identifier for each user
	
	@Column(unique = true, nullable = false) // Ensures usernames are unique and cannot be empty/null
	private String userName; // Field to store the login username
	
	@Column // Maps this field to a standard column in the table
	private String password; // Field to store the (usually hashed) user password

	// Default no-argument constructor required by JPA for entity instantiation
	public UserDetails() {
		
	}
	
	// Parameterized constructor to create a UserDetails object with specific values
	public UserDetails(int id, String userName, String password) {
		super(); // Calls the base Object class constructor
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	// Getter and Setter methods:
	// These allow Spring Security, JPA, and other services to access and update private user data

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
