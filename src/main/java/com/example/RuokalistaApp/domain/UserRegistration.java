package com.example.RuokalistaApp.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Note! This class is not an entity class. Class is used when creating new
 * application user.
 * 
 * ApplicationUser entity is used when user will be saved to db.
 * 
 * @author h01340
 *
 */
public class UserRegistration {
	
	@NotEmpty
	@Size(min = 4, max = 30)
	private String firstName = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String lastName = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	private String role = "USER";

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegistration(@NotEmpty @Size(min = 4, max = 30) String username,
			@NotEmpty @Size(min = 4, max = 30) String password, @NotEmpty @Size(min = 4, max = 30) String passwordCheck,
			@NotEmpty String role) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.role = role;
	}

	public UserRegistration(@NotEmpty @Size(min = 4, max = 30) String username,
			@NotEmpty @Size(min = 4, max = 30) String password,
			@NotEmpty @Size(min = 4, max = 30) String passwordCheck) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserRegistration [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", passwordCheck=" + passwordCheck + ", role=" + role + "]";
	}

}
