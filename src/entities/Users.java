package entities;

import java.util.ArrayList;
import java.util.List;

public class Users {

	private Integer userID;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private Boolean active;
	
	private List<Role> roles;
	
	public Users() {

	}


	public Users(Integer userID, String firstName, String lastName, String userName, String email, String password,
			Boolean active) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.active = active;
	}


	public Integer getUserID() {
		return userID;
	}


	public void setUserID(Integer userID) {
		this.userID = userID;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean isActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		roles.add(role);

	}

	public boolean hasRole(String role) {
		if (this.roles == null) {
			return false;
		}
		return this.roles.stream().anyMatch(r -> role.equals(r.getName()));
	}


	@Override
	public String toString() {
		return "Users:\n userID = " + userID + ", firstName = " + firstName + ", lastName = " + lastName + ", userName = "
				+ userName + "\n email = " + email + ", password = " + password + ", active = " + active + ", roles = " + roles;
	}
	
	
	
}
