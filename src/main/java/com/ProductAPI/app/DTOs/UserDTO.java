package com.ProductAPI.app.DTOs;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.ProductAPI.app.EntityObjects.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserDTO {
	
	private Integer id;
	
	@NotEmpty(message = "The email must not be empty or null")
	@Email
	private String email;
	@NotEmpty(message = "The first name must not be empty or null")
	private String first_name;
	@NotEmpty(message = "The last name must not be empty or null")
	private String last_name;
	@NotEmpty(message = "The username must not be empty or null")
	private String username;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setUsername(username);
		user.setEmail(email);
		return user;
	}
	public static UserDTO fromUser(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setFirst_name(user.getFirst_name());
		userDto.setLast_name(user.getLast_name());
		userDto.setEmail(user.getEmail());
		return userDto;
	}

	
}
