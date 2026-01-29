package com.cg.controller; // Defines the package for authentication logic

import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Token to represent a logged-in user
import org.springframework.security.core.authority.AuthorityUtils; // Utility to handle user roles/permissions
import org.springframework.security.core.context.SecurityContext; // Container for the authentication details
import org.springframework.security.core.context.SecurityContextHolder; // Helper that provides access to the security context
import org.springframework.security.crypto.password.PasswordEncoder; // Tool for hashing and matching passwords
import org.springframework.security.web.context.HttpSessionSecurityContextRepository; // Manages security context between HTTP requests
import org.springframework.security.web.context.SecurityContextRepository; // Interface for storing security context
import org.springframework.stereotype.Controller; // Marks the class as a Spring MVC controller
import org.springframework.ui.Model; // Used to share data with the UI
import org.springframework.web.bind.annotation.GetMapping; // Handles GET requests
import org.springframework.web.bind.annotation.ModelAttribute; // Binds form data to objects
import org.springframework.web.bind.annotation.PostMapping; // Handles POST requests
import org.springframework.web.bind.annotation.RequestParam; // Extracts form field values
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Passes temporary data during redirects

import com.cg.entity.UserDetails; // Import for the User entity
import com.cg.service.LoginService; // Service for user-related database operations

import jakarta.servlet.http.HttpServletRequest; // Represents the HTTP request object
import jakarta.servlet.http.HttpServletResponse; // Represents the HTTP response object

@Controller // Designates this class as a web controller
public class LoginController {

	@Autowired // Injects the service to find users in the database
	LoginService service;

	@Autowired // Injects the BCrypt or relevant password encoder bean
	PasswordEncoder passwordEncoder;

	// Initialises the repository to save the login session into the HTTP session
	private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	@GetMapping("/login") // Displays the login page
	public String loginPage() {
		return "Flight/login"; // Returns the login.html view
	}

	@PostMapping("/login") // Processes the login credentials
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String pass,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

		// Fetches user details from the database by username
		UserDetails user = service.checkUser(username);

		// Validates user existence and matches the raw password with the encoded hash
		if (user != null && passwordEncoder.matches(pass, user.getPassword())) {
			
			// 1. Create Authentication: Creates a token for the authenticated user with no specific roles
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					AuthorityUtils.NO_AUTHORITIES);

			// 2. Set Context: Creates a fresh security context and places the auth token inside it
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(auth);
			SecurityContextHolder.setContext(context);

			// 3. PERSIST THE SESSION: Saves the authentication context into the session so the user stays logged in
			securityContextRepository.saveContext(context, request, response);

			// Adds the username to a flash attribute for a one-time welcome message
			redirectAttributes.addFlashAttribute("loggedUserName", username);
			return "redirect:/api/list"; // Redirects to the flight list on success
		}

		// Redirects back to login with an error parameter if authentication fails
		return "redirect:/login?error=true";
	}

	@GetMapping("/register") // Displays the registration form
	public String registerUser(Model model) {
		model.addAttribute("UserDetails", new UserDetails()); // Binds an empty user object to the form
		return "Flight/register"; // Returns the registration view
	}

	@PostMapping("/register") // Processes the registration form
	public String saveUser(@ModelAttribute("UserDetails") UserDetails user) {
		// Hashes the plain text password before saving it for security
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		service.saveUser(user); // Saves the new user to the database
		return "redirect:/login"; // Redirects to login page after successful registration
	}
}
